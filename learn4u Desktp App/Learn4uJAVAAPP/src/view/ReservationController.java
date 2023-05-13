/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import service.ServiceEvenement;
import service.ServiceReservation;
import entity.Evenement;
import entity.Reservations;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import utils.DataSource;
import utils.Evenutils;

/**
 * FXML Controller class
 *
 * @author solta
 */
/**
 * FXML Controller class
 *
 * @author solta
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField tftel;
    @FXML
    private TextField tfreservations;
    @FXML
    private TextField tfEn;
    @FXML
    private TableView<Evenement> tvevents;
    @FXML
    private TableColumn<Evenement, Integer> colId;
    @FXML
    private TableColumn<Evenement, String> colEv;
    @FXML
    private TableColumn<Evenement, String> colDes;
    @FXML
    private TableColumn<Evenement, Date> colDd;
    @FXML
    private TableColumn<Evenement, Date> colDf;
    @FXML
    private TableColumn<Evenement, Integer> colNbr;
    @FXML
    private Button btnreserver;
    private TextField tfNbr;
     private TextField tfId;
      private TextField tfDes;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      ObservableList<Evenement> List = FXCollections.observableArrayList();
        try {
            Connection cnx = DataSource.getInstance().getConnection();
           
           
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * from calendar");
            while (rs.next()) {
                Evenement a = new Evenement();
                a.setDate_deb(rs.getDate("start"));
                a.setDate_fin(rs.getDate("end"));
                a.setDescription(rs.getString("description"));
                a.setId_ev(rs.getInt("id"));
                a.setNom_ev(rs.getString("titre"));
                
                List.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id_ev"));
        colEv.setCellValueFactory(new PropertyValueFactory<>("nom_ev"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDd.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
        colDf.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        colNbr.setCellValueFactory(new PropertyValueFactory<>("nbr"));
        
        //col_jeu.setCellValueFactory(new PropertyValueFactory<>("nom_jeu"));
        tvevents.setItems(List);
       
    }    
    
       
    /*private void getSelected(MouseEvent event) {
        int index = tvevents.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        
        tfEn.setText(colEv.getCellData(index));
        
        
    }*/
    private void supp(ActionEvent event) {
        
        if (tvevents.getSelectionModel().isEmpty()) {
             new Alert(Alert.AlertType.ERROR, "The table is empty ").show();
        } else {
            ObservableList<Evenement> bon = tvevents.getSelectionModel().getSelectedItems();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to delete this category ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new ServiceEvenement().supprimer(bon.get(0));
                System.out.println(bon.get(0).getId_ev());
            }
        }
        
    }
      
    
    
    private void AjouterReservation(ActionEvent event) {
        if (Integer.parseInt(tfNbr.getText())== 0){
             new Alert(Alert.AlertType.ERROR, "The table is empty ").show();
        } else {
        
           
        ServiceReservation sr = new ServiceReservation();
           
            
            sr.ajouterR(new Reservations(tfEn.getText(),Integer.parseInt(tftel.getText()), Integer.parseInt(tfreservations.getText())));
            

            JOptionPane.showMessageDialog(null, "Reservation ajoutée !");
            
           
    }
        }
    public Connection getConnection(){
        Connection cnx;
        try{
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn4u", "root","");
            return cnx;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    
    /*@FXML
    private void modifierR() {
        String query = "UPDATE evenement SET nom_ev='"+tfEn.getText()+"',description='" + tfDes.getText()+"',date_deb='" + tfDd.getValue()+"',date_fin='" + tfDf.getValue()  + "',nbr='" + (Integer.parseInt(tfNbr.getText())-Integer.parseInt(tfreservations.getText())) + "' WHERE id_ev =" + tfId.getText()+ "";
       
        executeQuery(query);
    
        }*/
    private void executeQuery(String query) {
        Connection cnx = getConnection();
        Statement st;
        try{
            st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("********************************* SUCCESS *************************************");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
    }

    @FXML
    private void modifierR(ActionEvent event) {
    }
}
    /*
     @FXML
    void ModifierRes(ActionEvent event) {
        ServiceReservation sr = new ServiceReservation();
 
       sr.modifier(new Reclamation(tfTypeRec.getText(),tfDescRec.getText(),tfDateRec.getDate()));
       noti();
       tableRec.refresh();
        //JOptionPane.showMessageDialog(null, "Offre modifié !");
  if (tvevents.getSelectionModel().getSelectedItem() != null) {

            Evenement t = tvevents.getSelectionModel().getSelectedItem();

            sr.modifierR(t);
            Alert EventAlert = new Alert(Alert.AlertType.INFORMATION);
            EventAlert.setTitle("Modifier");
            EventAlert.setHeaderText(null);

            EventAlert.setContentText("Reclamation est modifiée avec succès");
            EventAlert.showAndWait();

        } else {
         
            Alert selectEventAlert = new Alert(Alert.AlertType.WARNING);
            selectEventAlert.setTitle("Sélectionner une reclamation");
            selectEventAlert.setHeaderText(null);
            selectEventAlert.setContentText("Tu dois sélectionner une reclamation d'abord!");
            selectEventAlert.showAndWait();
            //Alert Select Book !
        }
    }
    }*/


    
    

