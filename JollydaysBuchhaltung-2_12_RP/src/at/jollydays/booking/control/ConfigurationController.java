/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.control;

import at.jollydays.booking.Globals;
import at.jollydays.booking.bo.BuhaArea;
import at.jollydays.booking.bo.BuhaBooking;
import at.jollydays.booking.bo.BuhaFilter;
import at.jollydays.booking.bo.BuhaKostl;
import at.jollydays.booking.bo.BuhaProperties;
import at.jollydays.booking.bo.BuhaRabatt;
import at.jollydays.booking.bo.NukeMrcommerceCountry;
import at.jollydays.booking.db.BuhaAreaJpaController;
import at.jollydays.booking.db.BuhaBookingJpaController;
import at.jollydays.booking.db.BuhaFilterJpaController;
import at.jollydays.booking.db.BuhaKostlJpaController;
import at.jollydays.booking.db.BuhaPropertiesJpaController;
import at.jollydays.booking.db.BuhaRabattJpaController;
import at.jollydays.booking.db.InvoiceItemJpaController;
import at.jollydays.booking.db.InvoiceJpaController;
import at.jollydays.booking.db.NukeMrcommerceArrangementCityJpaController;
import at.jollydays.booking.db.NukeMrcommerceCountryJpaController;
import at.jollydays.booking.db.NukeMrcommerceEventsArrangementCitiesJpaController;
import at.jollydays.booking.db.NukeMrcommerceItemsJpaController;
import at.jollydays.booking.db.NukeMrcommerceOrganiserJpaController;
import at.jollydays.booking.db.NukeMrcommerceSaleschannelinvoiceJpaController;
import at.jollydays.booking.db.NukeMrcommerceUser2eventJpaController;
import at.jollydays.booking.db.OrganiserInvoiceJpaController;
import at.jollydays.booking.db.PartnerJpaController;
import at.jollydays.booking.db.exceptions.NonexistentEntityException;
import at.jollydays.booking.db.NukeCooperationInvoiceDetailsJpaController;
import at.jollydays.booking.db.VNukeCooperationDeliverynoteCountsJpaController;
import at.jollydays.booking.db.VNukeCooperationDeliverynoteDetailsJpaController;
import at.jollydays.booking.db.NukeCooperationDeliverynoteAdditionalfieldsJpaController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gunter Reinitzer
 */
public class ConfigurationController {
    private ArrayList<BuhaFilter> allFilters;
    private ArrayList<BuhaFilter> allFiltersAR;
    private ArrayList<BuhaFilter> allFiltersER;
    private ArrayList<BuhaFilter> allFiltersRabatte;
    private ArrayList<BuhaFilter> allFiltersPayment;
    private ArrayList<BuhaFilter> allFiltersKoTraeger;
    private List<BuhaArea> allAreas;
    private ArrayList<BuhaBooking> allBookings;
    private ArrayList<BuhaRabatt> allRabatte;
    private ArrayList<BuhaKostl> allKostl;
    private List<NukeMrcommerceCountry> allCountries;
    private HashMap<String, NukeMrcommerceCountry> countryMap;
    private HashMap<String, BuhaProperties> propertiesMapNotPre;
    private HashMap<String, BuhaProperties> propertiesMapPre;
    private CSVController cSVController;
    private LoggingHandler loggingHandler;

