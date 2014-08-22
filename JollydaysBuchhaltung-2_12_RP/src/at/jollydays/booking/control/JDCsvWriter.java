/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.jollydays.booking.control;

import com.csvreader.CsvWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Gunter Reinitzer
 */
public class JDCsvWriter extends CsvWriter {
    private int recordCounter = 0;
    private ArrayList<ArrayList> listLines = null;
    private ArrayList<ArrayList> listLinesFinally = null;
    private ArrayList<String> listValues = null;
    private boolean deferred = false;
    private boolean lastFinally = false;

    public JDCsvWriter(String path) {
        super(path);
    }

//    @Override
//    public void endRecord() throws IOException, IOException, IOException {
//        super.endRecord();
//        recordCounter++;
//    }

    @Override
    public void writeRecord(String[] values) throws IOException {
        super.writeRecord(values);
        recordCounter++;
    }

    @Override
    public void writeRecord(String[] values, boolean bln) throws IOException {
        super.writeRecord(values, bln);
        recordCounter++;
    }

    public int getRecordCount() {
        return recordCounter;
    }

    @Override
    public void write(String value) throws IOException  {
        if (deferred) {
            if (listValues == null) {
                listValues = new ArrayList();
            }
            listValues.add(value);
        } else {
            super.write(value);
        }
    }

    @Override
    public void endRecord() throws IOException {
        recordCounter++;
        if (deferred) {
            if (listLines == null) {
                listLines = new ArrayList();
            }
            listLines.add(listValues);
            listValues = null;
        } else {
            super.endRecord();
        }
        lastFinally = false;
    }

    public void endRecordFinally() throws IOException {
        recordCounter++;
        if (deferred) {
            if (listLinesFinally == null) {
                listLinesFinally = new ArrayList();
            }
            listLinesFinally.add(listValues);
            listValues = null;
        } else {
            super.endRecord();
        }
        lastFinally = true;
    }


    public void deferredFlush() throws IOException {
        if (listLines != null) {
            for (ArrayList<String> values : listLines) {
                for (String value : values) {
                    this.write(value);
                }
                this.endRecord();
            }
        }
        if (listLinesFinally != null) {
            for (ArrayList<String> values : listLinesFinally) {
                for (String value : values) {
                    this.write(value);
                }
                this.endRecord();
            }
        }
        listLines = null;
        listLinesFinally = null;
        deferred = false;
    }

    public boolean isDeferred() {
        return deferred;
    }

    public boolean isLastFinally() {
        return lastFinally;
    }

    public void setDeferred(boolean deferred) {
        this.deferred = deferred;
    }

}
