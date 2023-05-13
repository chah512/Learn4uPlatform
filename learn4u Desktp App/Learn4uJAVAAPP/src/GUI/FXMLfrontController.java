/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class FXMLfrontController implements Initializable {

    @FXML
    private Button profile;
    @FXML
    private Button rate;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button home;
    @FXML
    private Button event;
    @FXML
    private Button course;
    @FXML
    private Button reclama;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void profile(ActionEvent event) throws IOException {
         pane.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/profile/FXMLProfile.fxml"));
        pane.getChildren().add(newLoadedPane);
    }

    @FXML
    private void rate(ActionEvent event) throws IOException {
         pane.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/rating.fxml"));
        pane.getChildren().add(newLoadedPane);
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) throws IOException {
         pane.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/view/Reservation.fxml"));
        pane.getChildren().add(newLoadedPane);
    }

    @FXML
    private void course(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
         AuthService service=new AuthService();
           if( service.Logout(SessionManager.getEmail()))
           {
            SessionManager.cleanUserSession();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/login/FXMLLogin.fxml"));
            Parent root;
            Stage stage;
            try {
                root = loader.load();
                stage = (Stage) home.getScene().getWindow();
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
    
}
