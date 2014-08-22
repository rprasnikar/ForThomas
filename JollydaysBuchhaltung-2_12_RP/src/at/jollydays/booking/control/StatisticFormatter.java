/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.control;

import java.util.logging.LogRecord;

/**
 *
 * @author Gunter Reinitzer
 */
public class StatisticFormatter extends JDFormatter {
    // SimpleFormatter oder XMLFormatter
    int logCounter = 0;
    
    @Override
    public synchronized String format(LogRecord record) {
        logCounter++;
        return super.format(record);
    }

    public int getLogCounter() {
        return logCounter;
    }
}