/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cours;
import entity.Formation;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import service.CoursService;
import service.formationcrud;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class PieChartViewController implements Initializable {

    @FXML
    private PieChart pieChart;
    ObservableList<PieChart.Data> list=FXCollections.
            observableArrayList();
    @FXML
    private Button sum;
    @FXML
    private Label hh;

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
        hh.setText(s);
        // TODO
    }    

    @FXML
    private void summ(ActionEvent event) {
        formationcrud ec = new formationcrud();
        String s = ec.countFormation();
        hh.setText(s);
    }
}    
    

