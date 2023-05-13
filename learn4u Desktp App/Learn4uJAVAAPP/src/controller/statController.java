/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Formation;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.formationcrud;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class statController implements Initializable {

    @FXML
    private AnchorPane slider;
    @FXML
    private Label dddd;
    @FXML
    private Label ff;
    @FXML
    private Label st;
    @FXML
    private Button ccours;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;
    @FXML
    private PieChart pieChart;
    @FXML
    private Button sum1;
    @FXML
    private Label hh1;
     ObservableList<PieChart.Data> list=FXCollections.
            observableArrayList();
    @FXML
    private ImageView fd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         formationcrud pdao=formationcrud.getInstance();
        List<Formation> pers=pdao.readAll();
        for(Formation p:pers) {
            list.addAll(
                new PieChart.Data(p.getNom(), 12.0)             
        );
        }
        pieChart.setAnimated(true);
        pieChart.setData(list);
        formationcrud ec = new formationcrud();
        String s = ec.countFormation();
        hh1.setText(s);
        // TODO
    } 

    @FXML
    private void ddd(MouseEvent event) {
    }

    @FXML
    private void form(MouseEvent event) {
    }

    @FXML
    private void fff(MouseEvent event) {
    }

    @FXML
    private void promo(MouseEvent event) {
    }

    @FXML
    private void abon(MouseEvent event) {
    }

    @FXML
    private void stt(MouseEvent event) {
    }

    @FXML
    private void stat(MouseEvent event) {
    }

    @FXML
    private void cours(ActionEvent event) {
    }

    @FXML
    private void summ(ActionEvent event) {
    }

    @FXML
    private void fddaceuil(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterEvent.fxml"));
            Stage stage = (Stage) fd.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
