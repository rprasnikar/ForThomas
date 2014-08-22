/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking; 

import at.jollydays.booking.control.BookingController;
import at.jollydays.booking.control.CSVController;
import at.jollydays.booking.control.ConfigurationController;
import at.jollydays.booking.control.LoggingHandler;
import at.jollydays.booking.control.NotFoundException;
import at.jollydays.booking.control.NothingFoundException;
import at.jollydays.booking.control.PaymentStatistic;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.*;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Gunter Reinitzer
 */
public class Processing {

    private static File csvFileAR = null;
    private static File csvFileDB = null;
    private static File csvFileER = null;
    private static File csvFileCR = null;
    private static File csvFileKZ = null;
    private static File csvFileVZ = null;
    private static File csvFileKT = null;
    private static File csvFileIT = null;
//    private static JDCsvWriter writerAR = null;
//    private static JDCsvWriter writerDB = null;
//    private static JDCsvWriter writerER = null;
//    private static JDCsvWriter writerCR = null;
//    private static JDCsvWriter writerKZ = null;
//    private static JDCsvWriter writerVZ = null;
//    private static JDCsvWriter writerKT = null;
//    private static JDCsvWriter writerIT = null;
    private static CSVController cSVController = null;

    public static void main(String[] args) {
        processing();
    }

    public static void processing() {
        processingSpecific(true, true, true, false);
    }

    public static void processingSpecific(boolean processAR, boolean processER, boolean processKZ, boolean processStorno) {

        cSVController = new CSVController();

        ConfigurationController configurationController = new ConfigurationController();
        BookingController bookingController = new BookingController(configurationController);

        File logDir = getDir(configurationController, Globals.DIRLOGFILES);
        String date = (new SimpleDateFormat("yyMMdd-hhmm")).format(new Date());
        LoggingHandler lh = new LoggingHandler(logDir, date);

        try {
            File csvDir = getDir(configurationController, Globals.DIRCSVFILES);
            
            String bdVersion = configurationController.getProperty(Globals.DBVERSION).getValue();

            lh.getLogger(Globals.LOGGING, Globals.EMPTYSTRING).log(Level.INFO, "processing started  - Program Version: " + Globals.VERSION + "   DB Version:  " + bdVersion);

            // AR
            if (processAR) {
                processAR(configurationController, bookingController, csvDir, lh);
            }

            // ER
            if (processER) {
                processER(configurationController, bookingController, csvDir, lh);
            }

            //KZ
            if (processKZ) {
                processKZ(configurationController, bookingController, lh);
            }


            //VZ

            //Storno
            if (processStorno) {
                processStorno(configurationController, bookingController, csvDir, lh);
            }


            errorHandling(configurationController, lh);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Processing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Processing.class.getName()).log(Level.SEVERE, null, ex);
        }

