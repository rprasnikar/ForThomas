/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.control;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author gunter reinitzer
 */
public class CSVController {

    JDCsvWriter writerAR = null;
    JDCsvWriter writerDB = null;
    JDCsvWriter writerER = null;
    JDCsvWriter writerCR = null;
    JDCsvWriter writerKT = null;
    JDCsvWriter writerDI = null; 
    JDCsvWriter writerStorno = null; 
    
    File csvARFile = null;
    File csvDBFile = null;
    File csvERFile = null;
    File csvCRFile = null;
    File csvKTFile = null;
    File csvDIFile = null;
    File csvStornoFile = null;

    public void prepareWriters() {
        writerAR = null;
        writerDB = null;
        writerER = null;
        writerCR = null;
        writerKT = null;
        writerDI = null;
        writerStorno = null;
    }

    public void closeWriters() {
        if (writerAR != null) {
            writerAR.flush();
            writerAR.close();
        }
        if (writerDB != null) {
            writerDB.flush();
            writerDB.close();
        }
        if (writerER != null) {
            writerER.flush();
            writerER.close();
        }
        if (writerCR != null) {
            writerCR.flush();
            writerCR.close();
        }
        if (writerKT != null) {
            writerKT.flush();
            writerKT.close();
        }
        if (writerDI != null) {
            writerDI.flush();
            writerDI.close();
        }
        if (writerStorno != null) {
            writerStorno.flush();
            writerStorno.close();
        }
        writerAR = null;
        writerDB = null;
        writerER = null;
        writerCR = null;
        writerKT = null;
        writerDI = null;
        writerStorno = null;
    }

    public JDCsvWriter getWriterAR() throws IOException {
        if (csvARFile != null && writerAR == null) {
            writerAR = new JDCsvWriter(csvARFile.getCanonicalPath());
            writerAR.setDelimiter(';');

            writeBuchungFirst(writerAR);
        }
        return writerAR;
    }

    public JDCsvWriter getWriterDB() throws IOException {
        if (csvDBFile != null && writerDB == null) {
            writerDB = new JDCsvWriter(csvDBFile.getCanonicalPath());
            writerDB.setDelimiter(';');

            writerDB.write("Konto-Nr");
            writerDB.write("Name");
            writerDB.write("Ort");
            writerDB.write("Strasse");
            writerDB.write("Postltz");
            writerDB.write("Titel");
            writerDB.write("Staat");
            writerDB.write("Telefon-Nr");
            writerDB.write("E-Mail-Adresse");
            writerDB.write("UST-Id-Nummer");
            writerDB.write("Pers-Anrede");
            writerDB.write("Zahlungsziel");
            writerDB.write("UST-Landkennz");
            writerDB.write("Mahnsperre");
            writerDB.write("Sammelkonto");
            writerDB.write("Vorname");
            writerDB.write("Familienname");
            writerDB.endRecord();
        }
        return writerDB;
    }

    public JDCsvWriter getWriterER() throws IOException {
        if (csvERFile != null && writerER == null) {
            writerER = new JDCsvWriter(csvERFile.getCanonicalPath());
            writerER.setDelimiter(';');

            writeBuchungFirst(writerER);
        }
        return writerER;
    }

    public JDCsvWriter getWriterCR() throws IOException {
        if (csvCRFile != null && writerCR == null) {
            writerCR = new JDCsvWriter(csvCRFile.getCanonicalPath());
            writerCR.setDelimiter(';');

            writerCR.write("Konto-Nr");                                        //1
            writerCR.write("Name");
            writerCR.write("Ort");
            writerCR.write("Strasse");
            writerCR.write("Postltz");                                         //5
            writerCR.write("Titel");
            writerCR.write("Staat");
            writerCR.write("Telefon-Nr");
            writerCR.write("E-Mail-Adresse");
            writerCR.write("UST-Id-Nummer");                                   //10
            writerCR.write("Pers-Anrede");
            writerCR.write("Zahlungsziel");       // Netto Zahlungsziel in Tagen
            writerCR.write("Skontotage");         // Skonto Zahlungsziel
            writerCR.write("Skontoprozent");      // prozentsatz
            writerCR.write("Bankkonto-Nr");                                    //15
            writerCR.write("Bankleitzahl");
            writerCR.write("Swiftcode");
            writerCR.write("IBAN-Nummer");
            writerCR.write("ZV");                                              //19 Zahlungsverkehr, Land ist nicht AT
            writerCR.write("UST-Landkennz");
            writerCR.write("Zahlbank");
            writerCR.endRecord();
        }
        return writerCR;
    }