    private BuhaPropertiesJpaController propertiesJpaController = new BuhaPropertiesJpaController();
    private OrganiserInvoiceJpaController organiserInvoiceJpaController = new OrganiserInvoiceJpaController();
    private NukeMrcommerceItemsJpaController itemsJpaController = new NukeMrcommerceItemsJpaController();
    private NukeMrcommerceUser2eventJpaController user2eventJpaController = new NukeMrcommerceUser2eventJpaController();
    private InvoiceItemJpaController invoiceItemJpaController = new InvoiceItemJpaController();
    private NukeMrcommerceSaleschannelinvoiceJpaController saleschannelinvoiceJpaController = new NukeMrcommerceSaleschannelinvoiceJpaController();
    private NukeMrcommerceOrganiserJpaController organiserJpaController = new NukeMrcommerceOrganiserJpaController();
//    private NukeMrcommerceArrangementJpaController arrangementJpaController = new NukeMrcommerceArrangementJpaController();
    private InvoiceJpaController invoiceJpaController = new InvoiceJpaController();
    private NukeMrcommerceEventsArrangementCitiesJpaController eventsArrangementCitiesJpaController = new NukeMrcommerceEventsArrangementCitiesJpaController();
    private NukeMrcommerceArrangementCityJpaController arrangementCityJpaController = new NukeMrcommerceArrangementCityJpaController(); 
    private PartnerJpaController partnerJpaController = new PartnerJpaController();
    private BuhaAreaJpaController areaJPAController = new BuhaAreaJpaController();
    private BuhaBookingJpaController bookingJPAController = new BuhaBookingJpaController();
    private NukeCooperationInvoiceDetailsJpaController cooperationInvoiceDetailsJPAController = new NukeCooperationInvoiceDetailsJpaController();
    private VNukeCooperationDeliverynoteCountsJpaController CooperationDeliverynoteCountsJpaController = new VNukeCooperationDeliverynoteCountsJpaController();
    private VNukeCooperationDeliverynoteDetailsJpaController CooperationDeliverynoteDetailsJpaController = new VNukeCooperationDeliverynoteDetailsJpaController();
    private NukeCooperationDeliverynoteAdditionalfieldsJpaController cooperationDeliverynoteAdditionalfieldsJpaController = new NukeCooperationDeliverynoteAdditionalfieldsJpaController();
    
    public static void main(String[] args) {

        BuhaFilterJpaController buhaFilterJpaController = new BuhaFilterJpaController();
        buhaFilterJpaController.findBuhaFilterEntities();

        BuhaAreaJpaController buhaAreaJpaController = new BuhaAreaJpaController();
        BuhaPropertiesJpaController propertiesJpaController = new BuhaPropertiesJpaController();

        BuhaArea area = buhaAreaJpaController.findBuhaArea(new Integer(1));
        Collection<BuhaBooking> buhaBookings = area.getBuhaBookingCollection();
        Iterator<BuhaBooking> iterBookings = buhaBookings.iterator();
        while (iterBookings.hasNext()) {
            BuhaBooking booking = iterBookings.next();

             Collection<BuhaFilter> filters = booking.getBuhaFilterCollection();
             Iterator<BuhaFilter> iterFilter = filters.iterator();
             while (iterFilter.hasNext()) {
                 BuhaFilter filter = iterFilter.next();
                 //allFilters.put(filter.getId(), filter);
             }
        }

    }

    public List<BuhaBooking> getBookings() {
        if (allBookings == null) {
            allBookings = new ArrayList();
            //BuhaFilterJpaController buhaFilterJpaController = new BuhaFilterJpaController();
            //buhaFilterJpaController.findBuhaFilterEntities();

            //BuhaAreaJpaController buhaAreaJpaController = new BuhaAreaJpaController();
            List<BuhaArea> areas = getAreas();

            //List<BuhaArea> areas = buhaAreaJpaController.findBuhaAreaEntities();
            Iterator<BuhaArea> iterArea = areas.iterator();
            while (iterArea.hasNext()) {
                BuhaArea area = iterArea.next();
                        //buhaAreaJpaController.findBuhaArea(new Integer(1));
                Collection<BuhaBooking> buhaBookings = area.getBuhaBookingCollection();
                allBookings.addAll(buhaBookings);
            }
            Collections.sort(allBookings);
        }
        return allBookings;
    }

