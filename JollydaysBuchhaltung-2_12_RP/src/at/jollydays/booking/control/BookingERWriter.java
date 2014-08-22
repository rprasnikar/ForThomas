/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.control;

import at.jollydays.booking.Globals;
import at.jollydays.booking.bo.BuhaBooking;
import at.jollydays.booking.bo.BuhaFilter;
import at.jollydays.booking.bo.BuhaKostl;
import at.jollydays.booking.bo.InvoiceItem;
import at.jollydays.booking.bo.NukeMrcommerceArrangementCity;
import at.jollydays.booking.bo.NukeMrcommerceEventsArrangementCities;
import at.jollydays.booking.bo.NukeMrcommerceItems;
import at.jollydays.booking.bo.NukeMrcommerceOrganiser;
import at.jollydays.booking.bo.NukeMrcommerceSaleschannelinvoice;
import at.jollydays.booking.bo.NukeMrcommerceUser2event;
import at.jollydays.booking.bo.OrganiserInvoice;
import at.jollydays.booking.bo.OrganiserInvoiceItem;
import at.jollydays.booking.db.NukeMrcommerceEventsArrangementCitiesJpaController;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;

/**
 *
 * @author Gunter Reinitzer
 */
public class BookingERWriter extends BookingHelper {

    private HashMap organiserMap = new HashMap();
    private NukeMrcommerceEventsArrangementCitiesJpaController eventsArrangementCitiesJpaController = new NukeMrcommerceEventsArrangementCitiesJpaController();

