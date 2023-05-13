/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI GF63
 */
public class FXMLchooseByRoleController implements Initializable {

    @FXML
    private Button user;
    @FXML
    private Button admin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void user(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FXMLfront.fxml"));

        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) admin.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void admin(ActionEvent event) {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/UsersList/FXMLParent.fxml"));

        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) admin.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