        lh.close();

    }

    private static void sendEmails(ConfigurationController configurationController, StringBuilder buffer) {
        try {
//            String recipient = Globals.MAILRECIPIENT;
//            String from = Globals.MAILSENDER;
//            String host = Globals.MAILHOST;
//            String user = Globals.MAILUSER;

            String recipients = configurationController.getProperty(Globals.MAILRECIPIENT).getValue();
            String from = configurationController.getProperty(Globals.MAILSENDER).getValue();
            String host = configurationController.getProperty(Globals.MAILHOST).getValue();
            String user = configurationController.getProperty(Globals.MAILUSER).getValue();
            String password = configurationController.getProperty(Globals.MAILPASSWORD).getValue();



            //Set the host smtp address
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.user", user);
            props.put("mail.password", password);


            // create some properties and get the default Session
            Session session = Session.getDefaultInstance(props, null);

            // create a message
            Message msg = new MimeMessage(session);

            // set the from and to address
            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);
            //        InternetAddress[] addressTo = new InternetAddress[recipients.length];
            //        for (int i = 0; i < recipients.length; i++) {
            //            addressTo[i] = new InternetAddress(recipients[i]);
            //        }
            String[] recipientArr = recipients.split(";");
            InternetAddress[] addressTo = new InternetAddress[recipientArr.length];
            for (int i = 0; i < recipientArr.length; i++) {
                addressTo[i] = new InternetAddress(recipientArr[i].trim());
            }
            //addressTo[0] = new InternetAddress(recipients);
            msg.setRecipients(Message.RecipientType.TO, addressTo);

            // Optional : You can also set your custom headers in the Email if you Want
            // msg.addHeader("MyHeaderName", "myHeaderValue");
            // Setting the Subject and Content Type

            msg.setSubject(Globals.MAILSUBJECT);
            msg.setContent(buffer.toString(), "text/plain");

            Transport.send(msg);
        } catch (MessagingException ex) {
            Logger.getLogger(Processing.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    private static void processAR(ConfigurationController configurationController, BookingController bookingController, File csvDir, LoggingHandler lh) {
        int lastNumber = Integer.parseInt(configurationController.getProperty(Globals.ARLASTNUMBER).getValue());
        int nextNumber = 0;
        int sizePacket = Integer.parseInt(configurationController.getProperty(Globals.ARPACKETSIZE).getValue());
        String date = (new SimpleDateFormat("yyMMdd")).format(new Date());

        if (lastNumber > 0 && sizePacket > 0 && csvDir.exists() && csvDir.canWrite()) {
            String fileCounter = Globals.EMPTYSTRING;

            // freien Filenamen suchen, normal kein Problem, nur einmal pro Tag
            for (int i = 0; i < 20; i++) {
                csvFileAR = new File(csvDir, "AR" + date + fileCounter + ".csv");
                csvFileDB = new File(csvDir, "DB" + date + fileCounter + ".csv");
                csvFileKT = new File(csvDir, "KT" + date + fileCounter + ".csv");
                if (!csvFileAR.exists() && !csvFileDB.exists() && !csvFileKT.exists()) {
                    break;
                }
                fileCounter = (new SimpleDateFormat("-hhmm")).format(new Date());
                csvFileAR = null;
                csvFileDB = null;
                csvFileKT = null;
            }
            cSVController.setCsvARFile(csvFileAR);
            cSVController.setCsvDBFile(csvFileDB);
            cSVController.setCsvKTFile(csvFileKT);
            cSVController.prepareWriters();

            try {
//                // Dateien öffnen, damit mehrere Pakete abgearbeitet werden können
//                writerAR = new JDCsvWriter(csvFileAR.getCanonicalPath());
//                writerAR.setDelimiter(';');
//                writerDB = new JDCsvWriter(csvFileDB.getCanonicalPath());
//                writerDB.setDelimiter(';');
//                // http://www.csvreader.com/java_csv_samples.php
//                // http://javacsv.sourceforge.net/


                int invoiceCounter = 0;
                int lastCounter = 1;

                lh.getLogger(Globals.LOGGING, Globals.EMPTYSTRING).log(Level.INFO, "AR processing started  - last Number before:    " + lastNumber);

                try {
                    while (lastNumber > nextNumber) {
                        nextNumber = lastNumber + 1;
                        lastNumber = bookingController.processARPacket(nextNumber, 0, sizePacket, lh, cSVController, true);
                        configurationController.setProperty(Globals.ARLASTNUMBER, Integer.toString(lastNumber));
                    }
                } catch (NotFoundException ex) {
                }

            } catch (IOException ex) {
                try {
                    lh.getLogger(Globals.LOGGINGAR).log(Level.SEVERE, null, ex);
                } catch (IOException ex1) {
                    Logger.getLogger(Processing.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    lh.getLogger(Globals.LOGGING, Globals.EMPTYSTRING).log(Level.INFO, "AR processing finished - last Number processed: " + lastNumber);

                    lh.getLogger(Globals.LOGGINGAR).log(Level.INFO, "Anzahle verarbeitete Belege: " + lh.getCountAll(Globals.LOGGINGAR));
                    lh.getLogger(Globals.LOGGINGAR).log(Level.INFO, "Anzahle fehlerhafte Belege:  " + lh.getCountErr(Globals.LOGGINGAR));
                    lh.getLogger(Globals.LOGGINGDB).log(Level.INFO, "Anzahle verarbeitete Kunden: " + lh.getCountAll(Globals.LOGGINGDB));
                    lh.getLogger(Globals.LOGGINGDB).log(Level.INFO, "Anzahle fehlerhafte Kunden:  " + lh.getCountErr(Globals.LOGGINGDB));
                    //                writerDB.flush();
                    //                writerDB.close();
                    //                writerAR.flush();
                    //                writerAR.close();
                    //                writerDB = null;
                    //                writerDB = null;
                } catch (IOException ex) {
                    Logger.getLogger(Processing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            cSVController.closeWriters();
        }

    }

    private static void processER(ConfigurationController configurationController, BookingController bookingController, File csvDir, LoggingHandler lh) {
        int lastNumber = Integer.parseInt(configurationController.getProperty(Globals.ERLASTNUMBER).getValue());
        int nextNumber = 0;
        int sizePacket = Integer.parseInt(configurationController.getProperty(Globals.ERPACKETSIZE).getValue());
        String date = (new SimpleDateFormat("yyMMdd")).format(new Date());

        if (lastNumber > 0 && sizePacket > 0 && csvDir.exists() && csvDir.canWrite()) {
            String fileCounter = Globals.EMPTYSTRING;

            // freien Filenamen suchen, normal kein Problem, nur einmal pro Tag
            for (int i = 0; i < 10; i++) {
                csvFileER = new File(csvDir, "ER" + date + fileCounter + ".csv");
                csvFileCR = new File(csvDir, "CR" + date + fileCounter + ".csv");
                csvFileKT = new File(csvDir, "KT" + date + fileCounter + ".csv");
                if (!csvFileER.exists() && !csvFileCR.exists() && !csvFileKT.exists()) {
                    break;
                }
                fileCounter = (new SimpleDateFormat("-hhmm")).format(new Date());
                csvFileER = null;
                csvFileCR = null;
                csvFileKT = null;
            }

            cSVController.setCsvERFile(csvFileER);
            cSVController.setCsvCRFile(csvFileCR);
            cSVController.setCsvKTFile(csvFileKT);
            cSVController.prepareWriters();


            try {
//                // Dateien öffnen, damit mehrere Pakete abgearbeitet werden können
//                writerER = new JDCsvWriter(csvFileER.getCanonicalPath());
//                writerER.setDelimiter(';');
//                writerCR = new JDCsvWriter(csvFileCR.getCanonicalPath());
//                writerCR.setDelimiter(';');
//                writerKT = new JDCsvWriter(csvFileKT.getCanonicalPath());
//                writerKT.setDelimiter(';');
//                // http://www.csvreader.com/java_csv_samples.php
//                // http://javacsv.sourceforge.net/


                int invoiceCounter = 0;
                int lastCounter = 1;
                lh.getLogger(Globals.LOGGING, Globals.EMPTYSTRING).log(Level.INFO, "ER processing started  - last Number before:    " + lastNumber);

                try {
                    while (lastNumber > nextNumber) {
                        nextNumber = lastNumber + 1;
                        lastNumber = bookingController.processERPacket(nextNumber, 0, sizePacket, lh, cSVController, true);
                        configurationController.setProperty(Globals.ERLASTNUMBER, Integer.toString(lastNumber));
                    }
                } catch (NothingFoundException ex) {
                }

                lh.getLogger(Globals.LOGGING, Globals.EMPTYSTRING).log(Level.INFO, "ER processing finished - last Number processed: " + lastNumber);

            } catch (IOException ex) {
                try {
                    lh.getLogger(Globals.LOGGINGER).log(Level.SEVERE, null, ex);
                } catch (IOException ex1) {
                    Logger.getLogger(Processing.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    lh.getLogger(Globals.LOGGINGER).log(Level.INFO, "Anzahle verarbeitete Belege:       " + lh.getCountAll(Globals.LOGGINGER));
                    lh.getLogger(Globals.LOGGINGER).log(Level.INFO, "Anzahle fehlerhafte Belege:        " + lh.getCountErr(Globals.LOGGINGER));
                    lh.getLogger(Globals.LOGGINGCR).log(Level.INFO, "Anzahle verarbeitete Veranstalter: " + lh.getCountAll(Globals.LOGGINGCR));
                    lh.getLogger(Globals.LOGGINGCR).log(Level.INFO, "Anzahle fehlerhafte Veranstalter:  " + lh.getCountErr(Globals.LOGGINGCR));
                    //                writerER.flush();
                    //                writerER.close();
                    //                writerCR.flush();
                    //                writerCR.close();
                    //                writerKT.flush();
                    //                writerKT.close();
                    //                writerER = null;
                    //                writerKT = null;
                    //                writerKT = null;
                } catch (IOException ex) {
                    Logger.getLogger(Processing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            cSVController.closeWriters();
        }
    }

    private static void processKZ(ConfigurationController configurationController, BookingController bookingController, LoggingHandler lh) {
        String date = (new SimpleDateFormat("yyMMdd-hhmm")).format(new Date());

        File destDir = getDir(configurationController, Globals.DIRPAYMENTDIR);
        File file = new File(configurationController.getProperty(Globals.FILEDBPAYMENT).getValue());
        
        //file = new File("/home/gugu/Desktop/ar.csv");

        if (file.exists() && file.canRead() && destDir.exists() && destDir.canWrite()) {
        //if (file.exists() && file.canRead() ) {
            try {
                try {
                    
                    PaymentStatistic statistic = bookingController.processKZ(file, lh);
                    
                    boolean success = file.renameTo(new File(destDir, "KZ" + date + ".csv"));
                    lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "Anzahle VoucherOkCounter      : " + statistic.getVoucherOkCounter());
                    lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "Anzahle StatusNokCounter      : " + statistic.getStatusNokCounter());
                    lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "Anzahle InvoiceNotFoundCounter: " + statistic.getInvoiceNotFoundCounter());
                    lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "Anzahle ErrorCounter          : " + statistic.getErrorCounter());
                    
                } catch (Exception ex) {
                    lh.getLogger(Globals.LOGGINGKZ).log(Level.SEVERE, null, ex);
                }

            } catch (Throwable ex) {
                try {
                    lh.getLogger(Globals.LOGGINGKZ).log(Level.SEVERE, null, ex);
                } catch (IOException ex1) {
                    Logger.getLogger(Processing.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
//                try {
//                    lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "Anzahle verarbeitete Zahlungen: " + lh.getCountAll(Globals.LOGGINGKZ));
//                    lh.getLogger(Globals.LOGGINGKZ).log(Level.INFO, "Anzahle fehlerhafte Zahlungen:  " + lh.getCountErr(Globals.LOGGINGKZ));
//                } catch (IOException ex) {
//                    Logger.getLogger(Processing.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        }
    }
    
    
    private static void processStorno(ConfigurationController configurationController, BookingController bookingController, File csvDir, LoggingHandler lh) {
        Calendar cFrom = Calendar.getInstance();
        Calendar cTo = Calendar.getInstance();

        cFrom.set(Calendar.DAY_OF_MONTH, 1);
        cFrom.set(Calendar.HOUR_OF_DAY, 0);
        cFrom.set(Calendar.MINUTE, 0);
        
        File csvFile = new File(csvDir, "Stornoliste.csv");
        cSVController.setCsvStornoFile(csvFile);
        cSVController.prepareWriters();        
        
        File destDir = getDir(configurationController, Globals.DIRPAYMENTDIR);
        bookingController.processStorno(cFrom.getTime(), cTo.getTime(), destDir, lh, cSVController);  
        
        cSVController.closeWriters();
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

    private static void errorHandling(ConfigurationController configurationController, LoggingHandler lh) throws FileNotFoundException, IOException {
        if (lh.getCountErr(Globals.LOGGINGAR) > 0 || lh.getCountErr(Globals.LOGGINGDB) > 0 || lh.getCountErr(Globals.LOGGINGER) > 0 || lh.getCountErr(Globals.LOGGINGCR) > 0 || lh.getCountErr(Globals.LOGGINGKZ) > 0 || lh.getCountErr(Globals.LOGGINGVZ) > 0) {
            StringBuilder buffer = new StringBuilder();
            String ls = System.getProperty("line.separator");
            if (lh.getCountErr(Globals.LOGGINGAR) > 0) {
                buffer.append(ls).append("AR Fehler:").append(ls);
                getFileContent(buffer, new File(lh.getFileErr(Globals.LOGGINGAR)));
            }
            if (lh.getCountErr(Globals.LOGGINGDB) > 0) {
                buffer.append(ls).append("DB Fehler:").append(ls);
                getFileContent(buffer, new File(lh.getFileErr(Globals.LOGGINGDB)));
            }
            if (lh.getCountErr(Globals.LOGGINGER) > 0) {
                buffer.append(ls).append("ER Fehler:").append(ls);
                getFileContent(buffer, new File(lh.getFileErr(Globals.LOGGINGER)));
            }
            if (lh.getCountErr(Globals.LOGGINGCR) > 0) {
                buffer.append(ls).append("CR Fehler:").append(ls);
                getFileContent(buffer, new File(lh.getFileErr(Globals.LOGGINGCR)));
            }
            if (lh.getCountErr(Globals.LOGGINGKZ) > 0) {
                buffer.append(ls).append("KZ Fehler:").append(ls);
                getFileContent(buffer, new File(lh.getFileErr(Globals.LOGGINGKZ)));
            }
            if (lh.getCountErr(Globals.LOGGINGVZ) > 0) {
                buffer.append(ls).append("VZ Fehler:").append(ls);
                getFileContent(buffer, new File(lh.getFileErr(Globals.LOGGINGVZ)));
            }

            sendEmails(configurationController, buffer);
        }
    }

    private static void getFileContent(StringBuilder buffer, File csvFile) throws FileNotFoundException, IOException {
        if (csvFile != null && csvFile.exists() && csvFile.canRead()) {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String ls = System.getProperty("line.separator");
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append(ls);
            }
        }
    }
}