    private  List<BuhaFilter> getFilters() {
        if (allFilters == null) {
            allFilters = new ArrayList(1000);
            allFiltersAR = new ArrayList(1000);
            allFiltersER = new ArrayList(1000);
            allFiltersRabatte = new ArrayList(1000);
            allFiltersPayment = new ArrayList(1000);
            allFiltersKoTraeger = new ArrayList(1000);

            Iterator<BuhaBooking> iterBookings = getBookings().iterator();
            while (iterBookings.hasNext()) {

                BuhaBooking booking = iterBookings.next();
                Collection<BuhaFilter> filters = booking.getBuhaFilterCollection();

                Iterator<BuhaFilter> iterFilter = filters.iterator();
                while (iterFilter.hasNext()) {
                    BuhaFilter filter = iterFilter.next();
                    allFilters.add(filter);
                    if (filter.getBuhaBooking().getBuhaArea().getId().intValue() == 1) {
                        allFiltersAR.add(filter);
                    }
                    if (filter.getBuhaBooking().getBuhaArea().getId().intValue() == 2) {
                        allFiltersER.add(filter);
                    }
                    if (filter.getBuhaBooking().getBuhaArea().getId().intValue() == 3) {
                        allFiltersPayment.add(filter);
                    }
                    if (filter.getBuhaBooking().getBuhaArea().getId().intValue() == 5) {
                        allFiltersKoTraeger.add(filter);
                    }
                    if (filter.getBuhaBooking().getBuhaArea().getId().intValue() == 6) {
                        allFiltersRabatte.add(filter);
                    }
                }
            }
        }
        return allFilters;
    }

    public  List<BuhaFilter> getFiltersAR() {
        getFilters();
        return allFiltersAR;
    }

    public  List<BuhaFilter> getFiltersER() {
        getFilters();
        return allFiltersER;
    }

    public  List<BuhaFilter> getFiltersARRabatte() {
        getFilters();
        return allFiltersRabatte;
    }
    
    public  List<BuhaFilter> getFiltersPayment() {
        getFilters();
        return allFiltersPayment;
    }

    public  List<BuhaFilter> getFiltersKoTraeger() {
        getFilters();
        return allFiltersKoTraeger;
    }


    public List<NukeMrcommerceCountry> getCountries() {
        if (allCountries == null) {
            NukeMrcommerceCountryJpaController countryJpaController = new NukeMrcommerceCountryJpaController();
            allCountries = countryJpaController.findNukeMrcommerceCountryEntities();
            countryMap = new HashMap();

                Iterator<NukeMrcommerceCountry> iterFilter = allCountries.iterator();
                while (iterFilter.hasNext()) {
                    NukeMrcommerceCountry country = iterFilter.next();
                    countryMap.put(country.getIsoCode(), country);
                }

        }
        return allCountries;
    }

    public String get2LetterCountry(String iso3LetterCountry, Logger lg, String messageDetail) {
        getCountries();
        NukeMrcommerceCountry country = countryMap.get(iso3LetterCountry);
        if (country == null) {
            lg.log(Level.SEVERE, "Land ISOCode nicht gefunden: " + iso3LetterCountry + " : " + messageDetail);
            country = countryMap.get("AUT");
        }
        return country.getIso2CodeBmd();
    }
    
    public String get3LetterCountryFromName(String countryName, Logger lg, String messageDetail) {
        Iterator<NukeMrcommerceCountry> iterCountry = getCountries().iterator();
        while (iterCountry.hasNext()) {
            NukeMrcommerceCountry country = iterCountry.next();
            if (country.getDescription().equals(countryName)) {
                return country.getIsoCode();
            }
        }
        lg.log(Level.SEVERE, "Land (Name) nicht gefunden: " + countryName + " : " + messageDetail);
        return Globals.EMPTYSTRING;
        
    }
    
    public List<BuhaArea> getAreas() {
        if (allAreas == null) {
            allAreas = areaJPAController.findBuhaAreaEntities();
        }
        return allAreas;
    }
    
    public BuhaAreaJpaController getAreaJPAController() {
        return areaJPAController;
    }
    
    public BuhaBookingJpaController getBookingJpaController() {
        return bookingJPAController;
    }

    public ArrayList<BuhaRabatt> getRabatte() {
        if (allRabatte == null) {
            BuhaRabattJpaController rabattJpaController = new BuhaRabattJpaController();
            List<BuhaRabatt> rabatte = rabattJpaController.findBuhaRabattEntities();
            allRabatte = new ArrayList();
            allRabatte.addAll(rabatte);
        }
        return allRabatte;
    }

    public ArrayList<BuhaKostl> getKostl() {
        if (allKostl == null) {
            BuhaKostlJpaController kostlJpaController = new BuhaKostlJpaController();
            allKostl = new ArrayList();
            allKostl.addAll(kostlJpaController.findBuhaKostlEntities());
        }
        Collections.sort(allKostl);

        return allKostl;
    }

