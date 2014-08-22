/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.test;

import at.jollydays.booking.Globals;
import at.jollydays.booking.bo.BuhaArea;
import at.jollydays.booking.bo.BuhaBooking;
import at.jollydays.booking.bo.BuhaFilter;
import at.jollydays.booking.bo.BuhaProperties;
import at.jollydays.booking.bo.BuhaRabatt;
import at.jollydays.booking.bo.Invoice;
import at.jollydays.booking.bo.InvoiceItem;
import at.jollydays.booking.bo.InvoiceItemHistory;
import at.jollydays.booking.bo.NukeMrcommerceAddress;
import at.jollydays.booking.bo.NukeMrcommerceArrangementCity;
import at.jollydays.booking.bo.NukeMrcommerceOrganiser;
import at.jollydays.booking.bo.OrganiserInvoice;
import at.jollydays.booking.bo.OrganiserInvoiceItem;
import at.jollydays.booking.control.BookingController;
import at.jollydays.booking.control.ConfigurationController;
import at.jollydays.booking.control.LoggingHandler;
import at.jollydays.booking.db.BuhaPropertiesJpaController;
import at.jollydays.booking.db.BuhaRabattJpaController;
import at.jollydays.booking.db.InvoiceItemJpaController;
import at.jollydays.booking.db.InvoiceJpaController;
import at.jollydays.booking.db.NukeMrcommerceArrangementCityJpaController;
import at.jollydays.booking.db.NukeMrcommerceOrganiserJpaController;
import at.jollydays.booking.db.OrganiserInvoiceJpaController;
import at.jollydays.booking.db.exceptions.NonexistentEntityException;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
 * @author Gunter Reinitzer
 */
public class TestKonfiguration {

    public static void main(String[] args) throws IOException {
        migratePaymentToBookingAndFilter();
//          selectOrdertypePaymenttype();
//        loggingHandler();

//        invoiceItemHistory();

//        zahlungseingang();

//        MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
//        double netto = 99.989;
//        System.out.println(roundScale2(netto));
//        System.out.println(netto);
//        BigDecimal big = new BigDecimal(netto);
//          System.out.println(big);
//        String value = big.round(mc).toPlainString();
//        System.out.println(value);

        //BuhaTestJpaController testJpaController = new BuhaTestJpaController();
        //testJpaController.findBuhaTestEntities();
        //BuhaKostlJpaController kostlJpaController = new BuhaKostlJpaController();
        //List kostlList = kostlJpaController.findBuhaKostlEntities();
        //kostlList = null;
        //getRabatte();

//        checkSettings();

//        Handler fh;
//        fh = new FileHandler("test.log");
//        Logger.getLogger("AR").addHandler(fh);
//        Logger.getLogger("AR").setLevel(Level.ALL);
//        Logger.getLogger("AR").log(Level.SEVERE, "test");
        
        
        //testReadByNumber();

        //testLogging();

        //testArrangement();

//        BigDecimal bd = BigDecimal.valueOf(10000.555);
//        double db = (bd.setScale(2, RoundingMode.HALF_UP)).doubleValue();
//        NumberFormat format = NumberFormat.getNumberInstance();
//        System.out.println(format.format(db));
//        format.setGroupingUsed(false);
//        System.out.println(format.format(db));


//        String recipients = "gunter@reinitzer.com;buchhaltung@jollydays.at;it@jollydays.at";
//        String[] recipientArr = recipients.split(";");
//        for (int i=0;i<recipientArr.length;i++) {
//            System.out.println(recipientArr[i]);
//        }
//
//        NukeMrcommerceItemsJpaController itemsJpaController = new NukeMrcommerceItemsJpaController();
//        NukeMrcommerceItems item = itemsJpaController.findNukeMrcommerceItems(new Integer(95008));
//        String desc = item.getNukeItemDescription();

//        getArrCityById();


    }

