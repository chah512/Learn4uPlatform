/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import entity.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.DataSource;
/**
 *
 * @author solta
 */
public class EveController implements Initializable {

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
    @FXML
    private TextField tfIdEv;
    ObservableList<Evenement> List = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Evenement> List = FXCollections.observableArrayList();
        try {
            Connection cnx = DataSource.getInstance().getConnection();
           
           
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * from evenement");
            while (rs.next()) {
                List.add(new Evenement(rs.getInt(1), rs.getString("nom_evenement"), rs.getString("description_evenement"), rs.getDate("date_debut"), rs.getDate("date_fin"), rs.getInt(6)));
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
    private void getSelected(MouseEvent event) {
    }

    
    
}