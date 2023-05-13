/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.signup;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.AuthService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author MSI GF63
 */
public class FXMLSignupController implements Initializable {

    @FXML
    private TextField tffirstname;
    @FXML
    private TextField tflastname;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private PasswordField tfverifypassword;
    @FXML
    private DatePicker tfbirthday;
    @FXML
    private Button tfsignup;
    @FXML
    private Button tfcancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Signup(ActionEvent event) {
         String regex = "^(.+)@(.+)$";
          Pattern email=Pattern.compile(regex);
          Matcher emailmatch=email.matcher(tfemail.getText());
          String name = "^[a-zA-Z]+";
          Pattern nameP=Pattern.compile(name);
          Matcher namematch=nameP.matcher(tffirstname.getText());
           Matcher Lnamematch=nameP.matcher(tflastname.getText());
        UserService service=new UserService();
        AuthService auth=new AuthService();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/login/FXMLLogin.fxml"));

                Parent root;
                Stage stage;

        ObservableList<User> emailcheck = service.SearchUsers(tfemail.getText());
        ObservableList<User> usernamecheck = service.SearchUsers(tfusername.getText());
        if((tfemail.getText().equals(""))||(tffirstname.getText().equals(""))||(tflastname.getText().equals(""))||(tfpassword.getText().equals(""))||(tfbirthday.getValue().equals("")))
                {Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("Please fill all fields !");
                 alert.showAndWait();}
        else
        if(emailmatch.matches()==false)
         {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(tfemail.getText()+" Is not a valid email !");
                 alert.showAndWait();
         }
        else
            if((namematch.matches()==false)||(Lnamematch.matches()==false))
            {Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("You can't have Special caracters or number in first name or last name !");
                 alert.showAndWait();}
        else
        if(tfpassword.getText().length()<6)
        {        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("Password must be longer than 6 caracters");
                 alert.showAndWait();}
        else
            if(!tfpassword.getText().equals(tfverifypassword.getText()))
        {        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("Passwords don't match !");
                 alert.showAndWait();}
        else
            if(emailcheck.isEmpty()==false)
            {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(tfemail.getText()+" This email is already in use !");;
                 alert.showAndWait();
            }
        else
                 if(usernamecheck.isEmpty()==false)
            {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(tfusername.getText()+" This username is already in use !");;
                 alert.showAndWait();
            }
        else        
        { User k = new User(tfemail.getText(),tffirstname.getText()+" "+tflastname.getText(),tfusername.getText(),tfpassword.getText(),tfbirthday.getValue().toString());
        
          
          
          //photo na9sa
          if(auth.Signup(k))
          {      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Success !");
                 alert.setHeaderText(null);
                 alert.setContentText("You have Successfully created an account, you can login and use our app now !");
                 alert.showAndWait();
                 
                try {
                    root = loader.load();
                    stage = (Stage) tfsignup.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
                 
          }else
          {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(" Oops ,Something went Wrong !, Check your internet connection ! ");
                 alert.showAndWait();
          }
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/login/FXMLLogin.fxml"));

        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) tfcancel.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
