/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.UsersList;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.UserService;
import utils.SessionManager;

/**
 * FXML Controller class
 *
 * @author MSI GF63
 */
public class FXMLUserController implements Initializable {

    @FXML
    private TextField tffirstname;
    @FXML
    private TextField tflastname;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private DatePicker tfbirthday;
    @FXML
    private ComboBox<String> tfisbanned;
    @FXML
    private ComboBox<String> tfrole;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnban;
    @FXML
    private Button btnadd;
    @FXML
    private TableView<User> tableuser;
    private TableColumn<?, ?> tcid;
    @FXML
    private TableColumn<?, ?> tcfullname;
    @FXML
    private TableColumn<?, ?> tcusername;
    @FXML
    private TableColumn<?, ?> tcemail;
    private TableColumn<?, ?> tcpassword;
    @FXML
    private TableColumn<?, ?> tcbirthday;
    @FXML
    private TableColumn<?, ?> tcisbanned;
    @FXML
    private TableColumn<?, ?> tcisverified;
    @FXML
    private TextField tfsearch;
    @FXML
    private Button btnsearch;
    @FXML
    private Button btnunban;
    private Button tfprofile;
    @FXML
    private Text tfusernumber;
    @FXML
    private Text tfonlinenumber;
    @FXML
    private Text tfbanneduser;
    @FXML
    private Button tfonlinefilter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       UserService service= new UserService();
       ObservableList<User> list = service.GetUsers();
       ObservableList<User> onlinelist = service.OnlineUsers();
       ObservableList<User> bannedlist = service.BannedUsers();
       tfrole.getItems().removeAll(tfrole.getItems());
       tfrole.getItems().add("User");
       tfrole.getItems().add("Admin");
       tfisbanned.getItems().removeAll(tfisbanned.getItems());
       tfisbanned.getItems().addAll("Yes","No");
       tfrole.getSelectionModel().selectFirst();
       tfisbanned.getSelectionModel().selectLast();
       System.out.print(list);
       refreshtable(list);
       String search=tfsearch.getText();
       System.out.print(SessionManager.getUsername());
       tfonlinenumber.setText(""+onlinelist.size());
       tfbanneduser.setText(""+bannedlist.size());
       AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
             ObservableList<User> onlinelist = service.OnlineUsers();
                    tfonlinenumber.setText(""+onlinelist.size());
                    ObservableList<User> bannedlist = service.BannedUsers();
                    tfbanneduser.setText(""+bannedlist.size());
                    ObservableList<User> list = service.GetUsers();
                    tfusernumber.setText(""+list.size());
        }
    };
    timer.start();
    }    

    @FXML
    private void UpdateActionlistener(ActionEvent event) {
        User usr = tableuser.getSelectionModel().getSelectedItem();
        if(usr==null){Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("You haven't selected any user to update!");
                 alert.showAndWait();}
        else
        { String regex = "^(.+)@(.+)$";
          Pattern email=Pattern.compile(regex);
          Matcher emailmatch=email.matcher(tfemail.getText());
          String name = "^[a-zA-Z]+";
          Pattern nameP=Pattern.compile(name);
          Matcher namematch=nameP.matcher(tffirstname.getText());
           Matcher Lnamematch=nameP.matcher(tflastname.getText());
          UserService service=new UserService();
          String Role;
          int isbanned;
        ObservableList<User> emailcheck = service.SearchUsers(tfemail.getText());
        ObservableList<User> usernamecheck = service.SearchUsers(tfusername.getText());
        if((tfemail.getText().equals(""))||(tffirstname.getText().equals(""))||(tflastname.getText().equals(""))||(tfpassword.getText().equals(""))||(tfbirthday.getValue().equals("")))
                {Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("Please fill all fields !");
                 alert.showAndWait();
                 fillblanks(usr);
                }
        else
        if(emailmatch.matches()==false)
         {
             Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(tfemail.getText()+" Is not a valid email !");
                 alert.showAndWait();
         }
        else
            if((namematch.matches()==false)||(Lnamematch.matches()==false))
            {Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("You can't have Special caracters or number in first name or last name !");
                 alert.showAndWait();}
        else
        if(tfpassword.getText().length()<6)
        {        Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("Password must be longer than 6 caracters");
                 alert.showAndWait();}
        else
            if(emailcheck.isEmpty()==false&&(tfemail.getText().equals(usr.getEmail())==false))
            {
                 Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(tfemail.getText()+" This email is already in use !");;
                 alert.showAndWait();
            }
        else
                 if(usernamecheck.isEmpty()==false&&(tfusername.getText().equals(usr.getUsername())==false))
            {
                 Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(tfusername.getText()+" This username is already in use !");;
                 alert.showAndWait();
            }
        else        
        { User k = new User(tfemail.getText(),tffirstname.getText()+" "+tflastname.getText(),tfusername.getText(),tfpassword.getText(),tfbirthday.getValue().toString());
         if(tfrole.getValue().equals("Admin"))
         { Role="a:1:{i:0;s:10:\"ROLE_ADMIN\";}";
         }else
           Role="a:1:{i:0;s:9:\"ROLE_USER\";}";
                   
         if(tfisbanned.getValue().equals("Yes"))
         { isbanned=1;
         
         }else
         {isbanned=0;}
          
          k.setIsbanned(isbanned);
          k.setRoles(Role);
          //photo na9sa
          if(service.Update(k, usr.getId()))
          {      Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Success !");
                 alert.setHeaderText(null);
                 alert.setContentText("You have Successfully updated User!");
                 alert.showAndWait();
                 ObservableList<User> list = service.GetUsers();
                 refreshtable(list);
                 clearfields();
                 
          }else
          {
                 Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(" Oops ,Something went Wrong ! ");
                 alert.showAndWait();
          }
        }}
        
    }

    @FXML
    private void DeleteActionlistener(ActionEvent event) {
        User usr = tableuser.getSelectionModel().getSelectedItem();
        if(usr==null){Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("You haven't selected any user to delete!");
                 alert.showAndWait();}
        else{
          UserService service=new UserService();
          if(service.delete(usr))
          {      Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("You have deleted User :"+usr.getUsername()+ "!");
                 alert.showAndWait();
                 refreshtable(service.GetUsers());
          }else
          {
              Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(" Oops ,Something went Wrong ! ");
                 alert.showAndWait();
          }
        }
        
        }
    

    @FXML
    private void BanActionlistener(ActionEvent event) {
        User usr = tableuser.getSelectionModel().getSelectedItem();
        if(usr==null){Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("You haven't selected any user to ban!");
                 alert.showAndWait();}
        else{
          UserService service=new UserService();
          if(service.Ban(usr))
          {      Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("You have banned User :"+usr.getUsername()+ "!");
                 alert.showAndWait();
                 refreshtable(service.GetUsers());
          }else
          {
              Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(" Oops ,Something went Wrong ! ");
                 alert.showAndWait();
          }
        }
    }

    @FXML
    private void AddActionlistener(ActionEvent event) {
         String regex = "^(.+)@(.+)$";
          Pattern email=Pattern.compile(regex);
          Matcher emailmatch=email.matcher(tfemail.getText());
          String name = "^[a-zA-Z]+";
          Pattern nameP=Pattern.compile(name);
          Matcher namematch=nameP.matcher(tffirstname.getText());
           Matcher Lnamematch=nameP.matcher(tflastname.getText());
        UserService service=new UserService();
        String Role;
        int isbanned;
        ObservableList<User> emailcheck = service.SearchUsers(tfemail.getText());
        ObservableList<User> usernamecheck = service.SearchUsers(tfusername.getText());
        if((tfemail.getText().equals(""))||(tffirstname.getText().equals(""))||(tflastname.getText().equals(""))||(tfpassword.getText().equals(""))||(tfbirthday.getValue().equals("")))
                {Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("Please fill all fields !");
                 alert.showAndWait();}
        else
        if(emailmatch.matches()==false)
         {
             Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(tfemail.getText()+" Is not a valid email !");
                 alert.showAndWait();
         }
        else
            if((namematch.matches()==false)||(Lnamematch.matches()==false))
            {Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("You can't have Special caracters or number in first name or last name !");
                 alert.showAndWait();}
        else
        if(tfpassword.getText().length()<6)
        {        Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("Password must be longer than 6 caracters");
                 alert.showAndWait();}
        else
            if(emailcheck.isEmpty()==false)
            {
                 Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(tfemail.getText()+" This email is already in use !");;
                 alert.showAndWait();
            }
        else
                 if(usernamecheck.isEmpty()==false)
            {
                 Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(tfusername.getText()+" This username is already in use !");;
                 alert.showAndWait();
            }
        else        
        { User k = new User(tfemail.getText(),tffirstname.getText()+" "+tflastname.getText(),tfusername.getText(),tfpassword.getText(),tfbirthday.getValue().toString());
         if(tfrole.getValue().equals("Admin"))
         { Role="a:1:{i:0;s:10:\"ROLE_ADMIN\";}";
         }else
           Role="a:1:{i:0;s:9:\"ROLE_USER\";}";
                   
         if(tfisbanned.getValue().equals("Yes"))
         { isbanned=1;
         
         }else
         {isbanned=0;}
          
          k.setIsbanned(isbanned);
          k.setRoles(Role);
          //photo na9sa
          if(service.Add(k))
          {      Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Success !");
                 alert.setHeaderText(null);
                 alert.setContentText("You have Successfully added new user !");
                 alert.showAndWait();
                 ObservableList<User> list = service.GetUsers();
                 refreshtable(list);
                 clearfields();
                 
          }else
          {
                 Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(" Oops ,Something went Wrong ! ");
                 alert.showAndWait();
          }
        }
        
    }
   
    @FXML
    private void SearchUsers(ActionEvent event) {
        UserService service=new UserService();
        ObservableList<User> list=service.SearchUsers(tfsearch.getText());
        refreshtable(list);
        
    }

    @FXML
    private void UnbanActionlistener(ActionEvent event) {
        User usr = tableuser.getSelectionModel().getSelectedItem();
        if(usr==null){Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("You haven't selected any user to unban!");
                 alert.showAndWait();}
        else{
          UserService service=new UserService();
          if(service.Unban(usr))
          {      Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("You have unbanned User :"+usr.getUsername()+ "!");
                 alert.showAndWait();
                 
          }else
          {
              Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText(" Oops ,Something went Wrong ! ");
                 alert.showAndWait();
          }
        }
    }
    
        public void refreshtable(ObservableList<User> list){

          
          tcfullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
          tcusername.setCellValueFactory(new PropertyValueFactory<>("Username"));
          tcemail.setCellValueFactory(new PropertyValueFactory<>("email"));
          tcbirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
          tcisbanned.setCellValueFactory(new PropertyValueFactory<>("Isbanned"));
          tcisverified.setCellValueFactory(new PropertyValueFactory<>("IsVerified"));
          tableuser.setItems(list);
          tfusernumber.setText(""+list.size());
           }
    public void clearfields(){
                 tfemail.clear();
                 tffirstname.clear();
                 tflastname.clear();
                 tfpassword.clear();
                 tfusername.clear();
    }
    public void fillblanks(User usr)
    {
           if(tfemail.getText().equals("")){
           tfemail.setText(usr.getEmail());
           }
            
                
           if(tfbirthday.getValue().equals("")){
                            tfbirthday.setValue(LocalDate.parse(usr.getBirthday()));
                        }
    }

    @FXML
    private void searchtextchange(InputMethodEvent event) {
    }

    @FXML
    private void onkeypressed(KeyEvent event) {
        UserService service=new UserService();
        
       
       ObservableList<User> searchedlist=service.SearchUsers(tfsearch.getText());
        refreshtable(searchedlist);
       
       
    }

    private void redirectProfile(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/profile/FXMLProfile.fxml"));

        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) tfprofile.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void onlinefilter(ActionEvent event) {
        UserService service=new UserService();
        ObservableList<User> list = service.OnlineUsers();
        refreshtable(list);
    }

    @FXML
    private void offlinefilter(ActionEvent event) {
        UserService service=new UserService();
        ObservableList<User> list = service.OfflineUsers();
        refreshtable(list);
    }

    

    
}
