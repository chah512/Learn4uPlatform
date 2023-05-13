/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.Avis;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.util.Duration;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import services.ServiceAvis;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author med
 */
public class ListratingController implements Initializable {
    static Stage stageAdd;
    static Avis d;
    @FXML
    private TableColumn<Avis, String>rating;
    @FXML
    private TableColumn<Avis, String> title;
    @FXML
    private TableColumn<Avis, String> category;
    @FXML
    private TableColumn<Avis, String> content;
    @FXML
    private Button supprimer;
    @FXML
    private TextField tfsearch;
  
    @FXML
    private TableView<Avis> table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ServiceAvis sa = new ServiceAvis();
        ArrayList arrayList = (ArrayList) sa.afficher();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
       rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
       title.setCellValueFactory(new PropertyValueFactory<>("title"));
      category.setCellValueFactory(new PropertyValueFactory<>("category"));
      content.setCellValueFactory(new PropertyValueFactory<>("content"));
        // TODO
    }    

   

    @FXML
    private void supprimer(ActionEvent event) {
        
               if (table.getSelectionModel().isEmpty()) {
             new Alert(Alert.AlertType.ERROR, "The table is empty ").show();
        } else {
            ObservableList<Avis> bon = table.getSelectionModel().getSelectedItems();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to delete this rate");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new ServiceAvis().deleteA(bon.get(0));
                System.out.println(bon.get(0).getId());
            }
        }
        list();
        
        
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
    @FXML
    private void chercher(ActionEvent event) {
        ObservableList<Avis> List = FXCollections.observableArrayList();
        ServiceAvis sa = new ServiceAvis();
        try {
            Connection cnx = MyConnection.getInstance().getConnection();
            String text = tfsearch.getText();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM avis where title='" + text + "'");

            while (rs.next()) {
                List.add(new Avis(rs.getInt(1), rs.getString("rating"), rs.getString("title"), rs.getString("category"), rs.getString("content")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListratingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        content.setCellValueFactory(new PropertyValueFactory<>("content"));
        
        table.setItems(List);
        table.refresh();
    }
    
    @FXML
       public void list(){
        ServiceAvis sa = new ServiceAvis();
        ArrayList arrayList = (ArrayList) sa.afficher();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
       
}
   
    private void gotorate(ActionEvent event) throws IOException {
         d = table.getSelectionModel().getSelectedItem();

            
            
          
        //Stage stageModifier = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/rating.fxml"));
        Scene scene = new Scene(root);
        stageAdd = new Stage();
        stageAdd.setScene(scene);
        stageAdd.show();
        // setNode(FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/ModifierCategorie.fxml")));
    }
    @FXML
    private void gotostat(ActionEvent event) throws IOException {
         d = table.getSelectionModel().getSelectedItem();

        //Stage stageModifier = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/PieChart.fxml"));
        Scene scene = new Scene(root);
        stageAdd = new Stage();
        stageAdd.setScene(scene);
        stageAdd.show();
        // setNode(FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/ModifierCategorie.fxml")));
    }
    
}
