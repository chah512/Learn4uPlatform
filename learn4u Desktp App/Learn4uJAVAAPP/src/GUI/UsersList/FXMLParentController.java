/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.UsersList;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.AuthService;
import utils.SessionManager;

/**
 * FXML Controller class
 *
 * @author MSI GF63
 */
public class FXMLParentController implements Initializable {

    @FXML
    private Button btnhome;
    @FXML
    private Button tfuserlists;
    @FXML
    private Button tfprofile;
    @FXML
    private Pane tfparent;
    @FXML
    private Button tfromationmanagement;
    @FXML
    private Button rating;
    @FXML
    private Button category;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redirectuserMnagement(ActionEvent event) throws IOException {
        tfparent.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/UsersList/FXMLUser.fxml"));
        tfparent.getChildren().add(newLoadedPane); 
    }

    @FXML
    private void redirectProfile(ActionEvent event) throws IOException {
       tfparent.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/profile/FXMLProfile.fxml"));
        tfparent.getChildren().add(newLoadedPane); 
    }

    @FXML
    private void Logout(ActionEvent event) {
       
            AuthService service=new AuthService();
           if( service.Logout(SessionManager.getEmail()))
           {
            SessionManager.cleanUserSession();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/login/FXMLLogin.fxml"));
            Parent root;
            Stage stage;
            try {
                root = loader.load();
                stage = (Stage) btnhome.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }else
           {Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Success !");
                 alert.setHeaderText(null);
                 alert.setContentText("You have Successfully updated User!");
                 alert.showAndWait();}
        }

    @FXML
    private void redirectahmed(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/consulterEvent.fxml"));

        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) tfromationmanagement.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void ratingredirect(ActionEvent event) throws IOException {
        tfparent.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/Listrating.fxml"));
        tfparent.getChildren().add(newLoadedPane); 
    }

    @FXML
    private void categoryredirect(ActionEvent event) throws IOException {
        tfparent.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/ListCategory.fxml"));
        tfparent.getChildren().add(newLoadedPane); 
    }

    @FXML
    private void events(ActionEvent event) throws IOException {
        tfparent.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/view/AfficherEve.fxml"));
        tfparent.getChildren().add(newLoadedPane); 
    }
        
    }
    

