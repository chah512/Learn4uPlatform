/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entities.Category;
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
import utils.MyConnection;

/**
 *
 * @author med_j
 */
public class categoryController implements Initializable{
    
    @FXML
    private TableView<Category> table;
    @FXML
    private TableColumn<Category, Integer> col_id;
    @FXML
    private TableColumn<Category, String> col_name;
    @FXML
    private TableColumn<Category, String> col_content;

    @FXML
    private TextField tfId;
    ObservableList<Category> List = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
 
        //col_jeu.setCellValueFactory(new PropertyValueFactory<>("nom_jeu"));
        table.setItems(List);
        table.refresh();
    }    

    @FXML
    private void getSelected(MouseEvent event) {
    }

}
