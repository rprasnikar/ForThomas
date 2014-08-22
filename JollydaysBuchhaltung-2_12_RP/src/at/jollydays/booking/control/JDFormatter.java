/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.control;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 *
 * @author Gunter Reinitzer
 */
public class JDFormatter extends Formatter {
    private DateFormat dateFormat;
    static final String lineSep = System.getProperty("line.separator");

    @Override
    public String format(LogRecord record) {
        StringBuilder buf = new StringBuilder(180);

        if (dateFormat == null) {
            dateFormat = DateFormat.getDateTimeInstance();
        }

        buf.append(dateFormat.format(new Date(record.getMillis())));
        buf.append(' ');
//        buf.append(record.getSourceClassName());
//        buf.append(' ');
//        buf.append(record.getSourceMethodName());
//        buf.append(lineSep);

        buf.append(record.getLevel());
        buf.append(": ");
        buf.append(formatMessage(record));
        buf.append(" (");
        buf.append(record.getSourceMethodName());
        buf.append(')');

        buf.append(lineSep);

        Throwable throwable = record.getThrown();
        if (throwable != null) {
            StringWriter sink = new StringWriter();
            throwable.printStackTrace(new PrintWriter(sink, true));
            buf.append(sink.toString());
        }



        return buf.toString();
    }
}
