/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.control;

import at.jollydays.booking.Globals;
import at.jollydays.booking.bo.BuhaBooking;
import at.jollydays.booking.bo.BuhaFilter;
import at.jollydays.booking.bo.Invoice;
import at.jollydays.booking.bo.InvoiceItem;
import at.jollydays.booking.bo.NukeMrcommerceOrganiser;
import at.jollydays.booking.bo.NukeMrcommerceSaleschannelinvoice;
import at.jollydays.booking.bo.NukeMrcommerceUser2event;
import at.jollydays.booking.bo.OrganiserInvoice;
import at.jollydays.booking.bo.OrganiserInvoiceItem;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Gunter Reinitzer
 */
public class BookingER extends BookingERWriter {
    
    //private CSVController cSVController;
    private ConfigurationController configurationController;
    
    BookingER(ConfigurationController configurationController) {
        this.configurationController = configurationController;
    }    

    public int processERPacket(int startInvoice, int endInvoice, int maxResults, boolean keyCaching) throws NothingFoundException, IOException {
        OrganiserInvoice organiserInvoice = null;
        List<OrganiserInvoice> invoices = configurationController.getOrganiserInvoiceJpaController().findOrganiserInvoiceEntitiesNext(maxResults, startInvoice);

        Iterator<OrganiserInvoice> iterInvoices = invoices.iterator();
        while (iterInvoices.hasNext()) {
            organiserInvoice = iterInvoices.next();

            if (endInvoice > 0 && endInvoice < organiserInvoice.getId()) {
                break;
            }
            String messageDetail = " Rechnung: " + organiserInvoice.getInvoiceNumber() + " / " + organiserInvoice.getId();

            configurationController.getLoggingHandler().getLogger(Globals.LOGGINGER).log(Level.INFO, messageDetail);

            NukeMrcommerceOrganiser organiser = configurationController.getOrganiserJpaController().findNukeMrcommerceOrganiser(organiserInvoice.getOrganiserID());

            //String country = configurationController.get3LetterCountryFromName(organiser.getNukeOrgState(), configurationController.getLoggingHandler().getLogger(Globals.LOGGINGER), messageDetail);
            //String country = "AUT";
            
            String country = null;
            if (organiser.getNukeOrgPlantIsostate()==null || organiser.getNukeOrgPlantIsostate().equals(Globals.EMPTYSTRING)){
                country = configurationController.get3LetterCountryFromName(organiser.getNukeOrgState(), configurationController.getLoggingHandler().getLogger(Globals.LOGGINGER), messageDetail);
            } else {
                country = organiser.getNukeOrgPlantIsostate();
            }

            Iterator<OrganiserInvoiceItem> iterInvoiceItems = organiserInvoice.getInvoiceItemCollection().iterator();

            int zeilenvorher = configurationController.getCSVController().getWriterER().getRecordCount();
            configurationController.getCSVController().getWriterER().setDeferred(true);
            double sum = 0.0;
            String countryBuhaCode = "0";

            while (iterInvoiceItems.hasNext()) {
                OrganiserInvoiceItem organiserInvoiceItem = iterInvoiceItems.next();
                //System.out.println("Rechnung: " + organiserInvoice.getInvoiceNumber() + " Item: " + " Gutschein: ");

                if (organiserInvoiceItem.getCorrespondingBookingId() == null) {
                    configurationController.getLoggingHandler().getLogger(Globals.LOGGINGER).log(Level.SEVERE, "kein CorrespondingBookingId zum Item gefunden - Item " + organiserInvoiceItem.getId() + " in Rechnung " + organiserInvoice.getInvoiceNumber() + " nicht buchbar!");
                    continue;
                }

                NukeMrcommerceSaleschannelinvoice itemSalesChannelInvoice = null;
                try {
                    try {
                        NukeMrcommerceUser2event user2Event = configurationController.getUser2eventJpaController().findNukeMrcommerceUser2event(organiserInvoiceItem.getCorrespondingBookingId());
                        if (user2Event == null) {
                            configurationController.getLoggingHandler().getLogger(Globals.LOGGINGER).log(Level.SEVERE, "keinen user2Event zur Buchung gefunden - Item " + organiserInvoiceItem.getId() + " in Rechnung " + organiserInvoice.getInvoiceNumber() + " nicht buchbar!");
                            continue;
                        }
                        Integer itemID = user2Event.getIncInvoiceitemId();
                        //user2Event.getNukeEventId();
                        //NukeMrcommerceEvents ev = null;
                        //int arrCityId = user2Event.getEventsArrangementCities().getNukeMrcommerceEventsArrangementCitiesPK().getArrangementToCityId();

                        if (itemID != null) {
                            InvoiceItem invoiceItem = null;
                            InvoiceItem itemRabatt = null;

//                            if (keyCaching && configurationController.getProperty(Globals.PREFIXITEM + itemID) == null) {
//                                //neue ArrCity in Datei schreiben, weil noch nicht vorhanden
//                                NukeMrcommerceArrangementCity arrCity = arrangementCityJpaController.findNukeMrcommerceArrangementCity(eventArrCity.getNukeMrcommerceEventsArrangementCitiesPK().getArrangementToCityId());
//                                NukeMrcommerceItems item = itemsJpaController.findNukeMrcommerceItems(invoiceItem.getMrcommerceItemID());
//                                writeKTSatz(arrCity, item);
//                            }
                            //NukeMrcommerceItemsJpaController itemsJpaController = new NukeMrcommerceItemsJpaController();



                            if (organiserInvoiceItem.getCorrespondingItemRabattID() != null) {
                                itemRabatt = configurationController.getInvoiceItemJpaController().findInvoiceItem(organiserInvoiceItem.getCorrespondingItemRabattID());
                                //InvoiceItem invoiceItem = invoiceItemJpaController.findInvoiceItem(user2Event.getIncInvoiceitemId());
                                //itemRabatt = invoiceItem.getCorrespondingItemRabattID();
                                //if (itemRabatt != null && itemRabatt.getMrcommerceItemID() < 95000) {
                                //    itemRabatt = null;
                                //}
                                //if (itemRabatt.getMrcommerceItemID() < 95000) {
                                //lh.getLoggerER().log(Level.SEVERE, "Rabatt ohne RabattItem in AR gefunden - Item " + itemRabatt.getId() + " in Rechnung " + itemRabatt.getInvoiceID().getInvoiceNumber());
                                //}
                            }

                            //Entstehung des Gutscheins ermitteln - erster Beleg in der Historie - und SalesChannel dort ermitteln
                            if (user2Event.getIncInvoiceitemId() != null) {
                                invoiceItem = configurationController.getInvoiceItemJpaController().findInvoiceItem(user2Event.getIncInvoiceitemId());
                                InvoiceItem firstInvoiceItem = configurationController.getInvoiceItemJpaController().findFirstInvoiceItemByUniqueNumber(invoiceItem.getUniqueNumber());
                                if (invoiceItem != null) {
                                    if (firstInvoiceItem.getOriginInvoiceItemId() != null && firstInvoiceItem.getOriginInvoiceItemId() > 0) {
                                        firstInvoiceItem = configurationController.getInvoiceItemJpaController().findInvoiceItem(firstInvoiceItem.getOriginInvoiceItemId());
                                    }
                                    if (itemRabatt == null) {
                                        itemRabatt = firstInvoiceItem.getCorrespondingItemRabattID();
                                    }
                                    itemSalesChannelInvoice = configurationController.getSaleschannelinvoiceJpaController().findNukeMrcommerceSaleschannelinvoice(firstInvoiceItem.getInvoiceID().getId());
                                }
                            }


//                            NukeMrcommerceEventsArrangementCities eventArrCity = eventsArrangementCitiesJpaController.findNukeMrcommerceEventsArrangementCitiesByEvent(user2Event.getNukeEventId());
//                            NukeMrcommerceArrangementCity arrCity = arrangementCityJpaController.findNukeMrcommerceArrangementCity(eventArrCity.getNukeMrcommerceEventsArrangementCitiesPK().getArrangementToCityId());
//                            NukeMrcommerceItems item = itemsJpaController.findNukeMrcommerceItems(invoiceItem.getMrcommerceItemID());
//
//                            if (eventArrCity != null && configurationController.getProperty(Globals.PREFIXARRCITY + arrCity.getId()) == null) {
//                                //neue ArrCity in Datei schreiben, weil noch nicht vorhanden
//                                writeKTSatz(arrCity, item);
//                            }
//
//
                            //Regel für den Veranstalter ermitteln
                            BuhaFilter filterVeranstalter = getFilterERVeranstalter(organiserInvoice, organiserInvoiceItem, invoiceItem, itemRabatt, country, user2Event);

                            //Kauf und Einlösung in unterschiedlichem Land - eigene Logik dafür
                            Auslandsfilter auslandsFilter = getFilterERAusland(organiserInvoice, organiserInvoiceItem, invoiceItem, itemRabatt, country);

                            if (filterVeranstalter != null) {
                                BuhaBooking bookingVeranstalter = filterVeranstalter.getBuhaBooking();
                                countryBuhaCode = bookingVeranstalter.getBuhaCountry();
                                // CSV schreiben, wir haben organiserInvoice, organiserInvoiceItem, booking, filter,
                                // Da Kunde aus anderem Land kommen kann, zwei Buchungsregeln ermitteln und richtig einsetzen
                                sum+= writeERSatz0(configurationController, organiserInvoice, organiserInvoiceItem, bookingVeranstalter, invoiceItem, itemRabatt, country, auslandsFilter, user2Event, itemSalesChannelInvoice,  keyCaching);
                                //writeERSatz1(writerER, organiserInvoice, organiserInvoiceItem, bookingVeranstalter, invoiceItem, itemRabatt, country, itemSalesChannelInvoice, lh);
                            } else {
                                // TODO: Errorlog checken
                                configurationController.getLoggingHandler().getLogger(Globals.LOGGINGER).log(Level.SEVERE, "keinen Filter gefunden - Item " + organiserInvoiceItem.getId() + " in Rechnung " + organiserInvoice.getInvoiceNumber() + " nicht buchbar!");
                            }

                        } else {
                            configurationController.getLoggingHandler().getLogger(Globals.LOGGINGER).log(Level.SEVERE, "keinen IncInvoiceItem zu Event gefunden - User2Event: " + user2Event.getId() + "  Item: " + organiserInvoiceItem.getId() + " in Rechnung " + organiserInvoice.getInvoiceNumber() + " nicht buchbar!");

                        }
                    } catch (IOException ex) {
                        configurationController.getLoggingHandler().getLogger(Globals.LOGGINGER).log(Level.SEVERE, null, ex);
                    }
                } catch (Throwable th) {
                    configurationController.getLoggingHandler().getLogger(Globals.LOGGINGER).log(Level.SEVERE, "schwerer Fehler - Item " + organiserInvoiceItem.getId() + " in Rechnung " + organiserInvoice.getInvoiceNumber() + " nicht buchbar!");
                    //SCHWERWIEGEND: keinen IncInvoiceItem zu Event gefunden - Item 48416 iPartner()n Rechnung 001175110 nicht buchbar!
                    //SELECT min(`OrganiserInvoice`.`ID`), count(*) FROM `incentro`.`OrganiserInvoice`
                    //where`OrganiserInvoice`.`InvoiceDateTime` > '2010-07-01' and `OrganiserInvoice`.`InvoiceDateTime` < '2010-08-01' limit 10000;

                    th.printStackTrace();
                }

            }
            if (configurationController.getCSVController().getWriterER().isDeferred()) {
                writerERSplitFirstLine(configurationController, organiserInvoice, countryBuhaCode, sum, "O");
                configurationController.getCSVController().getWriterER().deferredFlush();
            }

            //Stammdaten nur schreiben wenn Zeilen hinzugefügt wurden
            int zeilennachher = configurationController.getCSVController().getWriterER().getRecordCount();
            if (zeilennachher - zeilenvorher > 0) {
                writeCRSatz(configurationController, organiser);
            }

        }

        if (organiserInvoice != null) {
            return organiserInvoice.getId();
        }
        throw new NothingFoundException();


    }
    

