/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.control;

import at.jollydays.booking.Globals;
import at.jollydays.booking.bo.BuhaBooking;
import at.jollydays.booking.bo.BuhaFilter;
import at.jollydays.booking.bo.BuhaKostl;
import at.jollydays.booking.bo.BuhaRabatt;
import at.jollydays.booking.bo.Invoice;
import at.jollydays.booking.bo.InvoiceItem;
import at.jollydays.booking.bo.NukeCooperationInvoiceDetails;
import at.jollydays.booking.bo.NukeMrcommerceItems;
import at.jollydays.booking.bo.NukeMrcommerceSaleschannelinvoice;
import at.jollydays.booking.bo.Partner;
import at.jollydays.booking.bo.VNukeCooperationDeliverynoteDetails;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Gunter Reinitzer
 */
public class BookingAR extends BookingARWriter {
    
    private ConfigurationController configurationController;
    
    BookingAR(ConfigurationController configurationController) {
        this.configurationController = configurationController;
    }

    public int processARPacket(ConfigurationController configurationController, int startInvoice, int endInvoice, int maxResults, boolean keyCaching) throws IOException, NotFoundException {

        if (endInvoice > 0 && endInvoice > startInvoice){
            maxResults = endInvoice - startInvoice + 1;
        }
        List<Invoice> invoices = configurationController.getInvoiceJpaController().findInvoiceEntitiesNext(maxResults, startInvoice);

        Invoice invoice = null;
        Iterator<Invoice> iterInvoice = invoices.iterator();
        int itemCounter = 0;
        int invoiceCounter = 0;
        

        while (iterInvoice.hasNext()) {
            invoice = iterInvoice.next();
            buHaSS = Globals.OK;

            if (endInvoice > 0 && endInvoice < invoice.getId()) {
                break;
            }
            //bei Aktivierungsfiles nichts buchen
            if (invoice.getPaymentType() != null && invoice.getPaymentType().getId().equals(42)) {
                continue;
            }
            //bei Lieferschein nichts buchen
            if (invoice.getPaymentType() != null && invoice.getPaymentType().getId().equals(12)) {
                continue;
            }
            // Butlers nicht buchen
//            if (invoice.getPartnerID() == 110731 || invoice.getPartnerID() == 110738 || invoice.getPartnerID() == 175173
//                    || invoice.getPartnerID() == 175170 || invoice.getPartnerID() == 98171 || invoice.getPartnerID() == 98170) {
//                configurationController.getLoggingHandler().getLogger(Globals.LOGGINGAR).log(Level.INFO, "Rechnung ist Butlers Rechnung ");
//                continue;
//            }

            invoiceCounter++;
            String countryBuhaCode = "0";

            Partner partner = invoice.getPartner();
            NukeMrcommerceSaleschannelinvoice salesChannelInvoice = configurationController.getSaleschannelinvoiceJpaController().findNukeMrcommerceSaleschannelinvoice(invoice.getId());


            configurationController.getLoggingHandler().getLogger(Globals.LOGGINGAR).log(Level.INFO, "Rechnung: " + invoice.getInvoiceNumber() + " / " + invoice.getId());

            int zeilenvorher = configurationController.getCSVController().getWriterAR().getRecordCount();


            Collection<InvoiceItem> items = invoice.getInvoiceItemCollection();

            for (InvoiceItem item : items) {
                // Hack für 100% Rabatt, Systemfehler und Marketingaktion
                if (item.getMrcommerceItemID().intValue() == 95041 || item.getMrcommerceItemID().intValue() == 95044) {
                    invoice.setCategory(Integer.toString(Globals.ORDERTYPE100RABATT));
                }
                if (item.getMrcommerceItemID().intValue() == 95043) {
                    invoice.setCategory(Integer.toString(Globals.ORDERTYPEMARKETINGAKTION));
                }
            }

            Iterator<InvoiceItem> iterItem = items.iterator();

            double invoicePaymentSum = 0.0;
            double splitsum = 0.0;
            BuhaFilter filterPayment = null;
            
            //Splitbuchung bei Ablauf oder Wiederöffnung
            int category = Integer.parseInt(invoice.getCategory() != null ? invoice.getCategory() : "0");
            if (category == Globals.ORDERTYPEABLAUF || category == Globals.ORDERTYPEWIEDER) {
                configurationController.getCSVController().getWriterAR().setDeferred(true);
            }
            

            while (iterItem.hasNext()) {
                itemCounter++;
                InvoiceItem invoiceItem = iterItem.next();
                
                if (invoiceItem.getMrcommerceItemID() == 30414) {
                    System.out.println("Joker!");
                }

                //Filter Payment - bei Online Payment Kundenkonto entlasten
                //BuhaFilter filterPaymentTemp = getFilterARPayment(invoiceItem, invoice);
                //if (filterPaymentTemp != null) {
                    invoicePaymentSum = invoicePaymentSum + invoiceItem.getAmounteur().doubleValue();
                //    filterPayment = filterPaymentTemp;
                //}

//                if (Integer.parseInt(invoice.getCategory()) == Globals.ORDERTYPEABLAUF || Integer.parseInt(invoice.getCategory()) == Globals.ORDERTYPEWIEDER) {
                  category = Integer.parseInt(invoice.getCategory() != null ? invoice.getCategory() : "0");  
                  if (category == Globals.ORDERTYPEABLAUF || category == Globals.ORDERTYPEWIEDER) {
                    // spezielles Handling bei Ablauf und Wiederöffnung
                    if (invoiceItem.getInvoiceItemHistory() != null && invoiceItem.getInvoiceItemHistory().getInvoiceItemOld() != null) {
                        InvoiceItem invoiceItemRef = invoiceItem.getInvoiceItemHistory().getInvoiceItemOld();
                        //Invoice invoiceRef = invoiceItemRef.getInvoiceID();
                        invoiceItem.setUniqueNumber(invoiceItemRef.getUniqueNumber());
                        countryBuhaCode = getFilterAR(invoiceItem, invoice).getBuhaBooking().getBuhaCountry();
                        splitsum+= processARLine(configurationController, invoice, invoiceItemRef, salesChannelInvoice, filterPayment, keyCaching);

                        if (category == Globals.ORDERTYPEWIEDER) {
                            splitsum+= processARLine(configurationController, invoice, invoiceItem, salesChannelInvoice, filterPayment, keyCaching);
                        }
                    } else {
                        configurationController.getLoggingHandler().getLogger(Globals.LOGGINGAR).log(Level.SEVERE, "Fehler: bei Ablauf kein Vorgänger InvoiceItem gefunden zu InvoiceItem " + invoiceItem.getId());
                    }
                } else {
                    // normaler Ablauf
                    splitsum+= processARLine(configurationController, invoice, invoiceItem, salesChannelInvoice, filterPayment, keyCaching);
                }

            }
            filterPayment = getFilterARPayment(invoicePaymentSum, invoice);
            if (filterPayment != null && (invoicePaymentSum > 0.001 || invoicePaymentSum < -0.001)) {
                splitsum+= invoicePaymentSum;
                writeARSatz0Payment(configurationController, invoice, invoicePaymentSum, filterPayment, salesChannelInvoice, keyCaching);
            }
            
            if (configurationController.getCSVController().getWriterAR().isDeferred()) {
                //Summe wird um den Paymentbetrag korrigiert
                writerARSplitFirstLine(configurationController, invoice, countryBuhaCode, splitsum, "O");
                configurationController.getCSVController().getWriterAR().deferredFlush();
            }

            //Stammdaten nur schreiben wenn Zeilen hinzugefügt wurden
            int zeilennachher = configurationController.getCSVController().getWriterAR().getRecordCount();
            if (zeilennachher - zeilenvorher > 0) {
                
                        if (configurationController.getCooperationInvoiceDetailsJpaController().findByNukeCooperationinvoiceInvoice(invoice.getId()).size() > 0) {
                            Integer coop = configurationController.getCooperationInvoiceDetailsJpaController().findByNukeCooperationinvoiceInvoice(invoice.getId()).get(0).getNukeCooperationInvoiceDetailsPK().getNukeCooperationinvoiceCooperation();
                            if (configurationController.getCooperationDeliverynoteDetailsJpaController().findVNukeCooperationDeliverynoteDetails(coop) != null) {
                                VNukeCooperationDeliverynoteDetails coopPartner = configurationController.getCooperationDeliverynoteDetailsJpaController().findVNukeCooperationDeliverynoteDetails(coop);                
                                writeDBSatzLS(configurationController, partner, coopPartner);
                                System.out.println("Partner mit CoopPartner Daten in Debitor eintragen CoopNr.: " + coopPartner.getNukeCooperationId());
                            } else {
                                writeDBSatz(configurationController, partner);
                            }
                        } else {
                            writeDBSatz(configurationController, partner);
                        }


                
                
            }

            configurationController.getInvoiceJpaController().updateInvoiceBuha_ss_status(invoice,buHaSS);
          
            
        }
        //System.out.println("Invoices: " + invoiceCounter + "    Items: " + itemCounter);

        if (invoice != null) {
            return invoice.getId();
        }

        configurationController.getLoggingHandler().getLogger(Globals.LOGGINGAR).log(Level.INFO, "Keine Buchungen gefunden");
        throw new NotFoundException("Keine Buchungen gefunden");

    }
    