    public static void getAR() {

        InvoiceJpaController invoiceJpaController = new InvoiceJpaController();
        List<Invoice> invoices = invoiceJpaController.findInvoiceEntities(10, 144312);
        System.out.println(invoices.size());

        ConfigurationController configurationController = new ConfigurationController();
        File csvFile = new File("AR.csv");
        CsvWriter writer = null;
        try {
            //writer = new PrintWriter(new BufferedWriter(new FileWriter(csvFile)));
            writer = new CsvWriter(csvFile.getCanonicalPath());
            writer.setDelimiter(';');
            // http://www.csvreader.com/java_csv_samples.php
            // http://javacsv.sourceforge.net/

        } catch (IOException ex) {
            Logger.getLogger("Anwendung").log(Level.SEVERE, null, ex);
            return;
        }


        Iterator<Invoice> iterPartner = invoices.iterator();
        while (iterPartner.hasNext()) {

            Invoice partner = iterPartner.next();
            System.out.println(partner.getCategory());


            Collection<InvoiceItem> address = partner.getInvoiceItemCollection();
            Iterator<InvoiceItem> iterItem = address.iterator();
            while (iterItem.hasNext()) {
                InvoiceItem item = iterItem.next();
                System.out.println("Rechnung: " + partner.getInvoiceNumber() + " Item: " + item.getMrcommerceItemID() + " Gutschein: " + item.getUniqueNumber());

                BuhaFilter filter = null;
                List<BuhaFilter> filters = configurationController.getFiltersAR();
                Iterator<BuhaFilter> iterFilter = filters.iterator();
                while (iterFilter.hasNext()) {
                    filter = iterFilter.next();
                    if (filter.getItemFrom() <= item.getMrcommerceItemID()
                            && filter.getItemTo() >= item.getMrcommerceItemID()
                            && (filter.getGutscheinfilter() == null
                            || filter.getGutscheinfilter().equals(item.getUniqueNumber().substring(0, filter.getGutscheinfilter().length())))) {
                        break;
                    }
                    filter = null;
                }

                if (filter != null) {
                    try {
                        BuhaBooking booking = filter.getBuhaBooking();
                        // TODO: CSV schreiben, wir haben invoice, item, booking, filter, that's it
                        writer.write("");
                        writer.write("");
                        writer.write("");
                        writer.write("");
                        writer.endRecord();
                    } catch (IOException ex) {
                        Logger.getLogger(TestKonfiguration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    // TODO: Errorlog checken
                    Logger.getLogger("Buchung").log(Level.SEVERE, "keinen Filter gefunden - Item " + item.getId() + " in Rechnung " + partner.getInvoiceNumber() + " nicht buchbar!" );
                }

            }

        }
        writer.flush();
        writer.close();



        /*   System.out.println("eins");
        BuhaAreaJpaController buhaAreaJpaController = new BuhaAreaJpaController();

        System.out.println("zwei");
        BuhaArea area = buhaAreaJpaController.findBuhaArea(new Integer(1));
        System.out.println("drei");
        Collection<BuhaBooking> buhaBookings = area.getBuhaBookingCollection();
        System.out.println("vier");
        Iterator<BuhaBooking> iterInvoices = buhaBookings.iterator();
        System.out.println("fünf");
        while (iterInvoices.hasNext()) {
        System.out.println("sechs");
        BuhaBooking booking = iterInvoices.next();
        System.out.println(booking.getWebCountry());
        System.out.println(booking.getBuhaCountry());

        //BuhaBookingJpaController buhaBookingJpaController = new BuhaBookingJpaController();
        EntityManager em = buhaAreaJpaController.getEntityManager();
        em.getTransaction().begin();
        booking.setBuhaCountry("AT2");

        em.merge(booking);
        //em.refresh(booking);
        //em.remove(booking);
        //em.persist(booking);
        em.getTransaction().commit();
        em.close();
        }

         */

    }

    private static void invoiceItemHistory() {
        InvoiceItemJpaController invoiceItemJpaController = new InvoiceItemJpaController();
         InvoiceItem item = invoiceItemJpaController.findInvoiceItem(567591);
         InvoiceItemHistory hist = item.getInvoiceItemHistory();

        hist = null;

    }

    private static void getArrCityById() {

        NukeMrcommerceArrangementCityJpaController arrangementCityJpaController = new NukeMrcommerceArrangementCityJpaController();
        NukeMrcommerceArrangementCity arrangementCity = arrangementCityJpaController.findNukeMrcommerceArrangementCity(1750);

        NukeMrcommerceAddress adress = arrangementCity.getNukeMrcommerceAddress();
        System.out.println(adress.getDescription());


    }

    private static void loggingHandler() {
        try {

            //cSVController = new CSVController();

            ConfigurationController configurationController = new ConfigurationController();
            BookingController bookingController = new BookingController(configurationController);

            File logDir = getDir(configurationController, Globals.DIRLOGFILES);
            String date = (new SimpleDateFormat("yyMMdd-hhmm")).format(new Date());
            LoggingHandler lh = new LoggingHandler(logDir, date);

            lh.getLogger(Globals.LOGGING, Globals.EMPTYSTRING).log(Level.INFO, "AR processing started - last Number before: " + 1);
            lh.getLogger(Globals.LOGGING, Globals.EMPTYSTRING).log(Level.INFO, "AR processing finished - last Number processed" + 2);

            lh.close();


//            LoggingHandler handler = new LoggingHandler(new File("../logtest"), "222");
//            handler.getLogger("TS").log(Level.INFO, "test info");
//            handler.getLogger("TS").log(Level.SEVERE, "test ERR");
//            handler.close();
        } catch (Exception ex) {
            Logger.getLogger(TestKonfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static File getDir(ConfigurationController configurationController, String pathPropName) {
        String dirStr = configurationController.getProperty(pathPropName).getValue();
        Calendar cal = Calendar.getInstance();
        String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }

        File rootDir = new File(dirStr);
        if (!rootDir.exists()) {
            rootDir.mkdir();
        }

        File dirYear = new File(dirStr, Integer.toString(cal.get(Calendar.YEAR)));
        if (!dirYear.exists()) {
            dirYear.mkdir();
        }
        File dirMonth = new File(dirYear, month);
        if (!dirMonth.exists()) {
            dirMonth.mkdir();
        }
        return dirMonth;
    }
//    public static void getPartner() {
//        PartnerJpaController partnerJpaController = new PartnerJpaController();
//        int maxResults = 10;
//        int firstResult = 10000;
//        List<Partner> invoices = partnerJpaController.findPartnerEntities(maxResults, firstResult);
//        System.out.println(invoices.size());
//
//        ConfigurationController configurationController = new ConfigurationController();
//        File csvFile = new File("AR.csv");
//        CsvWriter writer = null;
//        try {
//            //writer = new PrintWriter(new BufferedWriter(new FileWriter(csvFile)));
//            writer = new CsvWriter(csvFile.getCanonicalPath());
//            // http://www.csvreader.com/java_csv_samples.php
//            // http://javacsv.sourceforge.net/
//
//        } catch (IOException ex) {
//            Logger.getLogger("Anwendung").log(Level.SEVERE, null, ex);
//            return;
//        }
//
//
//
//
//        Iterator<Partner> iterPartner = invoices.iterator();
//        while (iterPartner.hasNext()) {
//            Partner partner = iterPartner.next();
//            System.out.println("Partner" + partner.getAccountNumber() + "   " + partner.getAccountOwner());
//            Address address = partner.getAddress();
//            try {
//                System.out.println("Adresse: " + address.getCity() + "   " + address.getLastname());
//                writer.write("");
//                writer.write("");
//                writer.write("");
//                writer.write("");
//                writer.endRecord();
//            } catch (IOException ex) {
//                Logger.getLogger(TestKonfiguration.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//        writer.flush();
//        writer.close();
//
//
//
//        /*   System.out.println("eins");
//        BuhaAreaJpaController buhaAreaJpaController = new BuhaAreaJpaController();
//
//        System.out.println("zwei");
//        BuhaArea area = buhaAreaJpaController.findBuhaArea(new Integer(1));
//        System.out.println("drei");
//        Collection<BuhaBooking> buhaBookings = area.getBuhaBookingCollection();
//        System.out.println("vier");
//        Iterator<BuhaBooking> iterInvoices = buhaBookings.iterator();
//        System.out.println("fünf");
//        while (iterInvoices.hasNext()) {
//        System.out.println("sechs");
//        BuhaBooking booking = iterInvoices.next();
//        System.out.println(booking.getWebCountry());
//        System.out.println(booking.getBuhaCountry());
//
//        //BuhaBookingJpaController buhaBookingJpaController = new BuhaBookingJpaController();
//        EntityManager em = buhaAreaJpaController.getEntityManager();
//        em.getTransaction().begin();
//        booking.setBuhaCountry("AT2");
//
//        em.merge(booking);
//        //em.refresh(booking);
//        //em.remove(booking);
//        //em.persist(booking);
//        em.getTransaction().commit();writer.setDelimiter(';');
//        em.close();
//        }
//
//         */
//
//    }

    private static void selectOrdertypePaymenttype() {
        InvoiceJpaController jpaController = new InvoiceJpaController();
        Calendar cFrom = Calendar.getInstance();
        Calendar cTo = Calendar.getInstance();
        cFrom.clear();
        cTo.clear();
        cFrom.set(2011, Calendar.AUGUST, 1);
        cTo.set(2011, Calendar.AUGUST, 31);

        List<Invoice> invoices = jpaController.findInvoiceEntitiesOrdertypeDate(4, 10, cFrom.getTime(), cTo.getTime(), 0);
        System.out.println(invoices.size());
    }

    private static void migratePaymentToBookingAndFilter() {
        ConfigurationController configurationController = new ConfigurationController();
        ArrayList<BuhaRabatt> filtersOld = configurationController.getRabatte();
        List<BuhaArea> areas = configurationController.getAreas();
        BuhaArea areaRabatt = null;
/*        
        EntityManager em = configurationController.getAreaJPAController().getEntityManager();
        try {
            em.getTransaction().begin();
            for (BuhaArea area : areas) {
                if (area.getId().intValue() == 6) {
                    areaRabatt = area;
                    for (BuhaRabatt filterOld : filtersOld) {
                        BuhaBooking booking = new BuhaBooking();
                        booking.setBuhaArea(areaRabatt);
                        configurationController.getAreaJPAController().getEntityManager().persist(booking);
                        areaRabatt.getBuhaBookingCollection().add(booking);
                        booking.setSortId(filterOld.getId());
                        booking.setKonto(filterOld.getKonto());
                        booking.setGegenkonto(filterOld.getGegenkonto());
                        booking.setZwischenkonto(filterOld.getZwischenkonto());
                        booking.setDescription(filterOld.getDescription());
                        booking.setBuhaCountry("" + filterOld.getBuhaLand());
                        booking.setBuhaFilterCollection(new IndirectList());
                    }
                    //configurationController.getAreaJPAController().edit(areaRabatt);


                }
            }
            configurationController.getAreaJPAController().findBuhaAreaEntities();
            BuhaBookingJpaController bookingJpaController = configurationController.getBookingJpaController();
            List<BuhaBooking> bookings = bookingJpaController.findBuhaBookingEntities();
            areaRabatt = null;

            for (BuhaArea area : areas) {
                if (area.getId().intValue() == 6) {
                    areaRabatt = area;
                    for (BuhaRabatt filterOld : filtersOld) {
                        for (BuhaBooking booking : bookings) {
                            if (booking.getSortId() == filterOld.getId()) {
                                BuhaFilter filter = new BuhaFilter();
                                filter.setBuhaBooking(booking);
                                configurationController.getBookingJpaController().getEntityManager().persist(booking);
                                booking.getBuhaFilterCollection().add(filter);
                                filter.setItemFrom(filterOld.getItem());
                                filter.setItemTo(filterOld.getItem());
                                filter.setCountry(filterOld.getWebLand());
                                filter.setGutscheinfilter(filterOld.getGutscheinfilter());
                                filter.setCategory("" + filterOld.getCategory());
                                //bookingJpaController.edit(booking);
                            }
                        }
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(TestKonfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
    }

    public void getVeranstalter() {

    }


    public static void getER() {
        OrganiserInvoiceJpaController organiserInvoiceJpaController = new OrganiserInvoiceJpaController();
        List<OrganiserInvoice> invoices = organiserInvoiceJpaController.findOrganiserInvoiceEntitiesNext(10, 5000);
        System.out.println(invoices.size());

        NukeMrcommerceOrganiserJpaController organiserJpaController = new NukeMrcommerceOrganiserJpaController();

        ConfigurationController configurationController = new ConfigurationController();
        File csvFile = new File("ER.csv");
        CsvWriter writer = null;
        try {
            //writer = new PrintWriter(new BufferedWriter(new FileWriter(csvFile)));
            writer = new CsvWriter(csvFile.getCanonicalPath());
            writer.setDelimiter(';');
            // http://www.csvreader.com/java_csv_samples.php
            // http://javacsv.sourceforge.net/

        } catch (IOException ex) {
            Logger.getLogger("Anwendung").log(Level.SEVERE, null, ex);
            return;
        }

        Iterator<OrganiserInvoice> iterInvoices = invoices.iterator();
        while (iterInvoices.hasNext()) {

            OrganiserInvoice invoice = iterInvoices.next();

            //System.out.println(invoice.getCategory());
            NukeMrcommerceOrganiser organiser = organiserJpaController.findNukeMrcommerceOrganiser(invoice.getOrganiserID());
            String country = configurationController.get3LetterCountryFromName(organiser.getNukeOrgState(), Logger.getLogger("Anwendung"), "");
            Iterator<OrganiserInvoiceItem> iterInvoiceItems = invoice.getInvoiceItemCollection().iterator();


            while (iterInvoiceItems.hasNext()) {
                OrganiserInvoiceItem item = iterInvoiceItems.next();
                System.out.println("Rechnung: " + invoice.getInvoiceNumber() + " Item: "  + " Gutschein: " );

                BuhaFilter filter = null;
                
                List<BuhaFilter> filters = configurationController.getFiltersER();
                Iterator<BuhaFilter> iterFilter = filters.iterator();
                while (iterFilter.hasNext()) {
                    filter = iterFilter.next();
                    if (filter.getCountry().equals(country)) {
                        break;
                    }
                    filter = null;
                }

                if (filter != null) {
                    try {
                        BuhaBooking booking = filter.getBuhaBooking();
                        // TODO: CSV schreiben, wir haben invoice, item, booking, filter, that's it
                        writer.write("");
                        writer.write("");
                        writer.write("");
                        writer.write("");
                        writer.endRecord();
                    } catch (IOException ex) {
                        Logger.getLogger(TestKonfiguration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    // TODO: Errorlog checken
                    Logger.getLogger("Buchung").log(Level.SEVERE, "keinen Filter gefunden - Item " + item.getId() + " iPartner()n Rechnung " + invoice.getInvoiceNumber() + " nicht buchbar!" );
                }

            }

        }


        writer.flush();
        writer.close();



    }

    public static void getRabatte() {
        BuhaRabattJpaController rabattJpaController = new BuhaRabattJpaController();
        List<BuhaRabatt> rabatte = rabattJpaController.findBuhaRabattEntities();
    }


    public static String roundScale2( double d ) {
        return Double.toString(Math.rint( d * 100 ) / 100.).replaceAll("\\.", ",");
    }


    private static void testReadByNumber() {
        InvoiceJpaController invoiceJpaController = new InvoiceJpaController();
        List<Invoice> invoices = invoiceJpaController.findInvoiceByNumber(1901010);
        if (invoices.size() > 0) {
            Invoice invoice = invoices.iterator().next();
            System.out.println("ID Invoice:" + invoice.getId());
        }



        OrganiserInvoiceJpaController orgInvoiceJpaController = new OrganiserInvoiceJpaController();
        List<OrganiserInvoice> orgInvoices = orgInvoiceJpaController.findOrganiserInvoiceByNumber(1480710);
        System.out.println("Anzahl:" + orgInvoices.size());
        if (orgInvoices.size() > 0) {
            OrganiserInvoice invoice = orgInvoices.iterator().next();
            System.out.println("ID Invoice:" + invoice.getId());
        }
    }

    private static void checkSettings() {
        HashMap map = new HashMap();
        BuhaPropertiesJpaController propertiesJpaController = new BuhaPropertiesJpaController();
        List<BuhaProperties> properties = propertiesJpaController.findBuhaPropertiesEntities();
        for(BuhaProperties p : properties) {
            map.put(p.getName(), p);
        }
        BuhaProperties myprop = new BuhaProperties();
        myprop.setName("test1");
        myprop.setValue("eins");
        try {
            propertiesJpaController.edit(myprop);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TestKonfiguration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TestKonfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private static void testLoggingOld() {
//        File root = new File("/home/gugu/log");
//        LoggingHandler lh = new LoggingHandler(root, "20110117");
//        lh.getLoggerAR().log(Level.INFO, "eins");
//        lh.getLoggerAR().log(Level.SEVERE, "zwei");
//        System.out.println("normal: " + lh.getCountAR());
//        System.out.println("error:  " + lh.getCountAR_ERR());
//        lh.close();
    }
//
//    private static void testArrangement()  {
//
//            NukeMrcommerceArrangementJpaController arrangementJpaController = new NukeMrcommerceArrangementJpaController();
//            //        NukeMrcommerceArrangement arr = arrangementJpaController.findNukeMrcommerceArrangement(3064);
//            //        if (arr != null) {
//            //            NukeMrcommerceItems item = arr.getNukeMrcommerceItems();
//            //            Collection<NukeMrcommerceArrangementCity> cities = arr.getNukeMrcommerceArrangementCityCollection();
//            //            for (NukeMrcommerceArrangementCity city : cities) {
//            //                NukeMrcommerceAddress address = city.getAddress();
//            //                System.out.println(address.getDescription());
//            //
//            //            }
//            //
//            //        }
//            Integer days = arrangementJpaController.findMaxZFristByOrganiserID(119);
//            System.out.println(Integer.toString(days));
//
//
//    }


    private static void zahlungseingang() {

        InvoiceItemJpaController invoiceItemJpaController = new InvoiceItemJpaController();
//         List<InvoiceItem> items = invoiceItemJpaController.findInvoiceItemByUniqueNumber("GP331946");
//         InvoiceItem itemFound = null;
//         for (InvoiceItem item : items) {
//             System.out.println(" BH-Status: " + item.getBHstatus() + "   FF-Status: " + item.getFFstatus());
//         }
//         invoiceItemJpaController.updateInvoiceItemStatus("GP403960", 6);
//         invoiceItemJpaController.updateInvoiceItemStatus("GP414455", 6);
//         invoiceItemJpaController.updateInvoiceItemStatus("GP419625", 6);
//         invoiceItemJpaController.updateInvoiceItemStatus("GP211298", 6);
//         invoiceItemJpaController.updateInvoiceItemStatus("GP124097", 6);
//         invoiceItemJpaController.updateInvoiceItemStatus("GP549104", 6);
         int result = invoiceItemJpaController.updateInvoiceItemStatus("GY000000", 6);
         System.out.println(" Result: " +result);
        //GP331946
    }
}
