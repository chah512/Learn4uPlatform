/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Category;
import services.ServiceCategory;
import utils.MyConnection;
import utils.categutils;
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


/**
 *
 * @author med_j
 */
public class ListCategoryController implements Initializable{
     @FXML
    private TableView<Category> table;
    @FXML
    private TableColumn<Category, Integer> col_id;
    @FXML
    private TableColumn<Category, String> col_name;
    @FXML
    private TableColumn<Category, String> col_content;
    
    private TableColumn<Category, Integer> col_nbr;
    ObservableList<Category> List = FXCollections.observableArrayList();
    ObservableList<Category> dataList;
    @FXML
    private Button supp;
    @FXML
    private TextField tfIdEv;
    @FXML
    private TextField tfNomEv;
    @FXML
    private TextField tfDescEv;
    ;
    
    private PreparedStatement pst;
    private Label lb1;
    private Label lb2;
    @FXML
    private TextField tfchercher;
    private Object titre;
    private final String cityAPI = "https://www.metaweather.com/api/location/search/?query=";

    private final String weatherAPI = "https://www.metaweather.com/api/location/";
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refresh();
    }

    public void setLb1(Label lb1) {
        this.lb1 = lb1;
    }

    public void setLb2(Label lb2) {
        this.lb2 = lb2;
    }

    @FXML
    private void getSelected(MouseEvent event) {
        int index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        //tfIdEv.setText((col_id.getCellData(index)).toString());
        tfNomEv.setText(col_name.getCellData(index));
        tfDescEv.setText(col_content.getCellData(index));
       
       
    }

    @FXML
    private void supp(ActionEvent event) {
        
        if (table.getSelectionModel().isEmpty()) {
             new Alert(Alert.AlertType.ERROR, "The table is empty ").show();
        } else {
            ObservableList<Category> bon = table.getSelectionModel().getSelectedItems();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to delete this category ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new ServiceCategory().deleteC(bon.get(0));
                System.out.println(bon.get(0).getId());
            }
        }
        refresh();
    }

    @FXML
    private void AjouterCategory(ActionEvent event) {
        ServiceCategory sp = new ServiceCategory();
        categutils au = new categutils();
        String name = tfNomEv.getText();
        String content = tfDescEv.getText();
       

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "name is required");
        } else if (content.isEmpty()) {
            JOptionPane.showMessageDialog(null, "content is required");
        } 
        
        else {
            sp.addC(new Category(tfNomEv.getText(), tfDescEv.getText()));

            JOptionPane.showMessageDialog(null, "category ajoutée !");
            
            refresh();
        }

    }

    @FXML
    private void modifier(ActionEvent event) {
        
        ServiceCategory sp = new ServiceCategory();
        categutils au = new categutils();
        String name = tfNomEv.getText();
        String content = tfDescEv.getText();
        
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "name is required");
        } else if (content.isEmpty()) {
            JOptionPane.showMessageDialog(null, "content is required");
        } 
        else {

            sp.updateC(new Category(tfNomEv.getText(), tfDescEv.getText()));
            JOptionPane.showMessageDialog(null, "Category modifié !");
            
            refresh();
        }
    }

    @FXML
    private void refresh() {
        ObservableList<Category> List = FXCollections.observableArrayList();
        try {
            Connection cnx = MyConnection.getInstance().getConnection();
           
           
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * from category");
            while (rs.next()) {
                List.add(new Category(rs.getInt(1), rs.getString("name"), rs.getString("content")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        
        table.setItems(List);
        table.refresh();
    }

    @FXML
    private void chercher(ActionEvent event) {
        ObservableList<Category> List = FXCollections.observableArrayList();
        try {
            Connection cnx = MyConnection.getInstance().getConnection();
            String text = tfchercher.getText();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM category where name='" + text + "'");

            while (rs.next()) {
                List.add(new Category(rs.getInt(1), rs.getString("name"), rs.getString("content")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        
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
   
    

}
