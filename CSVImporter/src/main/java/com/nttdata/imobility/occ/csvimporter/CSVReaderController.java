/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package com.nttdata.imobility.occ.csvimporter;

import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Robert
 */
public class CSVReaderController {
    private Reader reader;
    private String filename;
    private List<String[]> data;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public CSVReaderController() {
    }
    
    public void setupReader() {
        try {
            reader = new FileReader(filename);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVReaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<String[]> runReader () {
        CSVReader<String[]> csvParser = CSVReaderBuilder.newDefaultReader(reader);
        try {
            data = csvParser.readAll();
            
        } catch (IOException ex) {
            Logger.getLogger(CSVReaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    
//    Reader reader = new FileReader("persons.csv");
//
//CSVReader<Person> csvPersonReader = ...;
//
//// read all entries at once
//List<Person> persons = csvPersonReader.readAll();
//
//// read each entry individually
//Iterator<Person> it = csvPersonReader.iterator();
//while (it.hasNext()) {
//  Person p = it.next();
//  // ...
//}


}
