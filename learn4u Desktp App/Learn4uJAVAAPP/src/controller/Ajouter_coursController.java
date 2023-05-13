/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cours;
import entity.Formation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pidevv.Datasource;
import service.CoursService;
import utils.DataValidationUtils;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class Ajouter_coursController implements Initializable {

    @FXML
    private AnchorPane show;
    @FXML
    private TextField nom;
    @FXML
    private TextField location;
    @FXML
    private Text nomfor;
    @FXML
    private DatePicker date_depart;
    @FXML
    private Text descri;
    @FXML
    private Button ajouter;
    @FXML
    private Button consulter;
    @FXML
    private AnchorPane slider;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;
 
    @FXML
    private Text descri1;
    @FXML
    private Text descri2;
     private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    @FXML
    private ComboBox<String> Cformattion;
  
    CoursService eventcru = CoursService.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection cnx = Datasource.getInstance().getCnx();
            rs = cnx.createStatement().executeQuery("SELECT nom FROM formmattion ");
            // Now add the comboBox addAll statement
           while (rs.next()){
            Cformattion.getItems().addAll(rs.getString("nom"));
           
           }
            } catch (SQLException ex) {
            Logger.getLogger(Ajouter_coursController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
          
              
    }   

    @FXML
    private void ajouter_event(ActionEvent event) throws SQLException {
        if (nom.getText().isEmpty() || location.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Check your fields! ");
                alert.show();
               System.out.println("Fields Are Empty");
            // show.setText("Enter all details");

        }else 
        if (!DataValidationUtils.isNomValid(nom.getText().replaceAll("\\s", ""))) {
                nom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                System.out.println("Cours name is invalid");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("invalid product name!");
                alert.show();
        }else 
        if (!DataValidationUtils.isDescriptionValid(location.getText().replaceAll("\\s", ""))) {
                location.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                System.out.println("Description is invalid");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("invalid product name!");
                alert.show();
        }
        else {
            String nom_event = nom.getText();
            
           
            String loc = location.getText();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            //String date_dep = date_depart.getValue().toString();
            Date date_depar = java.sql.Date.valueOf( date_depart.getValue());
           // String date_f = date_fin.getValue().toString();
            
            System.out.println(" date depare de type date " + date_depart);
            
            //System.out.println("date de depare de type string" + date_dep);
          //  System.out.println("date fin de type sting" + date_f);
           

            
            
                 ResultSet rs6 = null;
                 
        Connection cnx = Datasource.getInstance().getCnx();
        
        String v= String.valueOf(Cformattion.getValue());
         rs6 = cnx.createStatement().executeQuery("SELECT id FROM formmattion where nom='"+v+"'");  
     
        rs6.next();
        int id1 = rs6.getInt("id");
   
        Cours ev = new Cours(nom_event, loc,date_depar,id1);
            eventcru.ajouterCoursPST(ev);
            
             System.err.println("Added Seccessfully");
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Cours added successfuly!");
                        alert.show();
                        nom.setText("");
                        location.setText(" ");
              
            
            
               System.err.println("Added Seccessfully");
            

        }
    }

    @FXML
    private void consulter_event(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterEvent.fxml"));
            Stage stage = (Stage) consulter.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void form(MouseEvent event) {
    }

    @FXML
    private void promo(MouseEvent event) {
    }

    @FXML
    private void abon(MouseEvent event) {
    }

    @FXML
    private void stat(MouseEvent event) {
    }
    
}
