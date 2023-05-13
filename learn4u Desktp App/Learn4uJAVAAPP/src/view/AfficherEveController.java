/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import entity.Evenement;
import service.ServiceEvenement;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import java.util.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.io.*;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.embed.swing.SwingFXUtils;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Printer;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.print.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.DataSource;
import utils.Evenutils;
/**
 *
 * @author solta
 */
public class AfficherEveController implements Initializable {

    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, Integer> col_id;
    @FXML
    private TableColumn<Evenement, String> col_nom;
    @FXML
    private TableColumn<Evenement, String> col_desc;
    @FXML
    private TableColumn<Evenement, Date> col_debut;
    @FXML
    private TableColumn<Evenement, Date> col_fin;
    @FXML
    private TableColumn<Evenement, Integer> col_nbr;
    ObservableList<Evenement> List = FXCollections.observableArrayList();
    ObservableList<Evenement> dataList;
    private TextField tfIdEv;
    @FXML
    private TextField tfNomEv;
    @FXML
    private TextField tfDescEv;
    @FXML
    private DatePicker tfDateDebEv;
    @FXML
    private DatePicker tfDateFinEv;
    @FXML
    private TextField tfNbrEv;
    
    private PreparedStatement pst;
    private Label lb1;
    private Label lb2;
    @FXML
    private TextField tfchercher;
    private Object titre;
    private final String cityAPI = "https://www.metaweather.com/api/location/search/?query=";

    private final String weatherAPI = "https://www.metaweather.com/api/location/";
    @FXML
    private Button supp;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refresh();
    }
