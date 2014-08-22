/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.control;

import at.jollydays.booking.Globals;
import at.jollydays.booking.bo.BuhaFilter;
import at.jollydays.booking.bo.Invoice; 
import at.jollydays.booking.bo.InvoiceItem;
import at.jollydays.booking.bo.NukeMrcommerceArrangementCity;
import at.jollydays.booking.bo.NukeMrcommerceHistory;
import at.jollydays.booking.bo.NukeMrcommerceItems;
import at.jollydays.booking.bo.Partner;
import at.jollydays.booking.db.NukeMrcommerceHistoryJpaController;
import com.csvreader.CsvReader;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gunter reinitzer
 */
public class BookingController extends BookingHelper {

    private ConfigurationController configurationController;
    private DateFormat format = new SimpleDateFormat("yyyyMMdd");
    private static NumberFormat numberFormat = NumberFormat.getNumberInstance();
    private BookingAR bookingAR;
    private BookingER bookingER;

    public BookingController(ConfigurationController configurationController) {
        this.configurationController = configurationController;
        numberFormat.setGroupingUsed(false);
        bookingAR = new BookingAR(configurationController);
        bookingER = new BookingER(configurationController);
    }    
    
    private PaymentStatistic processKZPaymentRecord(ArrayList<PaymentRecord> recordList, LoggingHandler lh) throws IOException {

        if (!recordList.isEmpty()) {
            lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "  Kunde: " + recordList.get(0).getKontoNr());
        }
        PaymentStatistic statistic = new PaymentStatistic();