    public int processKreditor(ConfigurationController configurationController, int startValue, int maxResults, boolean all) throws IOException {

        NukeMrcommerceOrganiser organiser = null;
        int startCustomer = 0;
        //int resultLength = 0;

        List<NukeMrcommerceOrganiser> organisers = configurationController.getOrganiserJpaController().findNukeMrcommerceOrganiserEntities(maxResults, startValue);
        //resultLength = organisers.size();
        //System.out.println(organisers.size());

        Iterator<NukeMrcommerceOrganiser> iterOrganiser = organisers.iterator();
        while (iterOrganiser.hasNext()) {
            organiser = iterOrganiser.next();
            //System.out.println(organiser.getNukeorgID().intValue());
            startCustomer = organiser.getNukeorgID().intValue() + 1;
            writeCRSatz(configurationController, organiser);
        }

        if (organiser != null) {
            return startCustomer;
        }
        return 0;
    }
    

    protected BuhaFilter getFilterERVeranstalter(OrganiserInvoice organiserInvoice, OrganiserInvoiceItem organiserInvoiceItem, InvoiceItem invoiceItem, InvoiceItem itemRabatt, String country, NukeMrcommerceUser2event user2Event) {
        BuhaFilter filter = null;

        Iterator<BuhaFilter> iterFilter = configurationController.getFiltersER().iterator();
        while (iterFilter.hasNext()) {
            filter = iterFilter.next();
            if ((filter.getCountry().trim().equals(Globals.EMPTYSTRING) || filter.getCountry().equals(country))
                    && filter.getItemFrom() <= invoiceItem.getMrcommerceItemID().intValue()
                    && filter.getItemTo() >= invoiceItem.getMrcommerceItemID().intValue()
                    && filter.getBesorger() == organiserInvoice.getBesorger()
                    && checkGutscheinFilter(filter, invoiceItem.getUniqueNumber())
                    && filter.getDonotaccount() == (user2Event.getOrganiserDonotaccount() > 0)
                    && (filter.getCategory() == null || 
                        filter.getCategory().trim().equals(Globals.EMPTYSTRING) || 
                        filter.getCategory().trim().equals(Integer.toString(organiserInvoice.getOrderType())))
                ) {
                break;
            }
            filter = null;
        }
        return filter;
    }