    public int processDebitor(ConfigurationController configurationController, int partnerNr, int startValue, int maxResults, boolean all, LoggingHandler lh) throws IOException {

        Partner partner = null;

        if (all) {
//            int startCustomer = 0;
//            int startInvoice = startValue;
//            int resultLength = 0;
//            HashMap debitors = new HashMap();
//            //configurationController.getPartnerJpaController().findPartner(partnerNr)
//            InvoiceJpaController invoiceJpaController = new InvoiceJpaController();
//
//            do {
//
//                List invoices = invoiceJpaController.findInvoiceEntitiesNextPartner(maxResults, startInvoice);
//                resultLength = invoices.size();
//                for (Object result : invoices) {
//                    try {
//                        Integer invoiceId = (Integer) ((Object[]) result)[0];
//                        partner = (Partner) ((Object[]) result)[1];
//                        //partner = invoice.getPartner();
//                        if (partner != null && !debitors.containsKey(partner.getId())) {
//                            debitors.put(partner.getId(), partner);
//                            writeDBSatz(configurationController, partner);
//                        }
//                        //startInvoice = invoice.getId() + 1;
//                        startInvoice = invoiceId + 1;
//                    } catch (Throwable t) {
//                    }
//                }
//            } while (resultLength > 0);

        } else {
            
            partner = configurationController.getPartnerJpaController().findPartner(partnerNr);
            
//            InvoiceJpaController invoiceJpaController = new InvoiceJpaController();
//            //NukeMrcommerceItemsJpaController itemsJpaController = new NukeMrcommerceItemsJpaController();
//            //List<Invoice> organisers = invoiceJpaController.findInvoiceEntities(10, 144312);
//            List<Invoice> invoices = invoiceJpaController.findInvoiceEntities(maxResults, startValue);
//
//            Invoice invoice = null;
//            Iterator<Invoice> iterInvoice = invoices.iterator();
//            while (iterInvoice.hasNext()) {
//                invoice = iterInvoice.next();
//                partner = invoice.getPartner();
                if (partner == null) {
                    lh.getLogger(Globals.LOGGINGDB).log(Level.SEVERE, "Keinen Debitor gefunden mit Nummer: " + partnerNr);
                } else {
                    writeDBSatz(configurationController, partner);
                }
//            }
        }

//        writer.flush();
//        writer.close();
        if (partner != null) {
            return partner.getId();
        }
        return 0;
    }
    
