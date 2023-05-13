/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.profile;

import entities.User;
import java.awt.Dialog;
import java.awt.Dialog.ModalityType;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.UserService;
import utils.SessionManager;

/**
 * FXML Controller class
 *
 * @author MSI GF63
 */
public class FXMLProfileController implements Initializable {

    @FXML
    private Text tfemail;
    @FXML
    private TextField tfusername;
    private Text tffullname;
    @FXML
    private DatePicker tfbirthday;
    @FXML
    private TextField tffirstname;
    @FXML
    private TextField tflastname;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private PasswordField tfverifypassword;
    @FXML
    private Button btnsave;
    private Button btnback;
    @FXML
    private Text tfusernameview;
    @FXML
    private Text tfemailview;
    @FXML
    private Text tffullnameview;
    @FXML
    private Text tfbirthdayview;
    @FXML
    private Text tfroleview;
    @FXML
    private Button tfuploadimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfusernameview.setText(SessionManager.getUsername());
        tffullnameview.setText(SessionManager.getFullname());
        tfbirthdayview.setText(SessionManager.getBirthday());
        tfemailview.setText(SessionManager.getEmail());
        if(SessionManager.getrole().contains("ROLE_ADMIN"))
        {tfroleview.setText("Admin");
        
        }else
        {tfroleview.setText("User");
        }
    }    

    @FXML
    private void Save(ActionEvent event) {
                         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/profile/FXMLProfile.fxml"));
                                  Stage stage;
                         stage = (Stage) btnsave.getScene().getWindow();
                         Parent root;
        UserService service= new UserService();
        int id = SessionManager.getId();
        String regex = "^(.+)@(.+)$";
          Pattern email=Pattern.compile(regex);
          Matcher emailmatch=email.matcher(tfemail.getText());
          String name = "^[a-zA-Z]*";
          Pattern nameP=Pattern.compile(name);
          Matcher namematch=nameP.matcher(tffirstname.getText());
           Matcher Lnamematch=nameP.matcher(tflastname.getText());
           ObservableList<User> usernamecheck = service.SearchUsers(tfusername.getText());
           
           if((tfusername.getText().equals("")))
                {tfusername.setText(SessionManager.getUsername());}
           if((tffirstname.getText().equals("")))
                {tffirstname.setText(SessionManager.getFullname().substring(0, SessionManager.getFullname().indexOf(" ")));}
           if((tflastname.getText().equals("")))
                {  String[] parts = SessionManager.getFullname().split(" ");
                     String first = parts[0];//"hello"
                    String second = parts[1];//"World"
                    tflastname.setText(second);
                }
           if(tfbirthday.getValue().toString().equals(""))
           { tfbirthday.setValue(LocalDate.parse(SessionManager.getBirthday()));
           }
        
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
            
                 if(usernamecheck.isEmpty()==false&&(tfusername.getText().equals(SessionManager.getUsername())==false))
            {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(tfusername.getText()+" This username is already in use !");;
                 alert.showAndWait();
            }else
                     if(service.UpdateBirthday(tfbirthday.getValue().toString(), id)&&service.UpdateFullname(tffirstname.getText()+" "+tflastname.getText(), id)&&service.UpdateUsername(tfusername.getText(), id)&&service.UpdatePassword(tfpassword.getText(), id))
                     { Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Success");
                 alert.setHeaderText(null);
                 alert.setContentText("You have successfully updated your account !");;
                 alert.showAndWait();
                 
                   tfusernameview.setText(tfusername.getText());
                    tffullnameview.setText(tffullname.getText());
                   tfbirthdayview.setText(tfbirthday.getValue().toString());
                    tfemailview.setText(tfemail.getText());
        
                     }
    
    
    }

    @FXML
    private void Upload(ActionEvent event) {
    }

    

   
    
}