    public JDCsvWriter getWriterKT() throws IOException {
        if (csvKTFile != null && writerKT == null) {
            writerKT = new JDCsvWriter(csvKTFile.getCanonicalPath());
            writerKT.setDelimiter(';');

            writerKT.write("Kostentyp");
            writerKT.write("Kosten-Nummer");
            writerKT.write("Kosten-Bezeichnung");            
            writerKT.endRecord();
        }
        return writerKT;
    }

    public JDCsvWriter getWriterDI() throws IOException {
        if (csvDIFile != null && writerDI == null) {
            writerDI = new JDCsvWriter(csvDIFile.getCanonicalPath());
            writerDI.setDelimiter(';');

            writerDI.write("Kostentyp");
            writerDI.write("Kosten-Nummer");
            writerDI.write("Kosten-Bezeichnung");            
            writerDI.endRecord();
        }
        return writerDI;
    }

    public JDCsvWriter getWriterStorno() throws IOException {
        if (csvStornoFile != null && writerStorno == null) {
            writerStorno = new JDCsvWriter(csvStornoFile.getCanonicalPath());
            writerStorno.setDelimiter(';');

            writerStorno.write("Datum");
            writerStorno.write("Rechnung");
            writerStorno.write("Debitor");
            writerStorno.endRecord();
        }
        return writerStorno;
    }

    public void setCsvARFile(File csvFile) {
        this.csvARFile = csvFile;
    }

    public void setCsvDBFile(File csvFile) {
        this.csvDBFile = csvFile;
    }

    public void setCsvERFile(File csvFile) {
        this.csvERFile = csvFile;
    }

    public void setCsvCRFile(File csvFile) {
        this.csvCRFile = csvFile;
    }

    public void setCsvKTFile(File csvFile) {
        this.csvKTFile = csvFile;
    }
    
    public File getCsvKTFile() {
        return csvKTFile;
    }

    public void setCsvDIFile(File csvFile) {
        this.csvDIFile = csvFile;
    }

    public void setCsvStornoFile(File csvFile) {
        this.csvStornoFile = csvFile;
    }

    protected void writeBuchungFirst(JDCsvWriter writer) throws IOException {

        writer.write("satzart");                                                //1
        writer.write("belegnr");
        writer.write("konto");
        writer.write("gkto");
        writer.write("belegdat");                                               //5
        writer.write("text");
        writer.write("text1");
        writer.write("text2");
        writer.write("text3");
        writer.write("buchdat");                                                //10
        writer.write("landkz");
        writer.write("symbol");
        writer.write("bucod");
        writer.write("mwst");
        writer.write("betrag");                                                 //15
        writer.write("skonto");
        writer.write("steuer");
        writer.write("steucod");
        writer.write("periode");
        writer.write("gegenbuchkz");                                            //20
        writer.write("verbuchkz");
        //writer.write("kost");
        writer.write("auft-kost");
        writer.write("auft-traeger");
        writer.write("auft-betrag");                                            //24
        writer.write("auft-menge");
        writer.write("auft-mengekz");
        writer.write("auft-variator");
        writer.write("auft-monteiler");
        writer.write("auft-periode");                                           //29
        writer.write("auft-korekonto");
        writer.write("auft-schluessel");
        writer.write("auft-abteilung");
        writer.write("auft-dimension");
        writer.write("auft-geschaeftsbereich");                                 //34


        writer.endRecord();
    }

}
