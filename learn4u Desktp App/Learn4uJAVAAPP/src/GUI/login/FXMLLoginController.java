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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.AuthService;
import utils.SessionManager;

/**
 * FXML Controller class
 *
 * @author MSI GF63
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Button tfsignin;
    @FXML
    private Button btnSignup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Login(ActionEvent event) throws Exception {
         Stage stage;
        AuthService service= new AuthService();
                Parent root;

        
        stage = (Stage) tfsignin.getScene().getWindow();
       if((tfemail.getText().equals("")||tfpassword.getText().equals("")))
       {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(" You need to fill email and password field ! ");
                 alert.showAndWait();
       }else
       {  if(service.Login(tfemail.getText(), tfpassword.getText()))
        {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("success");
                 alert.setHeaderText(null);
                 alert.setContentText(" Success! ");
                 alert.showAndWait();
            if(SessionManager.getRole().contains("ROLE_ADMIN"))
            {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/login/FXMLchooseByRole.fxml"));
            root = loader.load();
            stage = (Stage) btnSignup.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
            
            
            }else
            {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FXMLfront.fxml"));
            root = loader.load();
            stage = (Stage) btnSignup.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
             
        }
        }
       else
        { 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(" Oops ,the Email or Password is invalid ! ");
                 alert.showAndWait();
        }
       }    
    }

   

    @FXML
    private void redirectResetpwd(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/login/FXMLResetpwd.fxml"));

        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) btnSignup.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

    

    @FXML
    private void register(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/signup/FXMLSignup.fxml"));

        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) btnSignup.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    

    

    
    
}