    public String testBuchungslogikAR(int invoiceNumber) {
        
        List<Invoice> invoices = configurationController.getInvoiceJpaController().findInvoiceByNumber(invoiceNumber);

        Invoice invoice = null;
        Iterator<Invoice> iterInvoice = invoices.iterator();
        int itemCounter = 0;
        int invoiceCounter = 0;
        StringBuffer buffer = new StringBuffer();

//        if (writerAR.getRecordCount() == 0)
//            writeBuchungFirst(writerAR);
//        if (writerDB.getRecordCount() == 0)
//            writeDEBSatzFirst(writerDB);

        while (iterInvoice.hasNext()) {
            invoice = iterInvoice.next();


            //bei Lieferschein nichts buchen
            if (invoice.getPaymentType() != null && invoice.getPaymentType().getId().equals(12)) {
                return "";
            }
            // Butlers nicht buchen
//            if (invoice.getPartnerID() == 110731 || invoice.getPartnerID() == 110738 || invoice.getPartnerID() == 175173
//                    || invoice.getPartnerID() == 175170 || invoice.getPartnerID() == 98171 || invoice.getPartnerID() == 98170) {
//                return "Butlers Rechnung";
//            }

            Collection<InvoiceItem> items = invoice.getInvoiceItemCollection();

            // Hack für 100% Rabatt, Systemfehler und Marketingaktion
            for (InvoiceItem item : items) {
                if (item.getMrcommerceItemID().intValue() == 95041 || item.getMrcommerceItemID().intValue() == 95044) {
                    invoice.setCategory(Integer.toString(Globals.ORDERTYPE100RABATT));
                }
                if (item.getMrcommerceItemID().intValue() == 95043) {
                    invoice.setCategory(Integer.toString(Globals.ORDERTYPEMARKETINGAKTION));
                }
            }
            

            for (InvoiceItem item : items) {
                BuhaFilter filter = getFilterAR(item, invoice);
                if (filter != null)
                    buffer.append("Item: "+ item.getServiceName() + "    Buchungsregel: " + filter.getBuhaBooking().getSortId() + "\n");
                else
                    buffer.append("Item: "+ item.getServiceName() + "    Buchungsregel: --- \n");
            }   
        }
        return buffer.toString();

    }
    