    protected double writeERSatz0(ConfigurationController configurationController, OrganiserInvoice organiserInvoice, OrganiserInvoiceItem orgInvoiceItem, BuhaBooking booking, InvoiceItem invoiceItem, InvoiceItem itemRabatt, String countryOrganiser, Auslandsfilter auslandsFilter, NukeMrcommerceUser2event user2Event, NukeMrcommerceSaleschannelinvoice salesChannelInvoice, boolean keyCaching) throws IOException {

        JDCsvWriter writer = configurationController.getCSVController().getWriterER();
        double sum = 0.0;

        double taxrate = getTaxRate(countryOrganiser, organiserInvoice.getInvoiceDateTime());

        // Für Besorger: wegen Veranstaltern, die steuerbefreit sind, nehmen wir die Steuer aus dem item
        if (organiserInvoice.getBesorger()) {
            taxrate = orgInvoiceItem.getTaxRate() != null ? orgInvoiceItem.getTaxRate().doubleValue() : 0;
        }

        double amountOrg = orgInvoiceItem.getAmount().doubleValue();
        // CHF Hack
        //if (booking.getBuhaCountry().trim().equals("3") && orgInvoiceItem.getBrutto_vk_currency_converted() != null ) {
        //    amountOrg = orgInvoiceItem.getBrutto_vk_currency_converted().doubleValue();
        //}
        
        double amountKun = invoiceItem.getAmounteur().doubleValue();
        //double rabatt = -orgInvoiceItem.getCorrespondingItemRabattAmount().doubleValue() * 100 / (100 + taxrate);

        if (booking.getNegativBuchen()) {
            amountKun = -amountKun;
            //amountOrg = -amountOrg;
            //rabatt    = -rabatt;
        }

        double prov = (amountKun - amountOrg);
        double tax = prov * taxrate / (taxrate + 100);
        //double taxRabatt = rabatt * taxrate / 100;


        int konto = 0;
        int gegenkonto = 0;
        int zwischenkonto = 0;

        if (booking.getKonto() == 0) {
            konto = organiserInvoice.getOrganiserID() + Globals.KREDITORSTARTVALUE;
        } else {
            konto = booking.getKonto();
        }

        if (booking.getGegenkonto() == 0) {
            gegenkonto = organiserInvoice.getOrganiserID() + Globals.KREDITORSTARTVALUE;
        } else {
            gegenkonto = booking.getGegenkonto();
        }

        if (booking.getZwischenkonto() == 0 && booking.getCreditor() && booking.getKonto() > 0 && booking.getGegenkonto() > 0) {
            zwischenkonto = organiserInvoice.getOrganiserID() + Globals.KREDITORSTARTVALUE;
        } else {
            zwischenkonto = booking.getZwischenkonto();
        }


        String gegenbuchKz = "E";
//        if (writer.isDeferred() && booking.getCreditor()) {
        if (writer.isDeferred()) {
            gegenbuchKz = "O";
        }

        String organiserNr = Integer.toString(organiserInvoice.getOrganiserID() + Globals.KREDITORSTARTVALUE);


        //.[invoice_item_amount]*0.65/(1+(invocie_item_tax_rate/100))
        if (organiserInvoice.getBesorger()) {

            // besorger
            double amountKunBesorger = -amountKun * 100 * Globals.BESORGERRUECKSTELLUNG / (taxrate + 100);
            tax = amountOrg * taxrate / (taxrate + 100);

            if (zwischenkonto > 0) {
                // 2 Buchungen über Zwischenkonto -> 3 mit Split über Kreditor

                // 1a=======
                writer.write("0");                                                      //1
                writer.write(organiserInvoice.getInvoiceNumber());
                writer.write(Integer.toString(booking.getKonto()));
                writer.write(organiserNr);
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                writer.write(booking.getBuhaCountry());
                writer.write("ER");
                writer.write(Short.toString(booking.getBuchungscode()));
                writer.write("0");
                writer.write(roundScale2(amountKunBesorger));                                   //15
                writer.write("0");
                writer.write("0");
                writer.write("0");
                writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                writer.write(gegenbuchKz);                                                      //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, amountKunBesorger, user2Event, keyCaching);


                // 1b=======
                writer.write("0");                                                      //1
                writer.write(organiserInvoice.getInvoiceNumber());
                writer.write(Integer.toString(booking.getZwischenkonto()));
                writer.write(organiserNr);
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                writer.write(booking.getBuhaCountry());
                writer.write("ER");
                writer.write(Short.toString(booking.getBuchungscode2()));
                writer.write("0");
                writer.write(roundScale2(-amountKunBesorger));                                   //15
                writer.write("0");
                writer.write("0");
                writer.write("0");
                writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                writer.write(gegenbuchKz);                                                      //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, -amountKunBesorger, user2Event, keyCaching);


                // 2=======
                writer.write("0");                                                      //1
                writer.write(organiserInvoice.getInvoiceNumber());
                writer.write(Integer.toString(booking.getGegenkonto()));
                writer.write(Integer.toString(organiserInvoice.getOrganiserID() + Globals.KREDITORSTARTVALUE));
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                writer.write(booking.getBuhaCountry());
                writer.write("ER");
                writer.write(Short.toString(booking.getBuchungscode2()));

                writer.write(roundScale2(taxrate));
                writer.write(roundScale2(amountOrg - tax));                                        //15
                writer.write("0");
                writer.write(roundScale2(tax));
                writer.write(Integer.toString(booking.getSteuercode()));
                writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                writer.write(gegenbuchKz);                                                      //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, amountOrg - tax, user2Event, keyCaching);

                sum = amountOrg;

            } else {

                // nur 1 Buchung
                writer.write("0");                                                       //1
                writer.write(organiserInvoice.getInvoiceNumber());
                writer.write(Integer.toString(konto));
                writer.write(Integer.toString(gegenkonto));
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                writer.write(booking.getBuhaCountry());
                writer.write("ER");
                writer.write(Short.toString(booking.getBuchungscode()));

                writer.write(roundScale2(taxrate));
                writer.write(roundScale2(amountKun));                                   //15
                writer.write("0");
                writer.write("0");
                writer.write(Integer.toString(booking.getSteuercode()));
                writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                writer.write(gegenbuchKz);                                                      //20
                writer.write("A");
                //writer.write("");
                writer.endRecordFinally();
                writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, amountKun, user2Event, keyCaching);

                sum = amountKun;

            }

        } else {
//          Vermittler

            if (zwischenkonto > 0) {
//              2 Buchungen über Zwischenkonto

//                if (booking.getSplitbuchung()) {
//                    // vermittler, 2 Buchungen mittels Splitbuchung
//                    gegenbuchKz = "O";     // Ohh nicht 0
//
////                  0======= Splitbuchung ohne Gegenkonto
//                    writer.write("0");                                                      //1
//                    writer.write(organiserInvoice.getInvoiceNumber());
//                    writer.write(Integer.toString(organiserInvoice.getOrganiserID() + Globals.KREDITORSTARTVALUE));
//                    writer.write("");
//                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
//                    writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
//                    writer.write(invoiceItem.getUniqueNumber());
//                    writer.write("");
//                    writer.write("");
//                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
//                    writer.write(booking.getBuhaCountry());
//                    writer.write("ER");
//                    writer.write(Short.toString(booking.getBuchungscode()));
//                    writer.write("0");
//                    writer.write(roundScale2(prov - amountKun));                                   //15
//                    writer.write("0");
//                    writer.write("0");
//                    writer.write("3");
//                    writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
//                    writer.write(gegenbuchKz);    // Ohh nicht 0                                                  //20
//                    writer.write("A");
//                    //writer.write("");
//                    writer.endRecord();
//
//                }

                if (booking.getZwischenkonto() > 0 && booking.getGegenkonto() > 0 && !invoiceItem.getBesorger()) {
                    // vermittler, vier Buchung mit drei Konten (Dinner & Casino)
//              1=======
                    writer.write("0");                                                      //1
                    writer.write(organiserInvoice.getInvoiceNumber());
                    writer.write(Integer.toString(konto));
                    writer.write(organiserNr);
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                    writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                    writer.write(invoiceItem.getUniqueNumber());
                    writer.write("");
                    writer.write("");
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                    writer.write(booking.getBuhaCountry());
                    writer.write("ER");
                    writer.write(Short.toString(booking.getBuchungscode()));
                    writer.write("0");
                    writer.write(roundScale2(amountKun - prov));                                   //15
                    writer.write("0");
                    writer.write("0");
                    writer.write("0");
                    writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                    writer.write(gegenbuchKz);                                                      //20
                    writer.write("A");
                    //writer.write("");
                    writer.endRecord();
                    writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, amountKun - prov, user2Event, keyCaching); // MantisBUg 2280

//              2=======
                    writer.write("0");                                                      //1
                    writer.write(organiserInvoice.getInvoiceNumber());
                    writer.write(Integer.toString(zwischenkonto));
                    writer.write(organiserNr);
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                    writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                    writer.write(invoiceItem.getUniqueNumber());
                    writer.write("");
                    writer.write("");
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                    writer.write(booking.getBuhaCountry());
                    writer.write("ER");
                    writer.write(Short.toString(booking.getBuchungscode2()));
                    writer.write("0");
                    writer.write(roundScale2(prov - amountKun));                                   //15
                    writer.write("0");
                    writer.write("0");
                    writer.write("0");
                    writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                    writer.write(gegenbuchKz);                                                      //20
                    writer.write("A");
                    //writer.write("");
                    writer.endRecord();
                    writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, prov - amountKun, user2Event, keyCaching); // MantisBUg 2280

//              3=======
                    writer.write("0");                                                      //1
                    writer.write(organiserInvoice.getInvoiceNumber());
                    writer.write(Integer.toString(konto));
                    writer.write(organiserNr);
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                    writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                    writer.write(invoiceItem.getUniqueNumber());
                    writer.write("");
                    writer.write("");
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                    writer.write(booking.getBuhaCountry());
                    writer.write("ER");
                    writer.write(Short.toString(booking.getBuchungscode()));
                    writer.write("0");
                    writer.write(roundScale2(prov));                                        //15
                    writer.write("0");
                    writer.write("0");
                    writer.write("0");
                    writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                    writer.write(gegenbuchKz);                                                      //20
                    writer.write("A");
                    //writer.write("");
                    writer.endRecord();
                    writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, prov, user2Event, keyCaching);

//              4=======
                    writer.write("0");                                                      //1
                    writer.write(organiserInvoice.getInvoiceNumber());
                    writer.write(Integer.toString(gegenkonto));
                    writer.write(organiserNr);
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                    writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                    writer.write(invoiceItem.getUniqueNumber());
                    writer.write("");
                    writer.write("");
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                    writer.write(booking.getBuhaCountry());
                    writer.write("ER");
                    writer.write(Short.toString(booking.getBuchungscode2()));
                    writer.write(roundScale2(taxrate));
                    writer.write(roundScale2(tax - prov));                                        //15
                    writer.write("0");
                    writer.write(roundScale2(-tax));
                    writer.write(Integer.toString(booking.getSteuercode()));
                    writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                    writer.write(gegenbuchKz);                                                      //20
                    writer.write("A");
                    //writer.write("");
                    writer.endRecord();
                    writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, tax - prov, user2Event, keyCaching);
                    
                    sum = 0;                    
                    
                    
                } else {

                    // vermittler, doppelte Buchung

//              1=======
                    writer.write("0");                                                      //1
                    writer.write(organiserInvoice.getInvoiceNumber());
                    writer.write(Integer.toString(booking.getKonto()));
                    writer.write(organiserNr);
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                    writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                    writer.write(invoiceItem.getUniqueNumber());
                    writer.write("");
                    writer.write("");
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                    writer.write(booking.getBuhaCountry());
                    writer.write("ER");
                    writer.write(Short.toString(booking.getBuchungscode()));
                    writer.write(roundScale2(taxrate));
                    writer.write(roundScale2(tax - prov));                                   //15
                    writer.write("0");
                    writer.write(roundScale2(-tax));
                    writer.write("3");
                    writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                    writer.write(gegenbuchKz);                                                      //20
                    writer.write("A");
                    //writer.write("");
                    writer.endRecord();
                    writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, tax - prov, user2Event, keyCaching);

