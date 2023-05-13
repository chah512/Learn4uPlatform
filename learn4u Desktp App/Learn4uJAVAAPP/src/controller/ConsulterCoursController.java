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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidevv.Datasource;
import service.CoursService;
import service.formationcrud;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ConsulterCoursController implements Initializable {

    @FXML
    private TableView<Cours> tbl;
    @FXML
    private TableColumn<Cours, String> nom;
    @FXML
    private TableColumn<Cours, String> description;
    @FXML
    private TableColumn<Cours, Integer> id;
    @FXML
    private TableColumn<Cours, Integer> cforma;
    @FXML
    private Button supp;
    @FXML
    private Button mod;
    @FXML
    private TextField filterField;
    @FXML
    private AnchorPane slider;
    @FXML
    private Button ccours;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;
    @FXML
    private Button ajouterr;
     private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    public static String nom_recup;
    public static String decriptionrecup;
    public static Date date_deprecup;
     public static int id_rec;
    @FXML
    private Label ff;
    @FXML
    private Label st;
    @FXML
    private Label dddd;
    @FXML
    private Button ajj;
    @FXML
    private Button act;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CoursService evcrud = new CoursService();
        ArrayList<Cours> ev = (ArrayList<Cours>) evcrud.readAll();
        ObservableList<Cours> obs = FXCollections.observableArrayList(ev);
        //table.setItems(obs);
      nom.setCellValueFactory(new PropertyValueFactory<Cours,String>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<Cours,String>("description"));
       
        
        cforma.setCellValueFactory(new PropertyValueFactory<Cours,Integer>("formations_id"));
        
        id.setCellValueFactory(new PropertyValueFactory<Cours,Integer>("id"));
        
         
     
 FilteredList<Cours> filteredData = new FilteredList<>(FXCollections.observableArrayList(ev), b -> true);
 	// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(events -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (events.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (events.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Cours> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tbl.comparatorProperty());

        tbl.setItems(sortedData);
        
        // TODO
    }    

    @FXML
    private void supprimer(ActionEvent event) {
         Cours ev = tbl.getSelectionModel().getSelectedItem();
        CoursService udao = CoursService.getInstance();
        udao.supprimerCours(ev);
        System.out.println(ev.getDescription());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Formation supprimer!");
                        alert.show();

        tbl.refresh();
    }

    @FXML
    private void modifier(ActionEvent event) {
        Cours ev = tbl.getSelectionModel().getSelectedItem();
        ConsulterCoursController.nom_recup=ev.getNom();
        ConsulterCoursController.decriptionrecup=ev.getDescription();
        ConsulterCoursController.date_deprecup=(Date) ev.getDteAjout();
        
                ConsulterCoursController.id_rec=ev.getId();

        System.out.println(ev.getId());
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/updateCours.fxml"));
            Stage stage = (Stage) mod.getScene().getWindow();
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

    @FXML
    private void cours(ActionEvent event) {
    }

    @FXML
    private void ajoutere(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ajouter_cours.fxml"));
            Stage stage = (Stage) ajouterr.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fff(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
            Stage stage = (Stage) ff.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void stt(MouseEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/stat.fxml"));
            Stage stage = (Stage) st.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
}

    @FXML
    private void ddd(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterCours.fxml"));
            Stage stage = (Stage) dddd.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajjj(ActionEvent event) {
        Cours ev = tbl.getSelectionModel().getSelectedItem();
        ConsulterCoursController.nom_recup=ev.getNom();
        ConsulterCoursController.decriptionrecup=ev.getDescription();
        
        
                ConsulterCoursController.id_rec=ev.getId();

        System.out.println(ev.getId());
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLCours.fxml"));
            Stage stage = (Stage) ajj.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void actt(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterCours.fxml"));
            Stage stage = (Stage) act.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
