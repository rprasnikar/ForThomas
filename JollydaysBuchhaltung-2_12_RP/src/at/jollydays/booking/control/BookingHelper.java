/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.control;

import at.jollydays.booking.Globals;
import at.jollydays.booking.bo.BuhaFilter;
import at.jollydays.booking.bo.BuhaKostl;
import at.jollydays.booking.bo.NukeMrcommerceArrangementCity;
import at.jollydays.booking.bo.NukeMrcommerceItems;
import at.jollydays.booking.bo.NukeMrcommerceSaleschannelinvoice;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Gunter Reinitzer
 */
public class BookingHelper {
    
    protected DateFormat format = new SimpleDateFormat("yyyyMMdd");
    protected MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
    protected NumberFormat nf = NumberFormat.getInstance();
    protected static NumberFormat numberFormat = NumberFormat.getNumberInstance();    

    BookingHelper() {
        numberFormat.setGroupingUsed(false);        
    }
    
    protected static String roundScale2(double d) {
        BigDecimal bd = BigDecimal.valueOf(d);
        double db = (bd.setScale(2, RoundingMode.HALF_UP)).doubleValue();
        return numberFormat.format(db);
        //return Double.toString(db).replaceAll("\\.", ",");
        //return Double.toString(Math.rint(d * 100) / 100.).replaceAll("\\.", ",");
    }  
    
    protected static String prepareStringForCSV(String toReplace) {
        if (toReplace != null) {
            return toReplace.replaceAll(";", ",");
        }
        return Globals.EMPTYSTRING;
    }    
    

    protected double getTaxRate(String countryOrganiser, Date date) {
        double taxrate = 0.0;
        if (countryOrganiser.equals("AUT")) {
            taxrate = 20.0;
        }
        if (countryOrganiser.equals("DEU")) {
            taxrate = 19.0;
        }
        if (countryOrganiser.equals("CHE")) {
            //ab 1.1.2011 neuer Steuersatz
            Calendar cal = Calendar.getInstance();
            cal.set(2011, 1, 1, 0, 0, 0);
            if (date.before(cal.getTime())) {
                taxrate = 7.6;
            } else {
                taxrate = 8.0;
            }
        }
        return taxrate;
    }   
    
    protected BuhaKostl getKostl(ConfigurationController configurationController, NukeMrcommerceSaleschannelinvoice salesChannelInvoice, String country) {
        Iterator<BuhaKostl> iterKostl = configurationController.getKostl().iterator();
        BuhaKostl kostl = null;
        int salesChannel = 0;
        if (salesChannelInvoice != null) {
            salesChannel = salesChannelInvoice.getNukeSaleschannelinvoiceChannel();
        }
        
//        if (salesChannel == 0) {
//            salesChannel = configurationController.getCooperationInvoiceDetailsJpaController().findNukeCooperationInvoiceDetails(null); //Logik für Lieferschein KOSTL hier einfügen
//        }
        
        if (salesChannel == 0) {
            salesChannel = 18;
        }
        
        //für die DACH Länder kann die Kostl direkt ermittelt werden
        while (iterKostl.hasNext()) {
            kostl = iterKostl.next();
            if (kostl.getChannel() == salesChannel
                    && (kostl.getCountry().equals(country) || kostl.getCountry().equals(Globals.EMPTYSTRING))) {
                break;
            }
            kostl = null;
        }
        
        //alles andere ist AT
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
    

    protected boolean checkGutscheinFilter(BuhaFilter filter, String gutscheinNummer) {

        //Vorprüfungen
        if (filter.getGutscheinfilter() == null) {
            return true;
        }
        if (filter.getGutscheinfilter().trim().equals(Globals.EMPTYSTRING)) {
            return true;
        }

        if (gutscheinNummer == null) {
            return false;
        }
        if (gutscheinNummer.trim().length() < filter.getGutscheinfilter().trim().length()) {
            return false;
        }

        //eigentliche Prüfung
        if (filter.getGutscheinfilter().equals(gutscheinNummer.substring(0, filter.getGutscheinfilter().length()))) {
            return true;
        }

        return false;


    }    
    
    protected void writeKTSatz(ConfigurationController configurationController, NukeMrcommerceArrangementCity arrCity, NukeMrcommerceItems item) throws IOException {
        JDCsvWriter writer = configurationController.getCSVController().getWriterKT();

        if (writer != null) {
            configurationController.setProperty(Globals.PREFIXARRCITY + arrCity.getId(), arrCity.getId().toString());
            String desc = item.getNukeItemName() + " - " + arrCity.getNukeMrcommerceAddress().getDescription();
            writer.write("2");
            writer.write(arrCity.getId().toString());
            writer.write(desc);
            writer.endRecord();
        }
    }
    
    protected int getISOCountry2BuhaCountry(String iSOCountry) {
        if (iSOCountry == null)
            return 0;
            
        if (iSOCountry.equals("AUT")) 
            return 0;

        if (iSOCountry.equals("DEU")) 
            return 2;
        
        if (iSOCountry.equals("CHE")) 
            return 3;
        
        return 0;
    }
}
