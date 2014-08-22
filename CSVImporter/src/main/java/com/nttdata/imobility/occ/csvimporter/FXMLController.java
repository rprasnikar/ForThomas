package com.nttdata.imobility.occ.csvimporter;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private TextField eingabe;
    
    @FXML
    private TableView tabelle;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        
        FileChooser fc = new FileChooser();
        File ifile = fc.showOpenDialog(borderPane.getScene().getWindow());
        
//        String text = eingabe.getText();
        CSVReaderController c = new CSVReaderController();
        System.out.println("Pfad + Filename: " + ifile.getAbsolutePath());
        eingabe.setText(ifile.getAbsolutePath());
        c.setFilename(ifile.getAbsolutePath());
        c.setupReader();
        List<String[]> l = c.runReader();
        Iterator<String[]> i = l.iterator();
        String[] werte;
        while (i.hasNext()) {
            werte = i.next();
            System.out.println("Neue Zeile:");
            CustomerMediaJpaController jpa = new CustomerMediaJpaController();
            CustomerMedia cm;
            cm = new CustomerMedia(werte[1]);
            if (jpa.findCustomerMediaByTag(cm.getTag()).isEmpty())
                jpa.create(cm);
            for (String wert : werte) {
                System.out.println(wert);
            }
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