    private BuhaFilter getFilterAR(InvoiceItem invoiceItem, Invoice invoice) {
        BuhaFilter filter = null;
        List<BuhaFilter> filters = configurationController.getFiltersAR();
        //System.out.println("Filters: " + filters.size());
        Iterator<BuhaFilter> iterFilter = filters.iterator();
        while (iterFilter.hasNext()) {
            filter = iterFilter.next();
//            if (filter.getId() == 1535) {
//                System.out.println("Filter: " + filter.getId() + " wird ausgewertet");
//            }
//            
            if (filter.getId() == 1535  && invoiceItem.getMrcommerceItemID() == 95057) {
                System.out.println("Filter: " + filter.getId() + " wird ausgewertet auf " + invoiceItem.getMrcommerceItemID());
            }
            
            try {
                boolean test1 = checkARPaymentType(filter, invoice);

                if (    checkARItemID(filter, invoiceItem)
                     && checkARGutscheinFilter(filter, invoiceItem)
                     && checkARCountryFilter(filter, invoiceItem, invoice)
                     && (filter.getCheckTaxrate() ? filter.getTaxrate() == invoiceItem.getTaxRate() : true)
                     && checkARPaymentType(filter, invoice)
                     && checkARCategory(filter, invoice)
                     && checkARBillingType(filter, invoice)
                     && checkARBesorger(filter, invoiceItem)
                     && checkARSignFilter(filter, invoiceItem.getAmounteur().doubleValue())
                     && checkARSalesChannelFilter(filter, invoice)
                     && checkARReverseChargeFilter(filter, invoice, invoiceItem.getMrcommerceItemID())
                    ) {
                    System.out.println("Filter: " + filter.getId() + " positiv");
                    break;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            filter = null;
        }
        return filter;

    }

   protected boolean checkARGutscheinFilter(BuhaFilter filter, InvoiceItem invoiceItem) {
        //Vorprüfungen
        if (filter.getGutscheinfilter() == null) {
            return true;
        }
        if (filter.getGutscheinfilter().trim().equals(Globals.EMPTYSTRING)) {
            return true;
        }

        if (invoiceItem.getUniqueNumber() == null) {
            return false;
        }
        if (invoiceItem.getUniqueNumber().trim().length() < filter.getGutscheinfilter().trim().length()) {
            return false;
        }

        //eigentliche Prüfung
        if (filter.getGutscheinfilter().equals(invoiceItem.getUniqueNumber().substring(0, filter.getGutscheinfilter().length()))) {
            return true;
        }

        return false;
    }
   
   protected boolean checkRabattGutscheinFilter(BuhaRabatt rabatt, InvoiceItem invoiceItem) {
        //Vorprüfungen
        if (rabatt.getGutscheinfilter() == null) {
            return true;
        }
        if (rabatt.getGutscheinfilter().trim().equals(Globals.EMPTYSTRING)) {
            return true;
        }

        if (invoiceItem.getUniqueNumber() == null) {
            return false;
        }
        if (invoiceItem.getUniqueNumber().trim().length() < rabatt.getGutscheinfilter().trim().length()) {
            return false;
        }

        //eigentliche Prüfung
        if (rabatt.getGutscheinfilter().equals(invoiceItem.getUniqueNumber().substring(0, rabatt.getGutscheinfilter().length()))) {
            return true;
        }

        return false;
    }   
   

    protected boolean checkARCountryFilter(BuhaFilter filter, InvoiceItem invoiceItem, Invoice invoice) {
        //Vorprüfungen




        if (filter.getCountry() == null) {
            return true;
        }
        if (filter.getCountry().trim().equals(Globals.EMPTYSTRING)) {
            return true;
        }

        
        //grauslicher Hack für die semiaktiven Bucher
        if (invoiceItem != null && invoiceItem.getTaxRate() == 19.0) {
            if (filter.getCountry().trim().equals("DEU")) {
                return true;
            }
        }
  
        
        if (invoice.getPartner().getAddress().getISOState() == null) {
            return false;
        }

        if (invoice.getPartner().getAddress().getISOState().equals(Globals.EMPTYSTRING)) {
            return false;
        }
        
        if (configurationController.getCooperationInvoiceDetailsJpaController().findByNukeCooperationinvoiceInvoice(invoice.getId()).size() > 0) {
            Integer coop = configurationController.getCooperationInvoiceDetailsJpaController().findByNukeCooperationinvoiceInvoice(invoice.getId()).get(0).getNukeCooperationInvoiceDetailsPK().getNukeCooperationinvoiceCooperation();
            if (configurationController.getCooperationDeliverynoteDetailsJpaController().findVNukeCooperationDeliverynoteDetails(coop) != null) {
                if (filter.getCountry().equals(configurationController.getCooperationDeliverynoteDetailsJpaController().findVNukeCooperationDeliverynoteDetails(coop).getNukeCooperationuserIsostate())) {
                    System.out.println("Filter : " + filter.getId() + " CoopID: " + coop + " CoopCountrymatch");
                    return true;
                }                
            }

        }
        
        if (filter.getCountry().equals(invoice.getPartner().getAddress().getISOState())) {
            return true;
        }

        return false;
    }
    
    protected boolean checkRabattCountryFilter(BuhaRabatt rabatt, InvoiceItem invoiceItem, Invoice invoice) {
        //Vorprüfungen




        if (rabatt.getWebLand() == null) {
            return true;
        }
        if (rabatt.getWebLand().trim().equals(Globals.EMPTYSTRING)) {
            return true;
        }


        //grauslicher Hack für die semiaktiven Bucher
        if (invoiceItem.getTaxRate() == 19.0) {
            if (rabatt.getWebLand().trim().equals("DEU")) {
                return true;
            }
        }


        if (invoice.getPartner().getAddress().getISOState() == null) {
            return false;
        }

        if (invoice.getPartner().getAddress().getISOState().equals(Globals.EMPTYSTRING)) {
            return false;
        }

        if (rabatt.getWebLand().equals(invoice.getPartner().getAddress().getISOState())) {
            return true;
        }

        return false;
    }    


    protected BuhaFilter getFilterARPayment(double sum, Invoice invoice) {
        BuhaFilter filter = null;
        List<BuhaFilter> filters = configurationController.getFiltersPayment();
        //System.out.println("Filters: " + filters.size());
        Iterator<BuhaFilter> iterFilter = filters.iterator();
        while (iterFilter.hasNext()) {
            filter = iterFilter.next();

            try {
                if ( //   checkARItemID(filter, invoiceItem)
                     //&& checkARGutscheinFilter(filter, invoiceItem)
                        checkARCountryFilter(filter, null, invoice)
                     //&& (filter.getCheckTaxrate() ? filter.getTaxrate() == invoiceItem.getTaxRate() : true)
                     && checkARPaymentType(filter, invoice)
                     && checkARCategory(filter, invoice)
                     && checkARBillingType(filter, invoice)
                     //&& checkARBesorger(filter, invoiceItem)
                     && checkARSignFilter(filter, sum)
                                           
//                        filter.getItemFrom() <= invoiceItem.getMrcommerceItemID()
//                        && (filter.getItemTo() >= invoiceItem.getMrcommerceItemID())
//                        && checkARPaymentType(filter, invoice)
//                        && checkARCategory(filter, invoice)
//                        && checkARSignFilter(filter, invoiceItem)
                    ) {
                    break;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            filter = null;
        }
        return filter;

    }   
    
   protected boolean checkARSalesChannelFilter(BuhaFilter filter, Invoice invoice) {
        //Vorprüfungen
        if (filter.getSaleschannel_Id() == null) {
            return true;
        }
        //eigentliche Prüfung
        if (filter.getSaleschannel_Id() == configurationController.getSaleschannelinvoiceJpaController().findNukeMrcommerceSaleschannelinvoice(invoice.getId()).getNukeSaleschannelinvoiceChannel()) {
            return true;
        }

        return false;
    }
    
    
    private double processARLine(ConfigurationController configurationController, Invoice invoice, InvoiceItem invoiceItem, NukeMrcommerceSaleschannelinvoice salesChannelInvoice, BuhaFilter filterPayment, boolean keyCaching) throws IOException {
        NukeMrcommerceSaleschannelinvoice itemSalesChannelInvoice = null;
        double splitsum = 0.0;
        //Filter für Item Buchungssätze
        BuhaFilter filter = getFilterAR(invoiceItem, invoice);

        //Filter für auf Items verteilte Rabatte
        BuhaFilter filterRabatt = getFilterARRabatt(invoiceItem, invoice);

        //Filter Kostenträger
        BuhaFilter filterKoTraeger = getFilterARKoTraeger(invoiceItem, invoice);

        if (salesChannelInvoice == null && invoiceItem.getUniqueNumber() != null && invoiceItem.getUniqueNumber().length() > 0) {
            InvoiceItem firstInvoiceItem = configurationController.getInvoiceItemJpaController().findFirstInvoiceItemByUniqueNumber(invoiceItem.getUniqueNumber());
            itemSalesChannelInvoice = configurationController.getSaleschannelinvoiceJpaController().findNukeMrcommerceSaleschannelinvoice(firstInvoiceItem.getInvoiceID().getId());
        } else {
            itemSalesChannelInvoice = salesChannelInvoice;
        }


        if (filter != null && invoiceItem.getAmounteur().compareTo(BigDecimal.ZERO) != 0) {
            BuhaBooking booking = filter.getBuhaBooking();

            if (booking.getKonto() > 0 || booking.getZwischenkonto() > 0 || booking.getGegenkonto() > 0) {
                NukeMrcommerceItems item = configurationController.getItemsJpaController().findNukeMrcommerceItems(invoiceItem.getMrcommerceItemID());

                splitsum+= writeARSatz0(configurationController, invoice, invoiceItem, booking, item, itemSalesChannelInvoice, filterKoTraeger, keyCaching);

                if (filterRabatt != null) {
                    //configurationController.getLoggingHandler().getLogger(Globals.LOGGINGAR).log(Level.INFO, "Rabatt processing in processARLine  ...");
                    NukeMrcommerceItems itemRabatt = configurationController.getItemsJpaController().findNukeMrcommerceItems(invoiceItem.getCorrespondingItemRabattID().getMrcommerceItemID());
                    splitsum+= writeARSatz0Rabatt(configurationController, invoice, invoiceItem, booking, itemRabatt, filterRabatt, itemSalesChannelInvoice, filterKoTraeger, keyCaching);
                }
            }
        } else if (invoiceItem.getAmounteur().compareTo(BigDecimal.ZERO) != 0) {
            configurationController.getLoggingHandler().getLogger(Globals.LOGGINGAR).log(Level.SEVERE, "Error: keinen Filter gefunden - Item " + invoiceItem.getMrcommerceItemID() + " Gutschein: " + invoiceItem.getUniqueNumber() + " in Rechnung " + invoice.getInvoiceNumber() + "/" + invoiceItem.getId() + "/" + invoice.getPartner().getAddress().getISOState() + " (" + invoice.getId() + " Besorger: " + invoiceItem.getBesorger() + ") nicht buchbar!");
        }
        return splitsum;
    }
           
    protected BuhaFilter getFilterARRabatt(InvoiceItem invoiceItem, Invoice invoice) {

        if (invoiceItem.getCorrespondingItemRabattAmount() == null || invoiceItem.getCorrespondingItemRabattAmount().doubleValue() == 0.0) {
            return null;
        }

        InvoiceItem itemPrev = invoiceItem.getCorrespondingItemRabattID();
        String state = invoice.getPartner().getAddress().getISOState();
        if (state == null || state.trim().length() == 0) {
            state = Globals.DEFAULTSTATE;
        }
        
        BuhaFilter rabatt = null;
        Iterator<BuhaFilter>  iterRabatte = configurationController.getFiltersARRabatte().iterator();
        while (iterRabatte.hasNext()) {
            rabatt = iterRabatte.next();
            //System.out.println("Rabatt Nr: " + rabatt.getId());
            
//            if (rabatt.getId() == 1369)
//                System.out.println("Rabatt");

            if (    
                    //rabatt.getItem() == 0 || rabatt.getItem() == itemPrev.getMrcommerceItemID().intValue()
                    checkARItemID(rabatt, itemPrev) &&
                    checkARGutscheinFilter(rabatt, invoiceItem) &&
                    checkARCountryFilter(rabatt, itemPrev, invoice) &&
                    checkARCategory(rabatt, invoice) &&
                    checkARSignFilter(rabatt, invoiceItem.getAmounteur().doubleValue())
                    //&& checkRabattCountryFilter(rabatt, invoiceItem, invoice)
                    //&& checkRabattGutscheinFilter(rabatt, invoiceItem)
                    //&& (rabatt.getCategory() == 0 || rabatt.getCategory() == Integer.parseInt(invoice.getCategory())
                ) {
                System.out.println("Rabatt Filter: " + rabatt.getId() + " positiv");
                break;
            }
            rabatt = null;
        }
        if (rabatt == null || ( rabatt.getBuhaBooking().getKonto() == 0 && rabatt.getBuhaBooking().getZwischenkonto() == 0 && rabatt.getBuhaBooking().getGegenkonto() == 0 ) )
            return null;
        
        return rabatt;
    }

    protected BuhaFilter getFilterARKoTraeger(InvoiceItem invoiceItem, Invoice invoice) {
        BuhaFilter filter = null;
        List<BuhaFilter> filters = configurationController.getFiltersKoTraeger();

        Iterator<BuhaFilter> iterFilter = filters.iterator();
        while (iterFilter.hasNext()) {
            filter = iterFilter.next();

            try {
                if (filter.getItemFrom() <= invoiceItem.getMrcommerceItemID()
                        && (filter.getItemTo() >= invoiceItem.getMrcommerceItemID())
                        && checkARCountryFilter(filter, invoiceItem, invoice)
                        && (filter.getCategory().trim().length() == 0 || filter.getCategory().trim().equals(invoice.getCategory().trim()))) {
                    break;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }

            filter = null;
        }
        return filter;

    }

    
    protected BuhaKostl getKostl(NukeMrcommerceSaleschannelinvoice salesChannelInvoice, String country) {
        Iterator<BuhaKostl> iterKostl = configurationController.getKostl().iterator();
        BuhaKostl kostl = null;
        int salesChannel = 0;
        if (salesChannelInvoice != null) {
            salesChannel = salesChannelInvoice.getNukeSaleschannelinvoiceChannel();
        }
        if (salesChannel == 0) {
            salesChannel = 18;
        }
        while (iterKostl.hasNext()) {
            kostl = iterKostl.next();
            if (kostl.getChannel() == salesChannel
                    && (kostl.getCountry().equals(country) || kostl.getCountry().equals(Globals.EMPTYSTRING))) {
                break;
            }
            kostl = null;
        }
        if (kostl == null) {
            country = "AUT";
            while (iterKostl.hasNext()) {
                kostl = iterKostl.next();
                if (kostl.getChannel() == salesChannel
                        && kostl.getCountry().equals(country)) {
                    break;
                }
                kostl = null;
            }
        }

        return kostl;
    }

    private boolean checkARSignFilter(BuhaFilter filter, double number) {
        
        if (filter.getSign() == '+' && number < 0.0 )
            return false;
            
        if ( filter.getSign() == '-' && number > 0.0 )
            return false;
        
        return true;   
    }

    private boolean checkARPaymentType(BuhaFilter filter, Invoice invoice) {
        if (filter.getPaymentType() == null)
            return true;
        if (filter.getPaymentType().trim().equals(Globals.EMPTYSTRING))
            return true;
        
        int filPT = Integer.parseInt(filter.getPaymentType());
        int invPT = invoice.getPaymentType().getId().intValue();
        if (filPT == invPT)
            return true;

        return false;
    }

    private boolean checkARItemID(BuhaFilter filter, InvoiceItem invoiceItem) {
        if ((filter.getItemFrom() <= invoiceItem.getMrcommerceItemID()) && (filter.getItemTo() >= invoiceItem.getMrcommerceItemID()))
                return true;
        return false;
    }

    private boolean checkARCategory(BuhaFilter filter, Invoice invoice) {
        if (filter.getCategory() == null)
            return true;
        if (filter.getCategory().trim().equals(Globals.EMPTYSTRING))
            return true;
        if (filter.getCategory().trim().equals(invoice.getCategory().trim()))
            return true;
        return false;
    }

    private boolean checkARBillingType(BuhaFilter filter, Invoice invoice) {
        if (filter.getBillingType() == null)
            return true;
        if (filter.getBillingType().trim().equals(Globals.EMPTYSTRING))
            return true;
        if (filter.getBillingType().trim().equals(invoice.getBillingType().trim()))
            return true;
        return false;
    }

    private boolean checkARBesorger(BuhaFilter filter, InvoiceItem invoiceItem) {
        if (filter.getBuhaBooking().getForcevermittler())
            return true;
        if (filter.getBesorger() == invoiceItem.getBesorger())
            return true;
        return false;
    }
    
        private boolean checkARReverseChargeFilter(BuhaFilter filter, Invoice invoice, Integer item) {
          
            if (filter.getId() == 1509 || filter.getId() == 1523 || filter.getId() == 1553) {
                System.out.println("Filter: " + filter.getId() + " wird bzg. Reverse Charge ausgewertet ausgewertet");
            }
            if (configurationController.getCooperationInvoiceDetailsJpaController().findByNukeCooperationinvoiceInvoice(invoice.getId()).size() > 0 && Globals.REVERSECHARGEITEMS.contains(item.intValue())) {
                Integer coop = configurationController.getCooperationInvoiceDetailsJpaController().findByNukeCooperationinvoiceInvoice(invoice.getId()).get(0).getNukeCooperationInvoiceDetailsPK().getNukeCooperationinvoiceCooperation();
                if (configurationController.getCooperationDeliverynoteDetailsJpaController().findVNukeCooperationDeliverynoteDetails(coop) != null) {
                    short revChargeFlag = configurationController.getCooperationDeliverynoteDetailsJpaController().findVNukeCooperationDeliverynoteDetails(coop).getNukeCooperationProvisionIsBrutto();
                    System.out.println("Filter : " + filter.getId() + " CoopID: " + coop + " Reverse Charge: " + revChargeFlag);
                    if (revChargeFlag != Globals.REVERSECHARGE) {
                        return true;
                    } else if (filter.getReverse_charge() == revChargeFlag) {
                        return true;
                    }
                }
            } else {
                return true;
            }
            return false;
        }
    
}