//              2=======
                    writer.write("0");                                                      //1
                    writer.write(organiserInvoice.getInvoiceNumber());
                    writer.write(Integer.toString(booking.getZwischenkonto()));
                    writer.write(organiserNr);
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                    writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                    writer.write(invoiceItem.getUniqueNumber());
                    writer.write("");
                    writer.write("");
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                    writer.write(booking.getBuhaCountry());
                    writer.write("ER");
                    writer.write(Short.toString(booking.getBuchungscode2()));
                    writer.write("0");
                    writer.write(roundScale2(amountKun));                                        //15
                    writer.write("0");
                    writer.write("0");
                    writer.write(Integer.toString(booking.getSteuercode()));
                    writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                    writer.write(gegenbuchKz);                                                      //20
                    writer.write("A");
                    //writer.write("");
                    writer.endRecord();
                    writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, amountKun, user2Event, keyCaching);

                    sum = amountKun - prov;
                }

            } else {
                // nur 1 Buchung, zb Freiplätze
                writer.write("0");                                                      //1
                writer.write(organiserInvoice.getInvoiceNumber());
                writer.write(Integer.toString(konto));
                writer.write(organiserNr);
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                writer.write(booking.getBuhaCountry());
                writer.write("ER");
                writer.write(Short.toString(booking.getBuchungscode()));
                writer.write("0");
                writer.write(roundScale2(-amountKun));                                   //15
                writer.write("0");
                writer.write("0");
                writer.write(Integer.toString(booking.getSteuercode()));
                writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                writer.write(gegenbuchKz);                                                      //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, -amountKun, user2Event, keyCaching);

                writer.write("0");                                                      //1
                writer.write(organiserInvoice.getInvoiceNumber());
                writer.write(Integer.toString(gegenkonto));
                writer.write(organiserNr);
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                writer.write(booking.getBuhaCountry());
                writer.write("ER");
                writer.write(Short.toString(booking.getBuchungscode2()));
                writer.write("0");
                writer.write(roundScale2(amountKun));                                   //15
                writer.write("0");
                writer.write("0");
                writer.write(Integer.toString(booking.getSteuercode()));
                writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                writer.write(gegenbuchKz);                                                      //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, amountKun, user2Event, keyCaching);

                //sum = amountKun;

            }