        for (PaymentRecord record : recordList) {

            Invoice invoice = null;
            //if (record.getBelegNr() < Globals.MINKUNDENRECHNUNG || configurationController.getProperty(Globals.PREFIXINVAR + record.getBelegNr()) != null) {
            //wird nicht mehr als Fehler gemeldet, daher keine MIN-Prüfung mehr
            if (configurationController.getProperty(Globals.PREFIXINVAR + record.getBelegNr()) != null) {
                continue;
            }

            //Rechnung noch nicht verarbeitet
            List<Invoice> invoices = configurationController.getInvoiceJpaController().findInvoiceByNumber(record.getBelegNr());
            if (invoices.size() > 0) {
                invoice = invoices.iterator().next();
            }

            if (invoice != null) {
                lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "    Rechnung " + invoice.getInvoiceNumber());
                for (InvoiceItem invoiceItem : invoice.getInvoiceItemCollection()) {

                    // nur Gutscheine die gekauft wurden
                    // ab Okt. 2012 nicht nur Gutscheine, sondern auch 95er Items
                    if (invoiceItem.getUniqueNumber() != null
                        && !invoiceItem.getUniqueNumber().equals(Globals.EMPTYSTRING)
                        && invoiceItem.getMrcommerceItemID().intValue() < Globals.GUTSCHEINMAXITEMNR
                        && invoiceItem.getAmount().doubleValue() > 0.0) {

                        lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "   Item: " + invoiceItem.getId() + "      Gutschein: " + invoiceItem.getUniqueNumber() != null ? invoiceItem.getUniqueNumber() : Globals.EMPTYSTRING);
                        if (invoiceItem.getBHstatus() != null
                                && (invoiceItem.getBHstatus().intValue() == Globals.BHSTATUSNICHTBEZ || // nicht bezahlt
                                invoiceItem.getBHstatus().intValue() == Globals.BHSTATUSMAHNUNG || // Mahnung (nicht bez.)
                                invoiceItem.getBHstatus().intValue() == Globals.BHSTATUSMANFREI || // manuell frei für Buchung
                                invoiceItem.getBHstatus().intValue() == Globals.BHSTATUSMAHNFREI) // Mahnung (frei f. Buch.)
                                ) {
                            int result = 0;
                            
//                            if (    invoiceItem.getUniqueNumber() != null && 
//                                    !invoiceItem.getUniqueNumber().equals(Globals.EMPTYSTRING) && 
//                                    invoiceItem.getMrcommerceItemID().intValue() < Globals.GUTSCHEINMAXITEMNR && 
//                                    invoiceItem.getAmount().doubleValue() > 0.0) {
                                result = configurationController.getInvoiceItemJpaController().updateInvoiceItemStatus(invoiceItem.getUniqueNumber(), Globals.BHSTATUSBEZAHLT);                            
                               
                                //                            } else {
//                                result = configurationController.getInvoiceItemJpaController().updateInvoiceItemStatus(invoiceItem, Globals.BHSTATUSBEZAHLT);                                                             
//                            }
                                
                            if (result < 0) {
                                lh.getLogger(Globals.LOGGINGKZ).log(Level.SEVERE, "Fehler bei Item: " + invoiceItem.getId() + " Gutschein: " + invoiceItem.getUniqueNumber() + " in Rechnung " + invoice.getInvoiceNumber() + " : " + result);
                                statistic.incErrorCounter(1);
                            } else if (result > 0) {
                                lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "Item: " + invoiceItem.getId() + " Gutschein: " + invoiceItem.getUniqueNumber() + " freigeschaltet");
                                statistic.incVoucherOkCounter(1);
                                
//                                // When das bezahlte Item ein Raktivierungsgebühr war -> den zugehörigen Gutschein wieder auf frei für Einlösung setzen
//                                if (invoiceItem.getMrcommerceItemID().intValue() == Globals.GUTSCHEINREAKTIVIERUNG) {
//                                    InvoiceItem invoiceItemOld = invoiceItem.getInvoiceItemHistory().getInvoiceItemOld();
//                                    if (invoiceItemOld != null) {
//                                            NukeMrcommerceHistory histEntryBH = new NukeMrcommerceHistory();
//                                            histEntryBH.setNukeHistoryDate(new Date());
//                                            histEntryBH.setNukeHistoryPass(invoiceItemOld.getUniqueNumber());
//                                            histEntryBH.setNukeHistoryMsg("Gutschein: " + invoiceItemOld.getUniqueNumber() + " nach Reaktivierung wieder freigeschaltet - Anpassung BH");
//                                            histEntryBH.setNukeHistoryStatustype((short) 2);
//                                            histEntryBH.setNukeHistoryStatus(invoiceItemOld.getBHstatus());
//                                            NukeMrcommerceHistoryJpaController HistoryJPAController = new NukeMrcommerceHistoryJpaController();
//                                            HistoryJPAController.create(histEntryBH);
//                                            NukeMrcommerceHistory histEntryFF = new NukeMrcommerceHistory();
//                                            histEntryFF.setNukeHistoryDate(new Date());
//                                            histEntryFF.setNukeHistoryPass(invoiceItemOld.getUniqueNumber());
//                                            histEntryFF.setNukeHistoryMsg("Gutschein: " + invoiceItemOld.getUniqueNumber() + " nach Reaktivierung wieder freigeschaltet - Anpassung FF");
//                                            histEntryFF.setNukeHistoryStatustype((short) 1);
//                                            histEntryFF.setNukeHistoryStatus(invoiceItemOld.getFFstatus());
//                                            HistoryJPAController.create(histEntryFF);
//
//                                        result = configurationController.getInvoiceItemJpaController().updateInvoiceItemStatus(invoiceItemOld.getUniqueNumber(), Globals.BHSTATUSBEZAHLT);                            
//                                        int resultFF = 0;
//                                        resultFF = configurationController.getInvoiceItemJpaController().updateInvoiceItemFFStatus(invoiceItemOld.getUniqueNumber(), Globals.FFSTATUSFREIEINLOESUNG);                           
//                                        if (result < 0 || resultFF < 0) {
//                                            lh.getLogger(Globals.LOGGINGKZ).log(Level.SEVERE, "Fehler bei Item: " + invoiceItemOld.getId() + " Gutschein: " + invoiceItemOld.getUniqueNumber() + " in Rechnung " + invoiceItemOld.getInvoice().getInvoiceNumber() + " : " + result);
//                                            statistic.incErrorCounter(1);
//                                        } else if (result > 0 && resultFF > 0) {
//                                            lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "Item: " + invoiceItemOld.getId() + " Gutschein: " + invoiceItemOld.getUniqueNumber() + " nach Reaktivierung wieder freigeschaltet");
//                                            statistic.incVoucherOkCounter(1);
//                                        }
//                                    }
//                                        
//                                }
                            }

                        } else {
                            lh.getLogger(Globals.LOGGINGKZ).log(Level.WARNING, "Item: " + invoiceItem.getId() + " Gutschein " + invoiceItem.getUniqueNumber() + " in Rechnung " + invoice.getInvoiceNumber() + " nicht freigeschalten: hat BH-Status " + invoiceItem.getBHstatus());
                            statistic.incStatusNokCounter(1);
                        }
//                    }
                } else if (    
                                
                                    (invoiceItem.getMrcommerceItemID().intValue() < Globals.GUTSCHEINMAXITEMNR
                                        ||  Globals.NONGUTSCHEINITEMSKZ.contains(invoiceItem.getMrcommerceItemID().intValue()))
                                    && invoiceItem.getAmount().doubleValue() > 0.0) {
                    
                                    //InvoiceItem invoiceItemOld = invoiceItem.getInvoiceItemHistory().getInvoiceItemOld();
                                    lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "   Item: " + invoiceItem.getId());
                                    if (invoiceItem.getBHstatus() != null
                                            && (invoiceItem.getBHstatus().intValue() == Globals.BHSTATUSNICHTBEZ || // nicht bezahlt
                                            invoiceItem.getBHstatus().intValue() == Globals.BHSTATUSMAHNUNG || // Mahnung (nicht bez.)
                                            invoiceItem.getBHstatus().intValue() == Globals.BHSTATUSMANFREI || // manuell frei für Buchung
                                            invoiceItem.getBHstatus().intValue() == Globals.BHSTATUSMAHNFREI) // Mahnung (frei f. Buch.)
                                            ) {
                                        int result = 0;
                                        int result2 = 0;

            //                            if (    invoiceItem.getUniqueNumber() != null && 
            //                                    !invoiceItem.getUniqueNumber().equals(Globals.EMPTYSTRING) && 
            //                                    invoiceItem.getMrcommerceItemID().intValue() < Globals.GUTSCHEINMAXITEMNR && 
            //                                    invoiceItem.getAmount().doubleValue() > 0.0) {
                                            //result = configurationController.getInvoiceItemJpaController().updateInvoiceItemStatus(invoiceItem.getUniqueNumber(), Globals.BHSTATUSBEZAHLT);                            

                                            //                            } else {
                                        if (Globals.NONGUTSCHEINITEMSKZ.contains(invoiceItem.getMrcommerceItemID().intValue())) {
                                            result = configurationController.getInvoiceItemJpaController().updateInvoiceItemStatus(invoiceItem, Globals.BHSTATUS95ABGESCHLOSSEN);
                                            result2 = configurationController.getInvoiceItemJpaController().updateInvoiceItemFFStatus(invoiceItem, Globals.FFSTATUS95ABGESCHLOSSEN);
                                            lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "   Item: " + invoiceItem.getId() + " auf abgeschlossen gesetzt (95er Item)");
                                        } else {
                                            result = configurationController.getInvoiceItemJpaController().updateInvoiceItemStatus(invoiceItem, Globals.BHSTATUSBEZAHLT);
                                            result2 = configurationController.getInvoiceItemJpaController().updateInvoiceItemFFStatus(invoiceItem, Globals.FFSTATUSFREIEINLOESUNG);
                                            lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "   Item: " + invoiceItem.getId() + " auf bezahlt/frei für Einlösung gesetzt");
                                        }
                                        //result = configurationController.getInvoiceItemJpaController().updateInvoiceItemStatus(invoiceItem, Globals.BHSTATUSBEZAHLT);                                                             
            //                            }

                                        if (result < 0) {
                                            lh.getLogger(Globals.LOGGINGKZ).log(Level.SEVERE, "Fehler bei Item: " + invoiceItem.getId() + "  in Rechnung " + invoice.getInvoiceNumber() + " : " + result);
                                            statistic.incErrorCounter(1);
                                        } else if (result == 0) {
                                            lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "Item: " + invoiceItem.getId() + " freigeschaltet");
                                            statistic.incVoucherOkCounter(1);

                                            // When das bezahlte Item ein Raktivierungsgebühr war -> den zugehörigen Gutschein wieder auf frei für Einlösung setzen
                                            if (invoiceItem.getMrcommerceItemID().intValue() == Globals.GUTSCHEINREAKTIVIERUNG) {
                                                //InvoiceItem invoiceItemOld = invoiceItem.getInvoiceItemHistory().getInvoiceItemOld();
                                                InvoiceItem invoiceItemOld = invoiceItem.getInvoiceItemHistory().getInvoiceItemOld();
                                                lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "   Item: " + invoiceItem.getId() + " bezieht sich auf Gutschein: " + invoiceItemOld.getUniqueNumber() != null ? invoiceItemOld.getUniqueNumber() : Globals.EMPTYSTRING);

                                                if (invoiceItemOld != null) {
                                                        NukeMrcommerceHistory histEntryBH = new NukeMrcommerceHistory();
                                                        histEntryBH.setNukeHistoryDate(new Date());
                                                        histEntryBH.setNukeHistoryPass(invoiceItemOld.getUniqueNumber());
                                                        histEntryBH.setNukeHistoryMsg("Gutschein: " + invoiceItemOld.getUniqueNumber() + " nach Reaktivierung wieder freigeschaltet - Anpassung BH");
                                                        histEntryBH.setNukeHistoryStatustype((short) 2);
                                                        histEntryBH.setNukeHistoryStatus(invoiceItemOld.getBHstatus());
                                                        NukeMrcommerceHistoryJpaController HistoryJPAController = new NukeMrcommerceHistoryJpaController();
                                                        HistoryJPAController.create(histEntryBH);
                                                        NukeMrcommerceHistory histEntryFF = new NukeMrcommerceHistory();
                                                        histEntryFF.setNukeHistoryDate(new Date());
                                                        histEntryFF.setNukeHistoryPass(invoiceItemOld.getUniqueNumber());
                                                        histEntryFF.setNukeHistoryMsg("Gutschein: " + invoiceItemOld.getUniqueNumber() + " nach Reaktivierung wieder freigeschaltet - Anpassung FF");
                                                        histEntryFF.setNukeHistoryStatustype((short) 1);
                                                        histEntryFF.setNukeHistoryStatus(invoiceItemOld.getFFstatus());
                                                        HistoryJPAController.create(histEntryFF);

                                                    result = configurationController.getInvoiceItemJpaController().updateInvoiceItemStatusReact(invoiceItemOld.getUniqueNumber(), Globals.BHSTATUSBEZAHLT);                            
                                                    int resultFF = 0;
                                                    resultFF = configurationController.getInvoiceItemJpaController().updateInvoiceItemFFStatusReact(invoiceItemOld.getUniqueNumber(), Globals.FFSTATUSFREIEINLOESUNG);                           
                                                    if (result < 0 || resultFF < 0) {
                                                        lh.getLogger(Globals.LOGGINGKZ).log(Level.SEVERE, "Fehler bei Item: " + invoiceItemOld.getId() + " Gutschein: " + invoiceItemOld.getUniqueNumber() + " in Rechnung " + invoiceItemOld.getInvoice().getInvoiceNumber() + " : " + result);
                                                        statistic.incErrorCounter(1);
                                                    } else if (result > 0 && resultFF > 0) {
                                                        lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "Item: " + invoiceItemOld.getId() + " Gutschein: " + invoiceItemOld.getUniqueNumber() + " nach Reaktivierung wieder freigeschaltet");
                                                        statistic.incVoucherOkCounter(1);
                                                    }
                                                }

                                            }
                                        }

                                    }
                        
                    }
                }
                configurationController.setProperty(Globals.PREFIXINVAR + record.getBelegNr(), format.format(new Date()));
