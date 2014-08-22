/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Gunter Reinitzer
 */
public class Globals {
    
    public static final String EMPTYSTRING = "";

    public static final String VERSION = "2.30_RP";
    public static final String WIKIURL = "http://wiki.jollydays.at/wiki/index.php/TechDoc_-_Buchhaltung_Schnittstelle_%C3%84nderungshistorie";

    // Defaultwerte
    public static final int DEBITORSTARTVALUE        = 2000000;                 // Differenz zwischen Kundennummer und Debitorennummer
    public static final int KREDITORSTARTVALUE       = 338000000;               // Differenz zwischen Veranstalternummer und Kreditorennummer
    public static final int KREDITORTERMSOFPAYMENT   = 14;                      // Zahlungsziel Veranstalter
    public static final int DEBITORTERMSOFPAYMENT    = 8;                       // Zahlungsziel Kunde
    public static final int GUTSCHEINMAXITEMNR       = 95000;                   // Gutschein Item Nummer Begrenzung
    //Items die keine Gutscheine sind aber bei Import der Kundenzahlungen auf bezahlt gesetzt gehören (Versand, Stornoversicherung, Verpackung und Gebühren):
    public static final List<Integer> NONGUTSCHEINITEMSKZ = Arrays.asList(95001,95008,95015,95018,95020,95021,95025,95026,95027,95031,95032,95033,95034,95045);
    public static final int GUTSCHEINREAKTIVIERUNG   = 95045;                   // Reaktivierunsgebühr
    public static final double BESORGERRUECKSTELLUNG = 0.65;                    // Rückstellung im Besorger Modell
    public static final int DEFAULTKOSTENTRAEGER     = 20;                      // Default Kostenträger, Sammler für "ohne Kostenträger"
    public static final int MINKUNDENRECHNUNG        = 18000;                   // minimaler Wert für eine gültige Rechnungsnummer, manuelle Rechnungen haben das Jahr einkodiert: 11000 ist 2011
    public static final String DEFAULTSTATE          = "AUT";                   // Land für Datensätze ohne Land
    public static final List<Integer> REVERSECHARGEITEMS = Arrays.asList(95030,95058,95059); //tbd ob 58 und 59 oder nur 59
    



    // Parameter in der DB
    public static final String DBVERSION          = "DBVERSION";                // Version der Buchungslogik, mit Programmversion abgleichen
    public static final String ARLASTNUMBER       = "ARLASTNUMBER";
    public static final String ARPACKETSIZE       = "ARPACKETSIZE";
    public static final String ERLASTNUMBER       = "ERLASTNUMBER";
    public static final String ERPACKETSIZE       = "ERPACKETSIZE";
    public static final String DIRCSVFILES        = "DIRCSVFILES";
    public static final String DIRLOGFILES        = "DIRLOGFILES";
    public static final String FILEDBPAYMENT      = "FILEDBPAYMENT";
    public static final String FILECRPAYMENT      = "FILECRPAYMENT";
    public static final String DIRPAYMENTDIR      = "DIRPAYMENT";

    // EMail
    public static final String MAILRECIPIENT      = "MAILRECIPIENT";
    public static final String MAILSENDER         = "MAILSENDER";
    public static final String MAILHOST           = "MAILHOST";
    public static final String MAILUSER           = "MAILUSER";
    public static final String MAILPASSWORD       = "MAILPASSWORD";
    public static final String MAILSUBJECT        = "SS FEHLER: Fehler in der Buchhaltungs-Schnittstelle";

    // Properties Präfix
    public static final String PREFIXARRCITY      = "PREARRCITY";               //ArrangementCity
    public static final String PREFIXINVAR        = "PREINVAR";                 //Kundenrechnung
    public static final String PREFIXITEM         = "PREITEM";                  //Itemnummer

    // wenn Kunde Rechnung bezahlt
    public static final int    BHSTATUSNICHTBEZ         = 1;                    // nicht bezahlt
    public static final int    BHSTATUSMAHNUNG          = 2;                    // Mahnung (nicht bez.)
    public static final int    BHSTATUSMANFREI          = 3;                    // manuell frei für Buchung
    public static final int    BHSTATUSMAHNFREI         = 4;                    // Mahnung (frei f. Buch.)
    public static final int    BHSTATUSMANBEZ           = 5;                    // manuell bezahlt
    public static final int    BHSTATUSBEZAHLT          = 6;                    // bezahlt
    
    // BH und FF Status 95er Items
    public static final int    BHSTATUS95ABGESCHLOSSEN  = 100;
    public static final int    FFSTATUS95ABGESCHLOSSEN  = 100;
    
    // Fulfillment Status
    public static final int    FFSTATUSFREIEINLOESUNG   = 2;

    // wenn wir Veranstalterrechnung bezahlen
    public static final int    ORGINVOICENICHTBEZ       = 1;                    // nicht bezahlt
    public static final int    ORGINVOICEBEZAHLT        = 3;                    // bezahlt

    //ordertype
    public static final int    ORDERTYPEABLAUF          = 13;                   // Ablauf
    public static final int    ORDERTYPEWIEDER          = 14;                   // Wiederherstellung
    public static final int    ORDERTYPE100RABATT       = 100;                  // Fake-Ordertype für Buchungsregel: 100% Rabatt oder Systemfehler
    public static final int    ORDERTYPEMARKETINGAKTION = 101;                  // Fake-Ordertype für Buchungsregel: Marketingaktion

    //paymenttype
    public static final int    PAYMENTTYPEVERM          = 27;                   // Vermittlung -> Buchungsbeleg

    public static final String LOGGINGAR                = "AR";
    public static final String LOGGINGDB                = "DB";
    public static final String LOGGINGER                = "ER";
    public static final String LOGGINGCR                = "CR";
    public static final String LOGGINGKZ                = "KZ";
    public static final String LOGGINGVZ                = "VZ";
    public static final String LOGGING                  = "LOG";
    
    public static final short   REVERSECHARGE           = 2;
    public static final double  REVERSECHARGE_TAXRATE   = 20.0;
    
    //BuHaSS Status
    public static final short   OK                      = 1;
    public static final short   FAIL                    = -1;
    public static final short   NOT_PROCESSED           = 0;

}