//          ======= Ausland Kauf- Einlösung
            if (auslandsFilter != null && !organiserInvoice.getBesorger()) {

                writer.write("0");                                                      //1
                writer.write(organiserInvoice.getInvoiceNumber());
                writer.write(Integer.toString(auslandsFilter.getInvoiceFilter().getBuhaBooking().getKonto()));
                writer.write(organiserNr);
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                writer.write(auslandsFilter.getInvoiceFilter().getBuhaBooking().getBuhaCountry());
                writer.write("ER");
                writer.write(Short.toString(auslandsFilter.getInvoiceFilter().getBuhaBooking().getBuchungscode()));
                writer.write("0");
                writer.write(roundScale2(amountKun));                                   //15
                writer.write("0");
                writer.write("0");
                writer.write(Integer.toString(auslandsFilter.getInvoiceFilter().getBuhaBooking().getSteuercode()));
                writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                writer.write(gegenbuchKz);                                                      //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, amountKun, user2Event, keyCaching);

                writer.write("0");                                                      //1
                writer.write(organiserInvoice.getInvoiceNumber());
                writer.write(Integer.toString(auslandsFilter.getOrganiserInvoiceFilter().getBuhaBooking().getGegenkonto()));
                writer.write(organiserNr);
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                writer.write(prepareStringForCSV(invoiceItem.getServiceName()));
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
                writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                writer.write(auslandsFilter.getOrganiserInvoiceFilter().getBuhaBooking().getBuhaCountry());
                writer.write("ER");
                writer.write(Short.toString(auslandsFilter.getOrganiserInvoiceFilter().getBuhaBooking().getBuchungscode2()));
                writer.write("0");
                writer.write(roundScale2(-amountKun));                                   //15
                writer.write("0");
                writer.write("0");
                writer.write(Integer.toString(auslandsFilter.getOrganiserInvoiceFilter().getBuhaBooking().getSteuercode()));
                writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                writer.write(gegenbuchKz);                                                      //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, -amountKun, user2Event, keyCaching);

            }