    // Korrekturbuchung wenn Kauf und Einlösung in verschiedenen Ländern
    protected Auslandsfilter getFilterERAusland(OrganiserInvoice organiserInvoice, OrganiserInvoiceItem organiserInvoiceItem, InvoiceItem invoiceItem, InvoiceItem itemRabatt, String country) {
        BuhaFilter filter = null;
        Iterator<BuhaFilter> iterFilter;
        Invoice invoice = invoiceItem.getInvoiceID();
        String countryInvoice = invoice.getPartner().getAddress().getISOState();
        Auslandsfilter auslandsfilter = new Auslandsfilter();

        iterFilter = configurationController.getFiltersER().iterator();
        while (iterFilter.hasNext()) {
            filter = iterFilter.next();
            if ( countryInvoice != null && !countryInvoice.equals(Globals.EMPTYSTRING) && (filter.getCountry().equals(countryInvoice))
                    && filter.getItemFrom() <= invoiceItem.getMrcommerceItemID().intValue()
                    && filter.getItemFrom() <= invoiceItem.getMrcommerceItemID().intValue()
                    && filter.getBesorger() == organiserInvoice.getBesorger()
                    && checkGutscheinFilter(filter, "AUSL")
                    && filter.getDonotaccount() == false) {
                break;
            }
            filter = null;
        }
        auslandsfilter.setInvoiceFilter(filter);
        if (filter == null) {
            return null;
        }

        iterFilter = configurationController.getFiltersER().iterator();


        while (iterFilter.hasNext()) {
            filter = iterFilter.next();
            if ((filter.getCountry().equals(country))
                    && filter.getItemFrom() <= invoiceItem.getMrcommerceItemID().intValue()
                    && filter.getItemFrom() <= invoiceItem.getMrcommerceItemID().intValue()
                    && filter.getBesorger() == organiserInvoice.getBesorger()
                    && checkGutscheinFilter(filter, "AUSL")
                    && filter.getDonotaccount() == false) {
                break;
            }
            filter = null;
        }
        auslandsfilter.setOrganiserInvoiceFilter(filter);
        if (filter == null) {
            return null;
        }

        if (auslandsfilter.getInvoiceFilter().getCountry().equals(auslandsfilter.getOrganiserInvoiceFilter().getCountry())) {
            return null;
        }

        return auslandsfilter;
    }

    protected BuhaFilter getFilterERKunde(OrganiserInvoice organiserInvoice, OrganiserInvoiceItem organiserInvoiceItem, InvoiceItem invoiceItem, InvoiceItem itemRabatt, String country) {
        BuhaFilter filter = null;
        Iterator<BuhaFilter> iterFilter = configurationController.getFiltersER().iterator();
        while (iterFilter.hasNext()) {
            filter = iterFilter.next();
            if ((filter.getCountry().equals(country))
                    && filter.getItemFrom() <= invoiceItem.getMrcommerceItemID().intValue()
                    && filter.getItemFrom() <= invoiceItem.getMrcommerceItemID().intValue()
                    && filter.getBesorger() == organiserInvoice.getBesorger() //&& filter.getDonotaccount() == (user2Event.getOrganiserDonotaccount() > 0
                    ) {
                break;
            }
            filter = null;
        }
        return filter;
    }

    protected BuhaFilter getFilterERRabatt(int itemID, String country) {
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
    
}