/*
    public void setLb1(Label lb1) {
        this.lb1 = lb1;
    }

    public void setLb2(Label lb2) {
        this.lb2 = lb2;
    }
*/

    @FXML
    private void handleMouseAction(MouseEvent event){
       Evenement ev = (Evenement) table.getSelectionModel().getSelectedItem();
       tfNomEv.setText(ev.getNom_ev());
       tfDescEv.setText(ev.getDescription());
       tfNbrEv.setText("" + ev.getNbr());
       tfIdEv.setText("" +ev.getId_ev());
       //tfDd.setText((Reservation.getDate_deb()).toString);
       //tfDf.setValue(Reservation.getDate_fin());
       
       
    }
    


    @FXML
    private void supp(ActionEvent event) {
        
        if (table.getSelectionModel().isEmpty()) {
             new Alert(Alert.AlertType.ERROR, "The table is empty ").show();
        } else {
            ObservableList<Evenement> bon = table.getSelectionModel().getSelectedItems();
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
        refresh();
    }

    @FXML
    private void AjouterEvenement(ActionEvent event) {
        ServiceEvenement sp = new ServiceEvenement();
        Evenutils au = new Evenutils();
        String nom = tfNomEv.getText();
        String description = tfDescEv.getText();
        String nbr = tfNbrEv.getText();

        if (nom.isEmpty()) {
            JOptionPane.showMessageDialog(null, "le nom ne doit pas etre vide");
        } else if (description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la description ne doit pas etre vide");
        } else if (nbr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "le nombre ne doit pas etre vide");
        } else if (tfDateDebEv.getValue() == null) {
            JOptionPane.showMessageDialog(null, "la date de debut ne doit pas etre vide");
        } else if (tfDateFinEv.getValue() == null) {
            JOptionPane.showMessageDialog(null, "la date de fin ne doit pas etre vide");
        } else if (!au.testNom(nom)) {
            JOptionPane.showMessageDialog(null, "le nom doit contenir des caracteres");
        } else if (!au.testNom(description)) {
            JOptionPane.showMessageDialog(null, "la description doit contenir des caracteres");
        } else if (!au.testNbr(nbr)) {
            JOptionPane.showMessageDialog(null, "le nombre doit contenir des chiffres");
        } else if (!testdatedeb()){JOptionPane.showMessageDialog(null, "la date de debut doit commencer a partir d aujordhui");}
        else if (!testda()){JOptionPane.showMessageDialog(null, "la date de debut doit inferieure a la date fin");}
        else {
            sp.ajouter(new Evenement(tfNomEv.getText(), tfDescEv.getText(), Date.valueOf(tfDateDebEv.getValue()), Date.valueOf(tfDateFinEv.getValue()), Integer.parseInt(tfNbrEv.getText())));

            JOptionPane.showMessageDialog(null, "Evenement ajoutée !");
            
            refresh();
        }

    }
 @FXML
    private void modifier(ActionEvent event) {
        ServiceEvenement sp = new ServiceEvenement();
        
            sp.modifier(new Evenement(Integer.parseInt(tfIdEv.getText()), tfNomEv.getText(), tfDescEv.getText(), Integer.parseInt(tfNbrEv.getText())));
            JOptionPane.showMessageDialog(null, "Evenement modifié !");
            
            refresh();
        
    }


    @FXML
    private void refresh() {
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
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_ev"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_ev"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_debut.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
        col_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbr"));
        //col_jeu.setCellValueFactory(new PropertyValueFactory<>("nom_jeu"));
        table.setItems(List);
        table.refresh();
    }

    @FXML
    private void chercher(ActionEvent event) {
        ObservableList<Evenement> List = FXCollections.observableArrayList();
        try {
            Connection cnx = DataSource.getInstance().getConnection();
            String text = tfchercher.getText();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM calendar where titre='" + text + "'");

            while (rs.next()) {
                List.add(new Evenement(rs.getInt(1), rs.getString("titre"), rs.getString("description"), rs.getDate("start"), rs.getDate("end"), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_ev"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_ev"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_debut.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
        col_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbr"));
        table.setItems(List);
        table.refresh();
    }

   
    
    @FXML
    private void print(ActionEvent event) {
         
 PrinterJob printerJob = PrinterJob.createPrinterJob();
   if(printerJob.showPrintDialog(null) && printerJob.printPage(table))
       table.setScaleX(0.60);
                        table.setScaleY(0.60);
                        table.setTranslateX(-500);
       printerJob.endJob();
       table.setScaleX(1);
                        table.setScaleY(1);
                        table.setTranslateX(0);
    }

    @FXML
    private void pdf(ActionEvent event) {
        
 PrinterJob job = PrinterJob.createPrinterJob();
 if (job != null) {
     table.setScaleX(0.60);
                        table.setScaleY(0.60);
                        table.setTranslateX(-500);
    boolean success = job.printPage(table);
    
    if (success) {
        
        job.endJob();
        table.setScaleX(1);
                        table.setScaleY(1);
                        table.setTranslateX(0);

    }
 }
    }
    private Boolean testdatedeb(){
    LocalDate now = LocalDate.now();
    if( tfDateDebEv.getValue().compareTo(now)>=0)
    {
        return true;
    }
   
    return false;
    }
    private Boolean testda(){
   
    if(tfDateFinEv.getValue().compareTo(tfDateDebEv.getValue()) > 0){
    return true;
    }
    return false;
    }
    
    public void showEvents(){
        ObservableList<Evenement> List = FXCollections.observableArrayList();
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_ev"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_ev"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_debut.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
        col_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbr"));
        table.setItems(List);
    }
    /* 
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {        
        
        if(event.getSource() == modifier){
            updateRecord();
            
    }
    /*
    
    }
    /*
    private void updateRecord() throws SQLException {
        Connection cnx = DataSource.getInstance().getConnection();
        String query = "UPDATE evenement SET nom_ev='"+tfNomEv.getText()+"',description='" + tfDescEv.getText()+ "',nbr='" + (Integer.parseInt(tfNbrEv.getText())) + "' WHERE id_ev =" + tfIdEv.getText()+ "";
        Statement st = cnx.createStatement();
       
            st.executeUpdate(query);
            System.out.println("Reservation Modifier !");
        showEvents();
    }
*/

 
   
   
}
