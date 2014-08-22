/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.control;

import at.jollydays.booking.Globals;
import at.jollydays.booking.bo.Address;
import at.jollydays.booking.bo.BuhaBooking;
import at.jollydays.booking.bo.BuhaFilter;
import at.jollydays.booking.bo.BuhaKostl;
import at.jollydays.booking.bo.BuhaRabatt;
import at.jollydays.booking.bo.Invoice;
import at.jollydays.booking.bo.InvoiceItem;
import at.jollydays.booking.bo.NukeMrcommerceArrangementCity;
import at.jollydays.booking.bo.NukeMrcommerceItems;
import at.jollydays.booking.bo.NukeMrcommerceSaleschannelinvoice;
import at.jollydays.booking.bo.Partner;
import at.jollydays.booking.bo.VNukeCooperationDeliverynoteCounts;
import at.jollydays.booking.bo.VNukeCooperationDeliverynoteDetails;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;


/**
 *
 * @author Gunter Reinitzer
 */
public class BookingARWriter extends BookingHelper {
    protected HashMap partnerMap = new HashMap();
    
    short buHaSS = Globals.NOT_PROCESSED;
    
    protected double writeARSatz0(ConfigurationController configurationController, Invoice invoice, InvoiceItem invoiceItem, BuhaBooking booking, NukeMrcommerceItems item, NukeMrcommerceSaleschannelinvoice salesChannelInvoice, BuhaFilter filterKoTraeger, boolean keyCaching) throws IOException {

        JDCsvWriter writer = configurationController.getCSVController().getWriterAR();
        
        if (booking.getSplitbuchung())
            writer.setDeferred(true);
        String gegenbuchKz = "E";
        if (writer.isDeferred()) {
            gegenbuchKz = "O";
        }        

        double amount = new Double(invoiceItem.getAmounteur().toPlainString());
        double taxrate = new Double(invoiceItem.getTaxRate() != null ? invoiceItem.getTaxRate() : 0);
        if (booking.getNegativBuchen()) {
            amount = -amount;
        }

        double bookingamount = amount;
        if (booking.getBetragNetto()) {
            bookingamount = amount * 100 / (taxrate + 100);
        }

        if (booking.getSteuerIgnorieren()) {
            taxrate = 0.0;
        }
        double tax = amount * taxrate / (taxrate + 100);

        int konto1 = 0;
        int konto2 = 0;
        int gegenkonto2 = 0;
        int gegenkonto1 = 0;
        int debitor = invoice.getPartnerID() + Globals.DEBITORSTARTVALUE;
        String debitorNr = Globals.EMPTYSTRING;
        
        // bei Coop Abrechnungen nicht die Partnernummer(neu je Abrechnugn) sondern die CoopPartner Nummer eintragen
        if (configurationController.getCooperationInvoiceDetailsJpaController().findByNukeCooperationinvoiceInvoice(invoice.getId()).size() > 0) {
                            Integer coop = configurationController.getCooperationInvoiceDetailsJpaController().findByNukeCooperationinvoiceInvoice(invoice.getId()).get(0).getNukeCooperationInvoiceDetailsPK().getNukeCooperationinvoiceCooperation();
                            if (configurationController.getCooperationDeliverynoteDetailsJpaController().findVNukeCooperationDeliverynoteDetails(coop) != null) {
                                VNukeCooperationDeliverynoteDetails coopPartner = configurationController.getCooperationDeliverynoteDetailsJpaController().findVNukeCooperationDeliverynoteDetails(coop);                
                                //debitorNr = "4" + leftPad(coopPartner.getNukeCooperationuserBmdId(), 6, "0");
                                if (coopPartner.getNukeCooperationuserBmdId() != null) {
                                    debitorNr = "" + coopPartner.getNukeCooperationuserBmdId();
                                    System.out.println("Partner mit CoopPartner Daten in Debitor eintragen CoopNr.: " + coopPartner.getNukeCooperationId() + " BMD ID: " + debitorNr);
                                    
                                    try {
                                        debitor = new Integer(debitorNr);  
                                    } catch (NumberFormatException e) {
                                        configurationController.getLoggingHandler().getLogger(Globals.LOGGINGAR).log(Level.WARNING, "CoopPartner CoopNr.: " + coopPartner.getNukeCooperationId() + " hat keine BMD ID eingetragen!");
                                        //Hurra, alles wieder anders wenn keine BMD_ID hinterlegt doch die PartnerID verwenden... (basierend auf Mantis 2407)
                                        debitor = invoice.getPartnerID() + Globals.DEBITORSTARTVALUE;
                                    }   
                                }

                            }
        } 
        
        
        
        double splitsum = amount;

        if (booking.getKonto() == 0 && booking.getDebitor()) {
            konto1 = debitor;
        } else {
            konto1 = booking.getKonto();
        }

        if (booking.getGegenkonto() == 0 && booking.getDebitor()) {
            gegenkonto2 = debitor;
        } else {
            gegenkonto2 = booking.getGegenkonto();
        }

        if (booking.getZwischenkonto() == 0 && booking.getDebitor() && booking.getKonto() > 0 && booking.getGegenkonto() > 0) {
            gegenkonto1 = debitor;
        } else {
            gegenkonto1 = booking.getZwischenkonto();
        }
        konto2 = gegenkonto1;

        if (booking.getKonto() > 0 && booking.getZwischenkonto() > 0 && booking.getGegenkonto() > 0 && booking.getDebitor()) {
            konto2 = gegenkonto2;
            gegenkonto2 = debitor;
        }

        
       
        if (configurationController.getCooperationInvoiceDetailsJpaController().findByNukeCooperationinvoiceInvoice(invoice.getId()).size() > 0 && Globals.REVERSECHARGEITEMS.contains(invoiceItem.getMrcommerceItemID().intValue())) {
                Integer coop = configurationController.getCooperationInvoiceDetailsJpaController().findByNukeCooperationinvoiceInvoice(invoice.getId()).get(0).getNukeCooperationInvoiceDetailsPK().getNukeCooperationinvoiceCooperation();
                if (configurationController.getCooperationDeliverynoteDetailsJpaController().findVNukeCooperationDeliverynoteDetails(coop) != null) {
                    short revChargeFlag = configurationController.getCooperationDeliverynoteDetailsJpaController().findVNukeCooperationDeliverynoteDetails(coop).getNukeCooperationProvisionIsBrutto();
                    if (revChargeFlag == Globals.REVERSECHARGE) {
                        tax = amount * (Globals.REVERSECHARGE_TAXRATE/100) * (-1);
                    }  
                }
            }

        int specialDatesDeliverynote = 0;
        String auftrJahr;
        String auftrMonat;
        Date specialInvoiceDate = new Date();
        
        if (configurationController.getCooperationDeliverynoteAdditionalfieldsJpaController().findNukeCooperationDeliverynoteAdditionalfields(invoice.getId()) != null) {
            String auftrPeriode = configurationController.getCooperationDeliverynoteAdditionalfieldsJpaController().findNukeCooperationDeliverynoteAdditionalfields(invoice.getId()).getNukeCooperatiodeliverynoteadditionalfieldsPeriod();
            if (auftrPeriode != Globals.EMPTYSTRING && auftrPeriode != null && auftrPeriode.length() > 0) {
                try {
                    specialDatesDeliverynote = 1;
                    auftrJahr = auftrPeriode.substring(0, 4);
                    auftrMonat = auftrPeriode.substring(4,6);
                    Calendar calendar;
                    calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    calendar.setTime(sdf.parse(auftrJahr+auftrMonat+"01"));// all done
                    calendar.add(Calendar.MONTH, 1);
                    calendar.add(Calendar.DATE, -1);
                    specialInvoiceDate = calendar.getTime();
                } catch (ParseException ex) {
                    Logger.getLogger(BookingARWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
             
            } 
        } 

        if (invoiceItem.getBesorger() && !booking.getForcevermittler()) {
            
            // Besorger Modell
            double besorgeramount = 0.0;
            if (invoiceItem.getMrcommerceItemID().intValue() > Globals.GUTSCHEINMAXITEMNR) {
                besorgeramount = bookingamount;
            } else {
                besorgeramount = bookingamount * Globals.BESORGERRUECKSTELLUNG;
            }
            
            if (writer.isDeferred()) {
                // Splitbuchung buhaCountry
                splitsum = 0.0;

                // erste Buchungszeile
                if (booking.getKonto() > 0) {
                    writer.write("0");                                              //1
                    writer.write(invoice.getInvoiceNumber());
                    writer.write(Integer.toString(booking.getKonto()));
                    writer.write(Integer.toString(debitor));
                    writer.write(format.format(invoice.getInvoiceDate()));          //5
                    writer.write(prepareStringForCSV(item.getNukeItemName()));
                    writer.write(invoiceItem.getUniqueNumber());
                    writer.write("");
                    writer.write("");
                    writer.write(format.format(invoice.getCreationDate()));         //10
                    writer.write(booking.getBuhaCountry());                         
                    writer.write("AR");
                    writer.write(Short.toString(booking.getBuchungscode()));

                    writer.write("0");
                    writer.write(roundScale2(besorgeramount));                      //15
                    writer.write("0");
                    writer.write("0");
                    writer.write("0");
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate()));
                    writer.write(gegenbuchKz);                                              //20
                    writer.write("A");
                    //writer.write("");
                    writer.endRecord();
                    writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, besorgeramount, keyCaching);
                    splitsum = besorgeramount;
                }

                // zweite Buchungszeile
                if (booking.getZwischenkonto() > 0) {
                    writer.write("0");                                              //1
                    writer.write(invoice.getInvoiceNumber());
                    writer.write(Integer.toString(booking.getZwischenkonto()));
                    writer.write(Integer.toString(debitor));
                    writer.write(format.format(invoice.getInvoiceDate()));          //5
                    writer.write(prepareStringForCSV(item.getNukeItemName()));
                    writer.write(invoiceItem.getUniqueNumber());
                    writer.write("");
                    writer.write("");
                    writer.write(format.format(invoice.getCreationDate()));         //10
                    writer.write(booking.getBuhaCountry());
                    writer.write("AR");
                    writer.write(Short.toString(booking.getBuchungscode2()));

                    writer.write("0");
                    writer.write(roundScale2(-besorgeramount));                       //15
                    writer.write("0");
                    writer.write("0");
                    writer.write(Integer.toString(booking.getSteuercode()));
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate()));
                    writer.write(gegenbuchKz);                                              //20
                    writer.write("A");
                    //writer.write("");
                    writer.endRecord();
                    writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, -besorgeramount, keyCaching);
                    splitsum += -besorgeramount;
                }

                // dritte Buchungszeile
                if (booking.getGegenkonto() > 0) {
                    writer.write("0");                                              //1
                    writer.write(invoice.getInvoiceNumber());
                    writer.write(Integer.toString(booking.getGegenkonto()));
                    writer.write(Integer.toString(debitor));
                    writer.write(format.format(invoice.getInvoiceDate()));          //5
                    writer.write(prepareStringForCSV(item.getNukeItemName()));
                    writer.write(invoiceItem.getUniqueNumber());
                    writer.write("");
                    writer.write("");
                    writer.write(format.format(invoice.getCreationDate()));         //10
                    writer.write(booking.getBuhaCountry());
                    writer.write("AR");
                    writer.write(Short.toString(booking.getBuchungscode2()));

                    writer.write(roundScale2(taxrate));
                    writer.write(roundScale2(-bookingamount));                       //15
                    writer.write("0");
                    writer.write(roundScale2(-tax));
                    writer.write(Integer.toString(booking.getSteuercode()));
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate()));
                    writer.write(gegenbuchKz);                                              //20
                    writer.write("A");
                    //writer.write("");
                    writer.endRecord();
                    writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, -bookingamount, keyCaching);
                    if (booking.getSteuercode() != 79) 
                        splitsum += -bookingamount;
                    else   
                        splitsum += -bookingamount -tax;
                }
                
                
            } else if (gegenkonto1 == 0) {
                // normale Buchung ohne Zwischenkonto
                
                tax = -tax;

                writer.write("0");                                                  //1
                writer.write(invoice.getInvoiceNumber());
                writer.write(Integer.toString(konto1));
                writer.write(Integer.toString(gegenkonto2));
                //if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getInvoiceDate())); 
                //} else {
                //    writer.write(format.format(specialInvoiceDate)); 
                //}                
             //5
                writer.write(prepareStringForCSV(item.getNukeItemName()));
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
                //if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getCreationDate())); 
                //} else {
                //    writer.write(format.format(specialInvoiceDate)); 
                //} //10
                writer.write(booking.getBuhaCountry());
                writer.write("AR");
                writer.write(Short.toString(booking.getBuchungscode()));

                writer.write(roundScale2(taxrate));
                writer.write(roundScale2(besorgeramount));                            //15
                writer.write("0");
                writer.write(roundScale2(tax));
                writer.write(Integer.toString(booking.getSteuercode()));
                if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
                writer.write(gegenbuchKz);                                                  //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, besorgeramount, keyCaching);

            } else {
                // doppelte Buchung mit Zwischenkonto

                // erste Buchungszeile
                writer.write("0");                                              //1
                writer.write(invoice.getInvoiceNumber());
                writer.write(Integer.toString(konto1));
                writer.write(Integer.toString(gegenkonto1));
                //if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getInvoiceDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }         //5
                writer.write(prepareStringForCSV(item.getNukeItemName()));
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
                //if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getCreationDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }         //10
                writer.write(booking.getBuhaCountry());
                writer.write("AR");
                writer.write(Short.toString(booking.getBuchungscode()));

                writer.write("0");
                writer.write(roundScale2(besorgeramount));                      //15
                writer.write("0");
                writer.write("0");
                writer.write("0");
                if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
                writer.write(gegenbuchKz);                                              //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, besorgeramount, keyCaching);

                // zweite Buchungszeile
                writer.write("0");                                              //1
                writer.write(invoice.getInvoiceNumber());
                writer.write(Integer.toString(konto2));
                writer.write(Integer.toString(gegenkonto2));
                //if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getInvoiceDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }          //5
                writer.write(prepareStringForCSV(item.getNukeItemName()));
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
//                if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getCreationDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }          //10
                writer.write(booking.getBuhaCountry());
                writer.write("AR");
                writer.write(Short.toString(booking.getBuchungscode2()));

                writer.write(roundScale2(taxrate));
                writer.write(roundScale2(bookingamount));                       //15
                writer.write("0");
                writer.write(roundScale2(tax));
                writer.write(Integer.toString(booking.getSteuercode()));
                if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
                writer.write(gegenbuchKz);                                              //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, bookingamount, keyCaching);
            }

        } else {
            //Vermittler Modell


            if (gegenkonto1 == 0) {
                // normale Buchung ohne Zwischenkonto

                writer.write("0");                                                  //1
                writer.write(invoice.getInvoiceNumber());
                writer.write(Integer.toString(konto1));
                writer.write(Integer.toString(gegenkonto2));
//                if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getInvoiceDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }              //5
                writer.write(prepareStringForCSV(item.getNukeItemName()));
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
//                if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getCreationDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }             //10
                writer.write(booking.getBuhaCountry());
                writer.write("AR");
                writer.write(Short.toString(booking.getBuchungscode()));

                writer.write(roundScale2(taxrate));
                writer.write(roundScale2(bookingamount));                            //15
                writer.write("0");
                writer.write(roundScale2(tax));
                writer.write(Integer.toString(booking.getSteuercode()));
                if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
                writer.write(gegenbuchKz);                                                  //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, bookingamount, keyCaching);
                if (booking.getSteuercode() != 79)
                    splitsum = bookingamount + tax;
                else
                    splitsum = bookingamount;

            } else if (writer.isDeferred()) {  
                // Splitbuchung: doppelte Buchung mit Gegenkonto Debitor

                // erste Buchungszeile
                writer.write("0");                                              //1
                writer.write(invoice.getInvoiceNumber());
                writer.write(Integer.toString(booking.getKonto()));
                writer.write(Integer.toString(debitor));
//                if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getInvoiceDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }           //5
                writer.write(prepareStringForCSV(item.getNukeItemName()));
                //writerER.write("");
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
//                if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getCreationDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }           //10
                writer.write(booking.getBuhaCountry());
                writer.write("AR");
                writer.write(Short.toString(booking.getBuchungscode()));

                writer.write(roundScale2(taxrate));
                writer.write(roundScale2(-bookingamount));                       //15
                writer.write("0");
                writer.write(roundScale2(-tax));
                writer.write(Integer.toString(booking.getSteuercode()));
                if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
                writer.write(gegenbuchKz);                                                  //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, -bookingamount, keyCaching);
                if (booking.getSteuercode() != 79)
                    splitsum = -bookingamount - tax;
                else
                    splitsum = -bookingamount;

                // zweite Buchungszeile
                writer.write("0");                                                      //1
                writer.write(invoice.getInvoiceNumber());
                writer.write(Integer.toString(booking.getGegenkonto()));
                writer.write(Integer.toString(debitor));
//                if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getInvoiceDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }                  //5
                writer.write(prepareStringForCSV(item.getNukeItemName()));
                //writerER.write("");
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
//                if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getCreationDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }                //10
                writer.write(booking.getBuhaCountry());
                writer.write("AR");
                writer.write(Short.toString(booking.getBuchungscode2()));

                writer.write(roundScale2(taxrate));
                writer.write(roundScale2(bookingamount));                            //15
                writer.write("0");
                writer.write(roundScale2(tax));
                writer.write(Integer.toString(booking.getSteuercode()));
                if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
                writer.write(gegenbuchKz);                                                      //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, bookingamount, keyCaching);
                if (booking.getSteuercode() != 79)
                    splitsum += bookingamount + tax;
                else
                    splitsum += bookingamount;
                
            } else {
                // doppelte Buchung mit Zwischenkonto

                // erste Buchungszeile
                writer.write("0");                                              //1
                writer.write(invoice.getInvoiceNumber());
                writer.write(Integer.toString(konto1));
                writer.write(Integer.toString(gegenkonto1));
//                if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getInvoiceDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }        //5
                writer.write(prepareStringForCSV(item.getNukeItemName()));
                //writerER.write("");
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
//                if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getCreationDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }        //10
                writer.write(booking.getBuhaCountry());
                writer.write("AR");
                writer.write(Short.toString(booking.getBuchungscode()));

                writer.write(roundScale2(taxrate));
                writer.write(roundScale2(bookingamount));                       //15
                writer.write("0");
                writer.write(roundScale2(tax));
                writer.write(Integer.toString(booking.getSteuercode()));
                if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
                writer.write(gegenbuchKz);                                                  //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, bookingamount, keyCaching);

                // zweite Buchungszeile
                writer.write("0");                                                      //1
                writer.write(invoice.getInvoiceNumber());
                writer.write(Integer.toString(konto2));
                writer.write(Integer.toString(gegenkonto2));
//                if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getInvoiceDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }                   //5
                writer.write(prepareStringForCSV(item.getNukeItemName()));
                //writerER.write("");
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
//                if (specialDatesDeliverynote == 0) {
                    writer.write(format.format(invoice.getInvoiceDate())); 
//                } else {
//                    writer.write(format.format(specialInvoiceDate)); 
//                }                  //10
                writer.write(booking.getBuhaCountry());
                writer.write("AR");
                writer.write(Short.toString(booking.getBuchungscode2()));

                writer.write(roundScale2(taxrate));
                writer.write(roundScale2(bookingamount));                            //15
                writer.write("0");
                writer.write(roundScale2(tax));
                writer.write(Integer.toString(booking.getSteuercode()));
                if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
                writer.write(gegenbuchKz);                                                      //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, bookingamount, keyCaching);

            }

        }
        return splitsum;
    } 
    
    protected void writeARSatz1(ConfigurationController configurationController, Invoice invoice, InvoiceItem invoiceItem, NukeMrcommerceSaleschannelinvoice salesChannelInvoice, BuhaFilter filterKoTraeger, double amount, boolean keyCaching) throws IOException {

        JDCsvWriter writer = configurationController.getCSVController().getWriterAR();
        
        keyCaching = true;
        
        if (keyCaching && invoiceItem != null && invoiceItem.getArrangementcity_id() != null && invoiceItem.getArrangementcity_id().intValue() > 0 && configurationController.getProperty(Globals.PREFIXARRCITY + invoiceItem.getArrangementcity_id().intValue()) == null) {
            //neue KT-ArrCity in Datei schreiben, weil noch nicht vorhanden
            NukeMrcommerceArrangementCity arrCity = configurationController.getArrangementCityJpaController().findNukeMrcommerceArrangementCity(invoiceItem.getArrangementcity_id().intValue());
            NukeMrcommerceItems item = configurationController.getItemsJpaController().findNukeMrcommerceItems(invoiceItem.getMrcommerceItemID());
            writeKTSatz(configurationController, arrCity, item);
        }        

        String country = invoice.getPartner().getAddress().getISOState();
        BuhaKostl kostl = getKostl(configurationController, salesChannelInvoice, country);
        
        String dimension = "";
        if (invoiceItem != null)
            dimension = Integer.toString(invoiceItem.getMrcommerceItemID());

        //21 empty entries
        writer.write("1");
        for (int i = 0; i < 20; i++) {
            writer.write("");
        }

        if (kostl != null) {                                             //22
            writer.write(Integer.toString(kostl.getKostl()));
        } else {
            writer.write("");
        }

        if (invoiceItem != null && invoiceItem.getArrangementcity_id() != null && invoiceItem.getArrangementcity_id().intValue() > 0) {
            writer.write(invoiceItem.getArrangementcity_id().toString());
        } else if (filterKoTraeger != null) {
            writer.write(Integer.toString(filterKoTraeger.getBuhaBooking().getKonto()));
        } else {
            writer.write(Integer.toString(Globals.DEFAULTKOSTENTRAEGER));
        }
        writer.write(roundScale2(amount));
        if (invoiceItem != null) {
            Integer iID = invoice.getId();
            Integer iiID = invoiceItem.getMrcommerceItemID();
            List<VNukeCooperationDeliverynoteCounts> anzahlItems = configurationController.getCooperationDeliverynoteCountsJpaController().findByInvoiceAndMrcommerceItemID(iID, iiID);
            if (anzahlItems.size() > 0)        {
                writer.write(new Long(anzahlItems.get(0).getAnzahl()).toString());
            } else {
                writer.write("1");                                                      //25
            }
        } else {
            writer.write("1");
        }

        writer.write("1");
        writer.write("0");
        writer.write("0");
        //String auftrPeriode = configurationController.getCooperationDeliverynoteAdditionalfieldsJpaController().findNukeCooperationDeliverynoteAdditionalfields(invoice.getId()).getNukeCooperatiodeliverynoteadditionalfieldsPeriod();
        if (configurationController.getCooperationDeliverynoteAdditionalfieldsJpaController().findNukeCooperationDeliverynoteAdditionalfields(invoice.getId()) != null) {
            String auftrPeriode = configurationController.getCooperationDeliverynoteAdditionalfieldsJpaController().findNukeCooperationDeliverynoteAdditionalfields(invoice.getId()).getNukeCooperatiodeliverynoteadditionalfieldsPeriod();
            if (auftrPeriode != Globals.EMPTYSTRING && auftrPeriode != null && auftrPeriode.length() > 0) {
                writer.write(auftrPeriode);
            } else 
                writer.write(format.format(invoice.getInvoiceDate()).substring(0, 6));
        } else {
            writer.write(format.format(invoice.getInvoiceDate()).substring(0, 6));
        }
        writer.write("");                                                       //30
        writer.write("0");
        writer.write("");
        writer.write(dimension);
        writer.write("");                       //34
        writer.endRecord();

    }
    
  
    protected double writeARSatz0Rabatt(ConfigurationController configurationController, Invoice invoice, InvoiceItem invoiceItem, BuhaBooking booking, NukeMrcommerceItems item, BuhaFilter filterRabatt, NukeMrcommerceSaleschannelinvoice salesChannelInvoice, BuhaFilter filterKoTraeger, boolean keyCaching) throws IOException {

        JDCsvWriter writer = configurationController.getCSVController().getWriterAR();
        String gegenbuchKz = "E";
        if (writer.isDeferred()) {
            gegenbuchKz = "O";
        }        

        double amount = -invoiceItem.getCorrespondingItemRabattAmount().doubleValue();
        if (filterRabatt.getBuhaBooking().getNegativBuchen()) {
            amount = -amount;
        }        
        
        double taxrate = new Double(invoiceItem.getTaxRate() != null ? invoiceItem.getTaxRate() : 0);

        InvoiceItem itemPrev = invoiceItem.getCorrespondingItemRabattID();
        int debitor = invoice.getPartnerID() + Globals.DEBITORSTARTVALUE;

        //bei positivem Betrag - Bestellung
        //bei negativem Betrag - Storno/Umbuchung/Einlösung
        //int konto = 0;
        //int gegenkonto = 0;
        
        double splitsum = amount;

        //configurationController.getLoggingHandler().getLogger(Globals.LOGGINGAR).log(Level.INFO, "Rabatt processing in writeARSatz0Rabatt 1: amount: " + amount + "   taxrate:" + taxrate);
        
        int konto1 = 0;
        int konto2 = 0;
        int gegenkonto2 = 0;
        int gegenkonto1 = 0;
        
        int specialDatesDeliverynote = 0;
        String auftrJahr;
        String auftrMonat;
        Date specialInvoiceDate = new Date();
        
        if (configurationController.getCooperationDeliverynoteAdditionalfieldsJpaController().findNukeCooperationDeliverynoteAdditionalfields(invoice.getId()) != null) {
            String auftrPeriode = configurationController.getCooperationDeliverynoteAdditionalfieldsJpaController().findNukeCooperationDeliverynoteAdditionalfields(invoice.getId()).getNukeCooperatiodeliverynoteadditionalfieldsPeriod();
            if (auftrPeriode != Globals.EMPTYSTRING && auftrPeriode != null && auftrPeriode.length() > 0) {
                try {
                    specialDatesDeliverynote = 1;
                    auftrJahr = auftrPeriode.substring(0, 4);
                    auftrMonat = auftrPeriode.substring(4,6);
                    Calendar calendar;
                    calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    calendar.setTime(sdf.parse(auftrJahr+auftrMonat+"01"));// all done
                    calendar.add(Calendar.MONTH, 1);
                    calendar.add(Calendar.DATE, -1);
                    specialInvoiceDate = calendar.getTime();
                } catch (ParseException ex) {
                    Logger.getLogger(BookingARWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
             
            } 
        } 
        
        if (filterRabatt.getBuhaBooking().getKonto() == 0) {
            konto1 = debitor;
        } else {
            konto1 = filterRabatt.getBuhaBooking().getKonto();
        }
        
//        if (filterRabatt.getGegenkonto() == 0) {
//            gegenkonto = debitor;
//        } else {
//            gegenkonto = filterRabatt.getGegenkonto();
//        }
        gegenkonto1 = debitor;
        if (filterRabatt.getBuhaBooking().getGegenkonto() != 0) {
            konto2 = filterRabatt.getBuhaBooking().getGegenkonto();
            gegenkonto2 = debitor;
        }

        //configurationController.getLoggingHandler().getLogger(Globals.LOGGINGAR).log(Level.INFO, "Rabatt processing in writeARSatz0Rabatt 2: konto: " + konto + "   gegenkonto:" + gegenkonto);
        
        
//        if (amount < 0) {
//            //storno etc.
//            int hilfe = konto1;
//            konto1 = gegenkonto2;
//            gegenkonto2 = hilfe;
//            amount = -amount;
//        }
        double tax = -amount * taxrate / (taxrate + 100);

        // Rabatt Buchung

        if (writer.isDeferred()) {
            //Splitbuchung
            
            String bucod1 = Integer.toString(filterRabatt.getBuhaBooking().getBuchungscode());
            String bucod2 = Integer.toString(filterRabatt.getBuhaBooking().getBuchungscode2());
//            if (invoice.getCategory().equals(Integer.toString(Globals.ORDERTYPEWIEDER))) {
//                bucod1 = "2";
//                bucod2 = "1";
//            }
            
            //erster Satz
            writer.write("0");                                                      //1
            writer.write(invoice.getInvoiceNumber());
            writer.write(Integer.toString(konto1));
            writer.write(Integer.toString(gegenkonto1));
            writer.write(format.format(invoice.getInvoiceDate()));                  //5
            writer.write(prepareStringForCSV(item.getNukeItemName()));
            //writerER.write("");
            writer.write(invoiceItem.getUniqueNumber());
            writer.write("");
            writer.write("");
            writer.write(format.format(invoice.getCreationDate()));                 //10
            writer.write(filterRabatt.getBuhaBooking().getBuhaCountry());
            writer.write("AR");
            writer.write(bucod1);

            writer.write(roundScale2(taxrate));
            writer.write(roundScale2(amount));     //15
            writer.write("0");
            writer.write(roundScale2(tax));
            writer.write(Integer.toString(booking.getSteuercode()));
            
            if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
            writer.write(gegenbuchKz);                                                      //20
            writer.write("A");
            //writer.write("");
            writer.endRecord();
            writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, amount, keyCaching);
            splitsum = amount + tax;

            if (konto2 != 0) {
                //zweiter Satz,
                writer.write("0");                                                      //1
                writer.write(invoice.getInvoiceNumber());
                writer.write(Integer.toString(konto2));
                writer.write(Integer.toString(gegenkonto2));
                writer.write(format.format(invoice.getInvoiceDate()));                  //5
                writer.write(prepareStringForCSV(item.getNukeItemName()));
                //writerER.write("");
                writer.write(invoiceItem.getUniqueNumber());
                writer.write("");
                writer.write("");
                writer.write(format.format(invoice.getCreationDate()));                 //10
                writer.write(filterRabatt.getBuhaBooking().getBuhaCountry());
                writer.write("AR");
                writer.write(bucod2);

                writer.write(roundScale2(taxrate));
                writer.write(roundScale2(-amount));     //15
                writer.write("0");
                writer.write(roundScale2(-tax));
                writer.write(Integer.toString(booking.getSteuercode()));
                //writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate()));
                if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
                writer.write(gegenbuchKz);                                                      //20
                writer.write("A");
                //writer.write("");
                writer.endRecord();
                writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, -amount, keyCaching);
                splitsum += -amount - tax;

            }

            
        } else if (filterRabatt.getBuhaBooking().getZwischenkonto() == 0) {
            
            //configurationController.getLoggingHandler().getLogger(Globals.LOGGINGAR).log(Level.INFO, "Rabatt processing in writeARSatz0Rabatt 3");
            writer.write("0");                                                      //1
            writer.write(invoice.getInvoiceNumber());
            writer.write(Integer.toString(konto1));
            writer.write(Integer.toString(gegenkonto1));
            writer.write(format.format(invoice.getInvoiceDate()));                  //5
            writer.write(prepareStringForCSV(item.getNukeItemName()));
            //writerER.write("");
            writer.write(invoiceItem.getUniqueNumber());
            writer.write("");
            writer.write("");
            writer.write(format.format(invoice.getCreationDate()));                 //10
            writer.write(filterRabatt.getBuhaBooking().getBuhaCountry());
            writer.write("AR");
            writer.write("1");

            writer.write(roundScale2(taxrate));
            writer.write(roundScale2(amount));     //15
            writer.write("0");
            writer.write(roundScale2(tax));
            writer.write(Integer.toString(booking.getSteuercode()));
            //writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate()));
            if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
            writer.write(gegenbuchKz);                                                      //20
            writer.write("A");
            //writer.write("");
            writer.endRecord();
            writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, amount, keyCaching);

        } else {

            //configurationController.getLoggingHandler().getLogger(Globals.LOGGINGAR).log(Level.INFO, "Rabatt processing in writeARSatz0Rabatt 4");

            //erster Satz
            writer.write("0");                                                      //1
            writer.write(invoice.getInvoiceNumber());
            writer.write(Integer.toString(konto1));
            writer.write(Integer.toString(filterRabatt.getBuhaBooking().getZwischenkonto()));
            writer.write(format.format(invoice.getInvoiceDate()));                  //5
            writer.write(prepareStringForCSV(item.getNukeItemName()));
            //writerER.write("");
            writer.write(invoiceItem.getUniqueNumber());
            writer.write("");
            writer.write("");
            writer.write(format.format(invoice.getCreationDate()));                 //10
            writer.write(filterRabatt.getBuhaBooking().getBuhaCountry());
            writer.write("AR");
            writer.write("1");

            writer.write(roundScale2(taxrate));
            writer.write(roundScale2(amount));     //15
            writer.write("0");
            writer.write(roundScale2(tax));
            writer.write(Integer.toString(booking.getSteuercode()));
            //writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate()));
            if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
            writer.write(gegenbuchKz);                                                      //20
            writer.write("A");
            //writer.write("");
            writer.endRecord();
            writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, amount, keyCaching);


            //zweiter Satz,
            writer.write("0");                                                      //1
            writer.write(invoice.getInvoiceNumber());
            writer.write(Integer.toString(filterRabatt.getBuhaBooking().getZwischenkonto()));
            writer.write(Integer.toString(gegenkonto2));
            writer.write(format.format(invoice.getInvoiceDate()));                  //5
            writer.write(prepareStringForCSV(item.getNukeItemName()));
            //writerER.write("");
            writer.write(invoiceItem.getUniqueNumber());
            writer.write("");
            writer.write("");
            writer.write(format.format(invoice.getCreationDate()));                 //10
            writer.write(filterRabatt.getBuhaBooking().getBuhaCountry());
            writer.write("AR");
            writer.write("1");

            writer.write(roundScale2(taxrate));
            writer.write(roundScale2(amount));     //15
            writer.write("0");
            writer.write(roundScale2(tax));
            writer.write(Integer.toString(booking.getSteuercode()));
            //writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate()));
            if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
            writer.write(gegenbuchKz);                                                      //20
            writer.write("A");
            //writer.write("");
            writer.endRecord();
            writeARSatz1(configurationController, invoice, invoiceItem, salesChannelInvoice, filterKoTraeger, amount, keyCaching);

        }
        return splitsum;
    }  
    

    protected void writeARSatz0Payment(ConfigurationController configurationController, Invoice invoice, double invoiceSum, BuhaFilter filterPayment, NukeMrcommerceSaleschannelinvoice salesChannelInvoice, boolean keyCaching) throws IOException {

        JDCsvWriter writer = configurationController.getCSVController().getWriterAR();

        // TODO: CSV schreiben, wir haben organiserInvoice, invoiceItem, booking, filter, that's it
        //double amount = new Double(invoiceItem.getAmount().toPlainString());
        //double taxrate = new Double(invoiceItem.getTaxRate() != null ? invoiceItem.getTaxRate() : 0);
        BuhaBooking bookingPayment = filterPayment.getBuhaBooking();
        //if (bookingPayment.getSteuerIgnorieren()) {
        //    taxrate = 0.0;
        //}
        //if (bookingPayment.getNegativBuchen()) {
        //    amount = -amount;
        //}
        String gegenbuchKz = "E";
        if (writer.isDeferred()) {
            gegenbuchKz = "O";
        }


        int konto = 0;
        int gegenkonto = 0;
        //int gegenkonto1 = 0;

        if (bookingPayment.getKonto() == 0) {
            konto = invoice.getPartnerID() + Globals.DEBITORSTARTVALUE;
        } else {
            konto = bookingPayment.getKonto();
        }

        if (bookingPayment.getGegenkonto() == 0) {
            gegenkonto = invoice.getPartnerID() + Globals.DEBITORSTARTVALUE;
        } else {
            gegenkonto = bookingPayment.getGegenkonto();
        }
        
        String companyName = invoice.getPartner().getAddress().getCompanyName() != null ? invoice.getPartner().getAddress().getCompanyName() : Globals.EMPTYSTRING;
//        String kunde = invoice.getPartner().getAddress().getFirstname() + " " + invoice.getPartner().getAddress().getLastname() + "" + companyName;
        String description = invoice.getPaymentType().getName() + ", " +  invoice.getPartner().getAddress().getFirstname() + " " + invoice.getPartner().getAddress().getLastname() + "" + companyName;

        //double tax = -amount * taxrate / (taxrate + 100);
        double tax = 0.0;

        writer.write("0");                                                      //1
        writer.write(invoice.getInvoiceNumber());
        writer.write(Integer.toString(konto));
        writer.write(Integer.toString(gegenkonto));
        writer.write(format.format(invoice.getInvoiceDate()));                  //5
        //writer.write(prepareStringForCSV("Online Zahlung: " + filterPayment.getDescription() + "  Kunde: " + kunde));
        writer.write(prepareStringForCSV(description));
        writer.write("");
        writer.write("");
        writer.write("");
        writer.write(format.format(invoice.getCreationDate()));                 //10
        writer.write(bookingPayment.getBuhaCountry());
        writer.write("KK");
        writer.write(Short.toString(bookingPayment.getBuchungscode()));

        writer.write("0");
        writer.write(roundScale2(invoiceSum));     //15
        writer.write("0");
        writer.write("0");
        writer.write(Integer.toString(bookingPayment.getSteuercode()));
        writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate()));
        writer.write(gegenbuchKz);                                                      //20
        writer.write("A");
        //writer.write(invoice.getInvoiceNumber());                               //22
        writer.endRecord();
        writeARSatz1(configurationController, invoice, null, salesChannelInvoice, null, invoiceSum, keyCaching);



    }    

      
    protected void writeDBSatz(ConfigurationController configurationController, Partner partner) throws IOException {

        if (partnerMap.containsKey(partner.getId())) {
            return;
        }
        partnerMap.put(partner.getId(), partner);

        configurationController.getLoggingHandler().getLogger(Globals.LOGGINGDB).log(Level.INFO, "Debitor: " + partner.getId());

        JDCsvWriter writer = configurationController.getCSVController().getWriterDB();

        Address address = partner.getAddress();
        int debitorNr = partner.getId() + Globals.DEBITORSTARTVALUE;
        if (address == null) {
            configurationController.getLoggingHandler().getLogger(Globals.LOGGINGDB).log(Level.SEVERE, "Keine Adresse gefunden für Debitor: " + partner.getId());
            return;
        }
        int buhaCountry = getISOCountry2BuhaCountry(address.getISOState());

        writer.write(Integer.toString(debitorNr));
        if (address.getCompanyName() != null && address.getCompanyName().trim().length() > 0) {
            writer.write(prepareStringForCSV(address.getCompanyName()));
        } else {
            writer.write(prepareStringForCSV(address.getLastname() + ", " + address.getFirstname()));
        }
        //writerER.write(address.getBranch());
        writer.write(prepareStringForCSV(address.getCity()));
        writer.write(prepareStringForCSV(address.getStreet()));
        writer.write(prepareStringForCSV(address.getPostalCode()));
        writer.write(prepareStringForCSV(address.getTitleText()));

        writer.write(prepareStringForCSV(configurationController.get2LetterCountry(address.getISOState(), configurationController.getLoggingHandler().getLogger(Globals.LOGGINGDB), "Kunde: " + partner.getId())));

        //writerER.write(address.getCompanyName2());
        //writerER.write(address.getCompanyNameAdditional());
        writer.write(prepareStringForCSV(address.getFon()));
        writer.write(prepareStringForCSV(address.getEmail()));
        writer.write(prepareStringForCSV(partner.getUIDNumber()));
        //writerER.write(organiser.getBankCode());
        //writerER.write(organiser.getIBANNumber());
        //writerER.write(address.getMobile());
        //writerER.write(address.getPosition());
        writer.write(prepareStringForCSV(address.getSalutationCode()));
        writer.write(Integer.toString(Globals.DEBITORTERMSOFPAYMENT));          //Zahlungsziel in Tagen, fix
        writer.write(prepareStringForCSV(Integer.toString(buhaCountry)));       //UST-Landkennz
        writer.write(prepareStringForCSV("0"));                                 //Mahnsperre        
////        if (buhaCountry == 2) {
////            writer.write("20002");
////        } else if (buhaCountry == 3) {
////            writer.write("20003");
////        } else {
//            writer.write("20001");
////        }
        writer.write(prepareStringForCSV("0"));  //keine Sammelkonten mehr
        writer.write(address.getFirstname());
        writer.write(address.getLastname());
        //writerER.write(organiser.getBICCode());
        //writerER.write(organiser.getBankName());
        //writerER.write(organiser.getFNNumber());
        writer.endRecord();

    }    
    
      protected void writeDBSatzLS(ConfigurationController configurationController, Partner partner, VNukeCooperationDeliverynoteDetails coopPartner) throws IOException {

        if (partnerMap.containsKey(partner.getId())) {
            return;
        }
        partnerMap.put(partner.getId(), partner);

        configurationController.getLoggingHandler().getLogger(Globals.LOGGINGDB).log(Level.INFO, "Debitor: " + partner.getId());

        JDCsvWriter writer = configurationController.getCSVController().getWriterDB();

        Address address = partner.getAddress();
        String debitorNr = Globals.EMPTYSTRING;
        if (coopPartner.getNukeCooperationuserBmdId() != null && coopPartner.getNukeCooperationuserBmdId().length() >= 1){
            //Nur wenn BMD_ID befüllt (Mantis 2407)
            debitorNr = "" + coopPartner.getNukeCooperationuserBmdId();
        } else {
            int debitorInt = partner.getId() + Globals.DEBITORSTARTVALUE;
            debitorNr = "" + debitorInt;
        }
                
        //String debitorNr = "4" + leftPad(coopPartner.getNukeCooperationuserBmdId(), 6, "0");
        if (address == null) {
            configurationController.getLoggingHandler().getLogger(Globals.LOGGINGDB).log(Level.SEVERE, "Keine Adresse gefunden für Debitor: " + partner.getId());
            return;
        }
        int buhaCountry = getISOCountry2BuhaCountry(address.getISOState());

        writer.write(debitorNr);
        if (address.getCompanyName() != null && address.getCompanyName().trim().length() > 0) {
            writer.write(prepareStringForCSV(address.getCompanyName()));
        } else {
            writer.write(prepareStringForCSV(address.getLastname() + ", " + address.getFirstname()));
        }
        //writerER.write(address.getBranch());
        writer.write(prepareStringForCSV(address.getCity()));
        writer.write(prepareStringForCSV(address.getStreet()));
        writer.write(prepareStringForCSV(address.getPostalCode()));
        writer.write(prepareStringForCSV(address.getTitleText()));

        writer.write(prepareStringForCSV(configurationController.get2LetterCountry(address.getISOState(), configurationController.getLoggingHandler().getLogger(Globals.LOGGINGDB), "Kunde: " + partner.getId())));

        //writerER.write(address.getCompanyName2());
        //writerER.write(address.getCompanyNameAdditional());
        writer.write(prepareStringForCSV(address.getFon()));
        writer.write(prepareStringForCSV(address.getEmail()));
        writer.write(prepareStringForCSV(partner.getUIDNumber()));
        //writerER.write(organiser.getBankCode());
        //writerER.write(organiser.getIBANNumber());
        //writerER.write(address.getMobile());
        //writerER.write(address.getPosition());
        writer.write(prepareStringForCSV(address.getSalutationCode()));
        writer.write(coopPartner.getNukeCooperationuserAccountterms());          //Zahlungsziel in Tagen, fix
        writer.write(prepareStringForCSV(Integer.toString(buhaCountry)));       //UST-Landkennz
        writer.write(prepareStringForCSV("0"));                                 //Mahnsperre        
        
//        if (coopPartner.getNukeCooperationuserBmdId() != null && coopPartner.getNukeCooperationuserBmdId().length() >= 1){
//            //Nur wenn BMD_ID befüllt (Mantis 2407)
////            if (buhaCountry == 2) {
////                writer.write("20012");
////            } else if (buhaCountry == 3) {
////                writer.write("20013");
////            } else {
//                writer.write("20011");
////            }
//        } else {
////            if (buhaCountry == 2) {
////                writer.write("20002");
////            } else if (buhaCountry == 3) {
////                writer.write("20003");
////            } else {
//                writer.write("20001");
////            }
//        }
        
        writer.write(prepareStringForCSV("0"));  //keine Sammelkonten mehr
        
        writer.write(address.getFirstname());
        writer.write(address.getLastname());
        //writerER.write(organiser.getBICCode());
        //writerER.write(organiser.getBankName());
        //writerER.write(organiser.getFNNumber());
        writer.endRecord();

    }  
    

    protected void writerARSplitFirstLine(ConfigurationController configurationController, Invoice invoice, String country, double sum, String gegenbuchKz) throws IOException {
        JDCsvWriter writer = configurationController.getCSVController().getWriterAR();
        writer.setDeferred(false);
        
        int buhaCountry = 0;
        try {
            buhaCountry = getISOCountry2BuhaCountry(invoice.getPartner().getAddress().getISOState());
        } catch (Exception ex) {
            configurationController.getLoggingHandler().getLogger(Globals.LOGGINGAR).log(Level.SEVERE, "Kein Land für Splitbuchung gefunden: Invoice " + invoice.getInvoiceNumber());
        }
        
                int specialDatesDeliverynote = 0;
        String auftrJahr;
        String auftrMonat;
        Date specialInvoiceDate = new Date();
        
        if (configurationController.getCooperationDeliverynoteAdditionalfieldsJpaController().findNukeCooperationDeliverynoteAdditionalfields(invoice.getId()) != null) {
            String auftrPeriode = configurationController.getCooperationDeliverynoteAdditionalfieldsJpaController().findNukeCooperationDeliverynoteAdditionalfields(invoice.getId()).getNukeCooperatiodeliverynoteadditionalfieldsPeriod();
            if (auftrPeriode != Globals.EMPTYSTRING && auftrPeriode != null && auftrPeriode.length() > 0) {
                try {
                    specialDatesDeliverynote = 1;
                    auftrJahr = auftrPeriode.substring(0, 4);
                    auftrMonat = auftrPeriode.substring(4,6);
                    Calendar calendar;
                    calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    calendar.setTime(sdf.parse(auftrJahr+auftrMonat+"01"));// all done
                    calendar.add(Calendar.MONTH, 1);
                    calendar.add(Calendar.DATE, -1);
                    specialInvoiceDate = calendar.getTime();
                } catch (ParseException ex) {
                    Logger.getLogger(BookingARWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
             
            } 
        } 


//      0======= Splitbuchung ohne Gegenkonto
        writer.write("0");                                                      //1
        writer.write(invoice.getInvoiceNumber());
        if (configurationController.getCooperationInvoiceDetailsJpaController().findByNukeCooperationinvoiceInvoice(invoice.getId()).size() > 0) {
            Integer coop = configurationController.getCooperationInvoiceDetailsJpaController().findByNukeCooperationinvoiceInvoice(invoice.getId()).get(0).getNukeCooperationInvoiceDetailsPK().getNukeCooperationinvoiceCooperation();
            if (configurationController.getCooperationDeliverynoteDetailsJpaController().findVNukeCooperationDeliverynoteDetails(coop) != null) {
                VNukeCooperationDeliverynoteDetails coopPartner = configurationController.getCooperationDeliverynoteDetailsJpaController().findVNukeCooperationDeliverynoteDetails(coop);                
                if (coopPartner.getNukeCooperationuserBmdId() != null && coopPartner.getNukeCooperationuserBmdId().length() >= 1) {
                    writer.write("" + coopPartner.getNukeCooperationuserBmdId());
                } else {
                    writer.write(Integer.toString(invoice.getPartnerID() + Globals.DEBITORSTARTVALUE));
                }
                        
            } else {
                writer.write(Integer.toString(invoice.getPartnerID() + Globals.DEBITORSTARTVALUE)); 
            }       
        } else {
            writer.write(Integer.toString(invoice.getPartnerID() + Globals.DEBITORSTARTVALUE)); 
        }        
        
        writer.write("");
        writer.write(format.format(invoice.getInvoiceDate()));                  //5
        writer.write("Buchung");
        writer.write("");
        writer.write("");
        writer.write("");
        writer.write(format.format(invoice.getInvoiceDate()));                  //10
//        writer.write(Integer.toString(buhaCountry));
        writer.write("0");                                                      //Anpassen -> immer 0 bei 1ter BZeile
        writer.write("AR");
        writer.write("1");
        writer.write("0");
        writer.write(roundScale2(-sum));                                         //15
        writer.write("0");
        writer.write("0");
        writer.write("0");
        //writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate()));
        if (specialDatesDeliverynote == 0) {
                    writer.write((new SimpleDateFormat("MM")).format(invoice.getInvoiceDate())); 
                } else {
                    writer.write((new SimpleDateFormat("MM")).format(specialInvoiceDate)); 
                } 
        writer.write(gegenbuchKz);    // Ohh nicht 0                            //20
        writer.write("A");
        //writer.write("");
        writer.endRecord();
    }
    
 
    
}