//configurationController.getProperty("PREINVAR26890811");
                
            } else {
                lh.getLogger(Globals.LOGGINGKZ).log(Level.WARNING, "Fehler bei Rechnung " + record.getBelegStr() + ": Rechnung nicht gefunden! ");
                statistic.incInvoiceNotFoundCounter(1);
            }
        }
        return statistic;
    }
    
    private PaymentStatistic processVZPaymentRecord(ArrayList<PaymentRecord> recordList, LoggingHandler lh) throws IOException {

        if (!recordList.isEmpty()) {
            lh.getLogger(Globals.LOGGINGVZ).log(Level.INFO, "  Kunde: " + recordList.get(0).getKontoNr());
        }
        PaymentStatistic statistic = new PaymentStatistic();
        return statistic;
    }

    public void processStorno(Date from, Date to, File dir, LoggingHandler lh, CSVController cSVController) {
        try {
            List<Invoice> invoices = configurationController.getInvoiceJpaController().findInvoiceEntitiesOrdertypeDate(4, 10, from, to, 0);
            
            JDCsvWriter writer = cSVController.getWriterStorno();
            
            for(Invoice invoice : invoices) {
                writer.write(format.format(invoice.getInvoiceDate()));
                writer.write(invoice.getInvoiceNumber());
                writer.write(Integer.toString(invoice.getPartnerID() + Globals.DEBITORSTARTVALUE));
                writer.endRecord();
            }
        } catch (IOException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class PaymentRecord {

        String kontoStr;
        String belegStr;
        String opBetragStr;
        double betragNr;
        int kontoNr;
        int belegNr;

        public PaymentRecord(CsvReader reader) throws IOException, NotFoundException {
            if (!reader.readRecord()) {
                throw new NotFoundException("kein Payment gefunden");
            }
            //ToDo gleiche Belege nur einmal abarbeiten
            kontoStr = reader.get("Bu-Konto");
            belegStr = reader.get("BelegNr");
//                String opBetragStr = reader.get("OP/Kost");
            opBetragStr = reader.get("OP-Betrag");
            opBetragStr = opBetragStr.replace(".", "");
            opBetragStr = opBetragStr.replace(",", ".");


            betragNr = 0;
            try {
                betragNr = Float.parseFloat(opBetragStr.replace(',', '.'));
            } catch (Throwable t) {
            }

            kontoNr = 0;
            try {
                kontoNr = Integer.parseInt(kontoStr);
            } catch (Throwable t) {
            }

            belegNr = 0;
            try {
                belegNr = Integer.parseInt(belegStr);
            } catch (Throwable t) {
            }
        }

        public String getKontoStr() {
            return kontoStr;
        }

        public String getBelegStr() {
            return belegStr;
        }

        public String getOpBetragStr() {
            return opBetragStr;
        }

        public double getBetragNr() {
            return betragNr;
        }

        public int getKontoNr() {
            return kontoNr;
        }

        public int getBelegNr() {
            return belegNr;
        }
    }

    public int processARPacket(int startInvoice, int endInvoice, int maxResults, LoggingHandler loggingHandler, CSVController cSVController, boolean keyCaching) throws IOException, NotFoundException {
        configurationController.setCSVController(cSVController);
        configurationController.setLoggingHandler(loggingHandler);
        return bookingAR.processARPacket(configurationController, startInvoice, endInvoice, maxResults, keyCaching);
    }

    public int processERPacket(int startInvoice, int endInvoice, int maxResults, LoggingHandler loggingHandler, CSVController cSVController, boolean keyCaching) throws NothingFoundException, IOException {
        configurationController.setCSVController(cSVController);
        configurationController.setLoggingHandler(loggingHandler);
        return bookingER.processERPacket(startInvoice, endInvoice, maxResults, keyCaching);
    }

    public int processDebitor(int partnerNr, int startValue, int maxResults, boolean all, LoggingHandler lh, CSVController cSVController) throws IOException {
        configurationController.setCSVController(cSVController);
        configurationController.setLoggingHandler(lh);
        return bookingAR.processDebitor(configurationController, partnerNr, startValue, maxResults, all, lh);
    }

    public void processSingleDebitor(Partner partner, LoggingHandler lh, CSVController cSVController) throws IOException {
        configurationController.setCSVController(cSVController);
        configurationController.setLoggingHandler(lh);
        bookingAR.writeDBSatz(configurationController, partner);
    }
    
    public int processKreditor(int startValue, int maxResults, boolean all, LoggingHandler lh, CSVController cSVController) throws IOException {
        configurationController.setCSVController(cSVController);
        configurationController.setLoggingHandler(lh);
        return bookingER.processKreditor(configurationController, startValue, maxResults, all);
    }

    public PaymentStatistic processKZ(File csvFile, LoggingHandler lh) throws IOException {

        CsvReader reader = null;
        ArrayList<PaymentRecord> recordList = new ArrayList();
        Double sum = 0.0;
        PaymentStatistic statisticAll = new PaymentStatistic();

        try {

            reader = new CsvReader(csvFile.getCanonicalPath());
            reader.setDelimiter(';');
            reader.readHeaders();
            
            // Headers ohne Spaces mittels trim
            String[] headers = new String[reader.getHeaderCount()];
            for (int i=0; i<reader.getHeaderCount(); i++) {
               headers[i] =reader.getHeader(i).trim();
            }
            reader.setHeaders(headers);
            // http://www.csvreader.com/java_csv_samples.php
            // http://javacsv.sourceforge.net/

            PaymentRecord record = new PaymentRecord(reader);
            recordList.add(record);

            while (true) {
                try {

                    PaymentRecord newRecord = new PaymentRecord(reader);

                    if (newRecord.getKontoNr() == record.getKontoNr()) {
                        // nicht verarbeiten, gleicher Kunde
                        recordList.add(newRecord);
                        sum = sum + newRecord.getBetragNr();
                        record = newRecord;
                        continue;
                    }

                    //unterschiedliche Kundennummer, alte Belege verarbeiten
                    if (sum == 0.0) {
                        PaymentStatistic statistic = processKZPaymentRecord(recordList, lh);
                        statisticAll.addStatistic(statistic);
                    }
                    //System.out.println("Kunde " + record.getKontoStr() + "OP: " + sum);
                    recordList = new ArrayList();
                    sum = 0.0;
                    recordList.add(newRecord);
                    sum = sum + newRecord.getBetragNr();
                    record = newRecord;
                    continue;

                } catch (Throwable th) {
                    // kein neuer record mehr, Liste verarbeiten
                    if (sum == 0.0) {
                        PaymentStatistic statistic = processKZPaymentRecord(recordList, lh);
                        statisticAll.addStatistic(statistic);
                    }
                }
                break;
            }

            reader.close();

        } catch (Throwable th) {
            lh.getLogger(Globals.LOGGINGKZ).log(Level.SEVERE, null, th);
            return statisticAll;
        }
        return statisticAll;
    }

    public PaymentStatistic processVZ(File csvFile, LoggingHandler lh) throws IOException {
        return new PaymentStatistic();
    }
    
    public void processDI(CSVController cSVController) throws IOException, NotFoundException {

        //this.cSVController = cSVController;
        configurationController.setCSVController(cSVController);
        List<NukeMrcommerceItems> items = configurationController.getItemsJpaController().findNukeMrcommerceItemsEntities();
                
        for (NukeMrcommerceItems item : items) {
            if (item.getNukeItemId() < 100000) {
                writeDISatz(item);
            }
        }
    }    
    
    public void processKT(int kostentraeger, LoggingHandler lh, CSVController cSVController) {
        //this.cSVController = cSVController;
        configurationController.setCSVController(cSVController);
        NukeMrcommerceArrangementCity arrCity = configurationController.getArrangementCityJpaController().findNukeMrcommerceArrangementCity(kostentraeger);
        if (arrCity != null) {
            try {
                NukeMrcommerceItems item = arrCity.getNukeMrcommerceItems();
                writeKTSatz(configurationController, arrCity, item);
            } catch (IOException ex) {
                
            }
        }
    }
    
    public static String roundScale2(double d) {
        BigDecimal bd = BigDecimal.valueOf(d);
        double db = (bd.setScale(2, RoundingMode.HALF_UP)).doubleValue();
        return numberFormat.format(db);
        //return Double.toString(db).replaceAll("\\.", ",");
        //return Double.toString(Math.rint(d * 100) / 100.).replaceAll("\\.", ",");
    }

    public static String prepareStringForCSV(String toReplace) {
        if (toReplace != null) {
            return toReplace.replaceAll(";", ",");
        }
        return Globals.EMPTYSTRING;
    }

    private void writeKTSatz(NukeMrcommerceArrangementCity arrCity, NukeMrcommerceItems item) throws IOException {
        //int id = arrCity.getNukeMrcommerceEventsArrangementCitiesPK().getArrangementToCityId();
        JDCsvWriter writer = configurationController.getCSVController().getWriterKT();

        if (writer != null) {
            configurationController.setProperty(Globals.PREFIXARRCITY + arrCity.getId(), arrCity.getId().toString());

            //   Versicherungserträge AT	VERSICHERUNGSERTRÄGE AT	x	x	0	1	1	0	x	x	x	x	2	1
            String desc = item.getNukeItemName() + " - " + arrCity.getNukeMrcommerceAddress().getDescription();
            writer.write("2");
            writer.write(arrCity.getId().toString());
            writer.write(desc);
            writer.endRecord();
        }
    }

    private void writeDISatz(NukeMrcommerceItems item) throws IOException {

        JDCsvWriter writer = configurationController.getCSVController().getWriterDI();
        if (writer != null) {
            writer.write("4");
            writer.write(Integer.toString(item.getNukeItemId()));
            writer.write(item.getNukeItemName());
            writer.endRecord();
        }
    }
    
    public String testBuchungslogikAR(int invoiceNumber) {
        return bookingAR.testBuchungslogikAR(invoiceNumber);
    }
    
    public ConfigurationController getConfigurationController() {
        return configurationController;
    }
}

class Auslandsfilter {

    private BuhaFilter invoiceFilter;
    private BuhaFilter organiserInvoiceFilter;

    BuhaFilter getInvoiceFilter() {
        return invoiceFilter;
    }
 
    
    void setInvoiceFilter(BuhaFilter invoiceFilter) {
        this.invoiceFilter = invoiceFilter;
    }

    BuhaFilter getOrganiserInvoiceFilter() {
        return organiserInvoiceFilter;
    }

    void setOrganiserInvoiceFilter(BuhaFilter organiserInvoiceFilter) {
        this.organiserInvoiceFilter = organiserInvoiceFilter;
    }
}
