/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entities.Avis;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import services.ServiceAvis;

/**
 * FXML Controller class
 *
 * @author med
 */
public class rateController implements Initializable {

    @FXML
    private ComboBox<String> rating;
    @FXML
    private TextField title;
    @FXML
    private ComboBox<String> category;
    @FXML
    private TextField content;
   
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
       
        ObservableList<String> listr = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> listc = FXCollections.observableArrayList("java","symfony","codename one");
         rating.setItems(listr);
         category.setItems(listc);
    }    

    @FXML
    private void onSave(ActionEvent event) {
      
        Avis p = new Avis(rating.getValue(), title.getText(), category.getValue(),content.getText());
        ServiceAvis ps = new ServiceAvis();
        
        if (!title.getText().equals("")&& !content.getText().equals("") )
         {
                   ps.addA(p);
                     new Alert(Alert.AlertType.INFORMATION, "Avis ajouté").show();
     
         }
         else
         {
                  new Alert(Alert.AlertType.ERROR, "Ecrivez votre titre et commentaire ").show();
         }
       
        
       
        
    }
    
}