    public BuhaProperties getProperty(String name) {
        if (name == null)
            return null;
        
        if (name.startsWith("PRE")) {
            return getPropertiesPre().get(name);
        } else {
            return getPropertiesNotPre().get(name);
        }
    }

    public void setProperty(String name, String value) {
        BuhaProperties prop = getProperty(name);
        if (prop == null) {
            prop = new BuhaProperties();
            prop.setName(name);
            prop.setValue(value);
            if (name.startsWith("PRE")) {
                getPropertiesPre().put(name, prop);
            } else {
                getPropertiesNotPre().put(name, prop);
            }
        }
        prop.setValue(value);
        try {
            propertiesJpaController.edit(prop);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCSVController(CSVController cSVController) {
        this.cSVController = cSVController;
    }

    public CSVController getCSVController() {
        return cSVController;
    }
    
    public LoggingHandler getLoggingHandler() {
        return loggingHandler;
    }
    
    public void setLoggingHandler(LoggingHandler loggingHandler) {
        this.loggingHandler = loggingHandler;
    }
    
    
    public OrganiserInvoiceJpaController getOrganiserInvoiceJpaController() {
        return organiserInvoiceJpaController;
    }

    public NukeMrcommerceItemsJpaController getItemsJpaController() {
        return itemsJpaController;
    }

    public NukeMrcommerceUser2eventJpaController getUser2eventJpaController() {
        return user2eventJpaController;
    }
    
    public InvoiceItemJpaController getInvoiceItemJpaController() {
        return invoiceItemJpaController;
    }

    public NukeMrcommerceSaleschannelinvoiceJpaController getSaleschannelinvoiceJpaController() {
        return saleschannelinvoiceJpaController;
    }

    public NukeMrcommerceOrganiserJpaController getOrganiserJpaController() {
        return organiserJpaController;
    }

//    public NukeMrcommerceArrangementJpaController getArrangementJpaController() {
//        return arrangementJpaController;
//    }

    public InvoiceJpaController getInvoiceJpaController() {
        return invoiceJpaController;
    }

    public  NukeCooperationInvoiceDetailsJpaController getCooperationInvoiceDetailsJpaController() {
        return cooperationInvoiceDetailsJPAController;
    }
    
    public VNukeCooperationDeliverynoteCountsJpaController getCooperationDeliverynoteCountsJpaController() { 
        return CooperationDeliverynoteCountsJpaController;
    }
    
    public VNukeCooperationDeliverynoteDetailsJpaController getCooperationDeliverynoteDetailsJpaController() { 
        return CooperationDeliverynoteDetailsJpaController;
    }
    
    public NukeCooperationDeliverynoteAdditionalfieldsJpaController getCooperationDeliverynoteAdditionalfieldsJpaController() {
        return cooperationDeliverynoteAdditionalfieldsJpaController;
    }
            
    
    public NukeMrcommerceEventsArrangementCitiesJpaController getEventsArrangementCitiesJpaController() {
        return eventsArrangementCitiesJpaController;
    }

    public NukeMrcommerceArrangementCityJpaController getArrangementCityJpaController() {
        return arrangementCityJpaController;
    }
    
    public PartnerJpaController getPartnerJpaController() {
        return partnerJpaController;
    }
     
    
    private HashMap<String, BuhaProperties> getPropertiesNotPre() {
        if (propertiesMapNotPre == null) {
            propertiesMapNotPre = new HashMap();
//            List<BuhaProperties> properties = propertiesJpaController.findBuhaPropertiesEntities();
            List<BuhaProperties> properties = propertiesJpaController.findBuhaPropertiesNotPre();
            for(BuhaProperties p : properties) {
                propertiesMapNotPre.put(p.getName(), p);
            }
        }
        return propertiesMapNotPre;
    }

        private HashMap<String, BuhaProperties> getPropertiesPre() {
        if (propertiesMapPre == null) {
            propertiesMapPre = new HashMap();
//            List<BuhaProperties> properties = propertiesJpaController.findBuhaPropertiesEntities();
            List<BuhaProperties> properties = propertiesJpaController.findBuhaPropertiesPre();
            for(BuhaProperties p : properties) {
                propertiesMapPre.put(p.getName(), p);
            }
        }
        return propertiesMapPre;
    }

}
