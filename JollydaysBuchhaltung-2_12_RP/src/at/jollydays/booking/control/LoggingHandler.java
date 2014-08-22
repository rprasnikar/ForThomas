/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.control;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gunter Reinitzer
 */
public class LoggingHandler {

    File root;
    String globaldetail;
    HashMap<String,JDLogger> loggerMap;

    private class JDLogger {

        private StatisticFormatter formatter_ALL;
        private StatisticFormatter formatter_ERR;
        private String file_ALL;
        private String file_ERR;
        private FileHandler fh_ALL;
        private FileHandler fh_ERR;
        private String topic;
        private Logger logger;

        public JDLogger(String topic) throws IOException {
            this(topic, globaldetail);
        }

        public JDLogger(String topic, String localdetail) throws IOException {
            this.topic = topic;
            logger = Logger.getLogger(topic);
            logger.setLevel(Level.ALL);

            file_ALL = root.getCanonicalPath() + File.separator + topic + localdetail + ".log";
            fh_ALL = new FileHandler(file_ALL, true);
            formatter_ALL = new StatisticFormatter();
            fh_ALL.setFormatter(formatter_ALL);
            fh_ALL.setLevel(Level.ALL);
            logger.addHandler(fh_ALL);

            file_ERR = root.getCanonicalPath() + File.separator + topic + localdetail + "_ERR.log";
            fh_ERR = new FileHandler(file_ERR, true);
            formatter_ERR = new StatisticFormatter();
            fh_ERR.setFormatter(formatter_ERR);
            fh_ERR.setLevel(Level.SEVERE);
            logger.addHandler(fh_ERR);
        }
        
        public FileHandler getFhAll() {
            return fh_ALL;
        }

        public FileHandler getFhErr() {
            return fh_ERR;
        }

        public Logger getLogger() {
            //return Logger.getLogger(topic);
            return logger;
        }

        public int getCountAll() {
            return formatter_ALL.getLogCounter();
        }

        public int getCountErr() {
            return formatter_ERR.getLogCounter();
        }

        public String getFileAll() {
            return file_ALL;
        }

        public String getFileErr() {
            return file_ERR;
        }

    }

    public LoggingHandler(File root, String localdetail) {
        this.root = root;
        this.globaldetail = localdetail;

        loggerMap = new HashMap();

        try {
            String topic;
            if (!root.exists()) {
                root.mkdir();
            }

        } catch (Exception ex) {
            Logger.getLogger(LoggingHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void close() {

        for (String topic : loggerMap.keySet()) {
            JDLogger logger = loggerMap.get(topic);
            logger.getFhAll().flush();
            logger.getFhAll().close();
            logger.getFhErr().flush();
            logger.getFhErr().close();
        }

    }

    public Logger getLogger(String topic) throws IOException {
        getJDLogger(topic);
        JDLogger logger = loggerMap.get(topic);
        if (logger != null) {
            return logger.getLogger();
        }
        return null;
    }

    public Logger getLogger(String topic, String localdetail) throws IOException {
        getJDLogger(topic, localdetail);
        JDLogger logger = loggerMap.get(topic);
        if (logger != null) {
            return logger.getLogger();
        }
        return null;
    }

    public int getCountAll(String topic) throws IOException {
        JDLogger logger = loggerMap.get(topic);
        if (logger == null) {
            return 0;
        }
        return getJDLogger(topic).getCountAll();
    }

    public int getCountErr(String topic) throws IOException {
        JDLogger logger = loggerMap.get(topic);
        if (logger == null) {
            return 0;
        }
        return getJDLogger(topic).getCountErr();
    }

    private JDLogger getJDLogger(String topic) throws IOException {
        JDLogger logger = loggerMap.get(topic);
        if (logger == null) {
            logger = new JDLogger(topic);
            loggerMap.put(topic, logger);
        }
        return logger;
    }

    private JDLogger getJDLogger(String topic, String localdetail) throws IOException {
        JDLogger logger = loggerMap.get(topic);
        if (logger == null) {
            logger = new JDLogger(topic, localdetail);
            loggerMap.put(topic, logger);
        }
        return logger;
    }

    public String getFileAll(String topic) throws IOException {
        return getJDLogger(topic).getFileAll();
    }

    public String getFileErr(String topic) throws IOException {
        return getJDLogger(topic).getFileErr();
    }

}