//          ======= Rabatte
            if (itemRabatt != null) {

                double rabattBrutto = -orgInvoiceItem.getCorrespondingItemRabattAmount().doubleValue();
                double rabatt = rabattBrutto * 100 / (100 + taxrate);
                double taxRabatt = rabatt * taxrate / 100;

                String countryKunde = itemRabatt.getInvoiceID().getPartner().getAddress().getISOState();
                BuhaFilter filterKunde = getFilterERRabatt(configurationController, itemRabatt.getMrcommerceItemID().intValue(), countryKunde);
                BuhaFilter filterOrganiser = getFilterERRabatt(configurationController, itemRabatt.getMrcommerceItemID().intValue(), countryOrganiser);

                if (filterKunde != null && filterOrganiser != null) {
                    writer.write("0");                                                      //1
                    writer.write(organiserInvoice.getInvoiceNumber());
                    //writer.write(Integer.toString(filterKunde.getBuhaBooking().getKonto()));
                    writer.write(Integer.toString(filterOrganiser.getBuhaBooking().getKonto()));
                    //writer.write(Integer.toString(filterOrganiser.getBuhaBooking().getGegenkonto()));
                    writer.write(organiserNr);
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                    writer.write("Rabatt");
                    writer.write(itemRabatt.getUniqueNumber());
                    writer.write("");
                    writer.write("");
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                    writer.write(filterOrganiser.getBuhaBooking().getBuhaCountry());
                    writer.write("ER");
                    writer.write(Short.toString(filterOrganiser.getBuhaBooking().getBuchungscode()));
                    writer.write(roundScale2(taxrate));
                    writer.write(roundScale2(rabatt));                                   //15
                    writer.write("0");
                    writer.write(roundScale2(taxRabatt));
                    writer.write(Integer.toString(filterOrganiser.getBuhaBooking().getSteuercode()));
                    writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                    writer.write(gegenbuchKz);                                                      //20
                    writer.write("A");
                    //writer.write("");
                    writer.endRecord();
                    writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, rabatt, user2Event, keyCaching);

                    writer.write("0");                                                      //1
                    writer.write(organiserInvoice.getInvoiceNumber());
                    //writer.write(Integer.toString(filterKunde.getBuhaBooking().getKonto()));
                    writer.write(Integer.toString(filterKunde.getBuhaBooking().getGegenkonto()));
                    //writer.write(Integer.toString(filterOrganiser.getBuhaBooking().getGegenkonto()));
                    writer.write(organiserNr);
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
                    writer.write("Rabatt");
                    writer.write(itemRabatt.getUniqueNumber());
                    writer.write("");
                    writer.write("");
                    writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
                    writer.write(filterOrganiser.getBuhaBooking().getBuhaCountry());
                    writer.write("ER");
                    writer.write(Short.toString(filterKunde.getBuhaBooking().getBuchungscode2()));
                    writer.write("0");
                    writer.write(roundScale2(-rabattBrutto));                                   //15
                    writer.write("0");
                    writer.write("0");
                    writer.write(Integer.toString(filterOrganiser.getBuhaBooking().getSteuercode()));
                    writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
                    writer.write(gegenbuchKz);                                                      //20
                    writer.write("A");
                    //writer.write("");
                    writer.endRecord();
                    writeERSatz1(configurationController, organiserInvoice, orgInvoiceItem, booking, invoiceItem, itemRabatt, countryOrganiser, salesChannelInvoice, -rabattBrutto, user2Event, keyCaching);

                }
            }
        }

        if (writer.isDeferred()) {
            return sum;
        }
        return 0.0;
    }

    protected void writeERSatz1(ConfigurationController configurationController, OrganiserInvoice organiserInvoice, OrganiserInvoiceItem orgInvoiceItem, BuhaBooking booking, InvoiceItem invoiceItem, InvoiceItem itemRabatt, String countryOrganiser, NukeMrcommerceSaleschannelinvoice salesChannelInvoice, double amount, NukeMrcommerceUser2event user2Event, boolean keyCaching) throws IOException {

        JDCsvWriter writer = configurationController.getCSVController().getWriterER();

        NukeMrcommerceEventsArrangementCities eventArrCity = eventsArrangementCitiesJpaController.findNukeMrcommerceEventsArrangementCitiesByEvent(user2Event.getNukeEventId());
        
        String cachingKey = Globals.PREFIXARRCITY + eventArrCity.getNukeMrcommerceEventsArrangementCitiesPK().getArrangementToCityId();
        if (keyCaching && eventArrCity != null && configurationController.getProperty(cachingKey) == null) {
            //neue ArrCity in Datei schreiben, weil noch nicht vorhanden
            NukeMrcommerceArrangementCity arrCity = configurationController.getArrangementCityJpaController().findNukeMrcommerceArrangementCity(eventArrCity.getNukeMrcommerceEventsArrangementCitiesPK().getArrangementToCityId());
            NukeMrcommerceItems item = configurationController.getItemsJpaController().findNukeMrcommerceItems(invoiceItem.getMrcommerceItemID());
            writeKTSatz(configurationController, arrCity, item);
        }

        int kosTr = 0;
        if (eventArrCity != null) {
            kosTr = eventArrCity.getNukeMrcommerceEventsArrangementCitiesPK().getArrangementToCityId();
        }

        String country = invoiceItem.getInvoiceID().getPartner().getAddress().getISOState();
        BuhaKostl kostl = getKostl(configurationController, salesChannelInvoice, country);
        if (kostl == null) {
            configurationController.getLoggingHandler().getLogger(Globals.LOGGINGER).log(Level.SEVERE, "keine Kostenstelle gefunden - Item " + orgInvoiceItem.getId() + " in Rechnung " + organiserInvoice.getInvoiceNumber());
            return;
        }

        //21 empty entries
        writer.write("1");
        for (int i = 0; i < 20; i++) {
            writer.write("");
        }

        writer.write(Integer.toString(kostl.getKostl()));                       //22
        writer.write(Integer.toString(kosTr));
        writer.write(roundScale2(amount));
        writer.write("1");                                                      //25
        writer.write("1");
        writer.write("0");
        writer.write("0");
        writer.write(format.format(organiserInvoice.getInvoiceDateTime()).substring(0, 6));
        writer.write("");                                                       //30
        writer.write("0");
        writer.write("");                                                       //     abteilung, nicht definiert
        writer.write(Integer.toString(invoiceItem.getMrcommerceItemID()));      //     item Nr.
        writer.write("");                                                       //34   geschber, nicht definiert

        //war der Buchungassatz 0 finally, muss auch der Satz 1 finally sein
        if (writer.isLastFinally()) {
            writer.endRecordFinally();
        } else {
            writer.endRecord();
        }


    }

    protected void writeCRSatz(ConfigurationController configurationController, NukeMrcommerceOrganiser organiser) throws IOException {

        if (organiserMap.containsKey(organiser.getNukeorgID())) {
            return;
        }

        JDCsvWriter writer = configurationController.getCSVController().getWriterCR();
        organiserMap.put(organiser.getNukeorgID(), organiser);
        String messageDetail = " Veranstalter " + organiser.getNukeorgID();

        configurationController.getLoggingHandler().getLogger(Globals.LOGGINGCR).log(Level.INFO, messageDetail);
        
        
//        Integer days = configurationController.getArrangementJpaController().findMaxZFristByOrganiserID(organiser.getNukeorgID());
//        if (days != null) {
//            organiser.setTermOfPayment(days.intValue());
//        }
        if (organiser.getDeptcondition_day2() != null && organiser.getDeptcondition_day2().intValue() > 0) {
            organiser.setTermOfPayment(organiser.getDeptcondition_day2().intValue());
        }
        
        
        String letter3Country = configurationController.get3LetterCountryFromName(organiser.getNukeOrgState(), configurationController.getLoggingHandler().getLogger(Globals.LOGGINGCR), messageDetail);
        String letter2Country = configurationController.get2LetterCountry(letter3Country, configurationController.getLoggingHandler().getLogger(Globals.LOGGINGCR), messageDetail);

        int creditorNr = organiser.getNukeorgID() + Globals.KREDITORSTARTVALUE;
        writer.write(Integer.toString(creditorNr));                             //1
        writer.write(prepareStringForCSV(organiser.getNukeOrgName() + " " + organiser.getNukeOrgName2()));
        writer.write(prepareStringForCSV(organiser.getNukeOrgCity()));
        writer.write(prepareStringForCSV(organiser.getNukeOrgStreet()));
        writer.write(prepareStringForCSV(organiser.getNukeOrgZip()));           //5
        writer.write(prepareStringForCSV(Globals.EMPTYSTRING));
        writer.write(prepareStringForCSV(letter2Country));
        writer.write(prepareStringForCSV(organiser.getNukeOrgFon()));
        writer.write(prepareStringForCSV(organiser.getNukeOrgEmail()));
        writer.write(prepareStringForCSV(organiser.getNukeOrgTaxUid()));        //10
        writer.write(prepareStringForCSV(Globals.EMPTYSTRING));
        writer.write(Integer.toString(organiser.getTermOfPayment()));
        writer.write("0");
        writer.write("0");
        writer.write(organiser.getNukeOrgBankaccount());                        //15
        writer.write(organiser.getNukeOrgBlz());
        writer.write(organiser.getNukeOrgBic());
        writer.write(organiser.getNukeOrgIban());
        writer.write(letter3Country.equals("AUT") ? "0" : "1");                 //19
        writer.write(prepareStringForCSV(Integer.toString(getISOCountry2BuhaCountry(letter3Country))));
        if (organiser.getNukeOrgIban() != null && organiser.getNukeOrgIban().length() > 2 && organiser.getNukeOrgIban().substring(0, 2).equals("DE")) {
            writer.write("2");
        } else {
            writer.write("1");
        }

        writer.endRecord();
    }



    protected BuhaFilter getFilterERRabatt(ConfigurationController configurationController, int itemID, String country) {
        BuhaFilter filter = null;
        if (itemID < Globals.GUTSCHEINMAXITEMNR) {
            return null;
        }
        Iterator<BuhaFilter> iterFilter = configurationController.getFiltersER().iterator();
        while (iterFilter.hasNext()) {
            filter = iterFilter.next();
            if ((filter.getCountry().equals(country))
                    && filter.getItemFrom() <= itemID
                    && filter.getItemTo() >= itemID
                    && filter.getDonotaccount() == false) {
                break;
            }
            filter = null;
        }
        return filter;
    }

    protected void writerERSplitFirstLine(ConfigurationController configurationController, OrganiserInvoice organiserInvoice, String country, double sum, String gegenbuchKz) throws IOException {
        JDCsvWriter writer = configurationController.getCSVController().getWriterER();

        configurationController.getCSVController().getWriterER().setDeferred(false);        
        
//      0======= Splitbuchung ohne Gegenkonto
        writer.write("0");                                                      //1
        writer.write(organiserInvoice.getInvoiceNumber());
        writer.write(Integer.toString(organiserInvoice.getOrganiserID() + Globals.KREDITORSTARTVALUE));
        writer.write("");
        writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //5
        writer.write("Buchung");
        writer.write("");
        writer.write("");
        writer.write("");
        writer.write(format.format(organiserInvoice.getInvoiceDateTime()));              //10
        writer.write(country);
        writer.write("ER");
        writer.write("2");
        writer.write("0");
        writer.write(roundScale2(-sum));                                   //15
        writer.write("0");
        writer.write("0");
        writer.write("0");
        writer.write((new SimpleDateFormat("MM")).format(organiserInvoice.getInvoiceDateTime()));
        writer.write(gegenbuchKz);    // Ohh nicht 0                                                  //20
        writer.write("A");
        //writer.write("");
        writer.endRecord();
    }
}
