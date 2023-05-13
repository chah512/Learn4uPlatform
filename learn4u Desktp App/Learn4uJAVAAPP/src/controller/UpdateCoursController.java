/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cours;
import entity.Formation;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import service.CoursService;
import service.formationcrud;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class UpdateCoursController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private DatePicker date_depart;
    @FXML
    private Button update;
    @FXML
    private Button consulter;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;
    @FXML
    private AnchorPane slider;
    @FXML
    private TextField descrip;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         nom.setText(ConsulterCoursController.nom_recup);
        descrip.setText(ConsulterCoursController.decriptionrecup);
       // date_depart.setText(ConsulterCoursController.date_deprecup);
       // date_depart.setValue(ConsulterCoursController.date_deprecup.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        
        //ref.setText(String.valueOf(ConsulterEventController.id_rec));
        System.out.println(ConsulterEventController.date_deprecup);
        // TODO
    }    

    @FXML
    private void update_event(ActionEvent event) throws SQLException {
        Cours prom = new Cours();
         //prom.setId(parseInt(ref.getText()));
        prom.setNom(nom.getText());
        prom.setDescription(descrip.getText());
        

        System.out.println("hahahah");
       CoursService pr=CoursService.getInstance();
            pr.modifierFormationPST(prom); 
        showMessageDialog(null, "update with succese");
    }

    @FXML
    private void consulter_event(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterCours.fxml"));
            Stage stage = (Stage) update.getScene().getWindow();
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
    private void abon(MouseEvent event) {
    }

    @FXML
    private void stat(MouseEvent event) {
    }

    @FXML
    private void promo(MouseEvent event) {
    }
    
}
