/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cours;
import static java.lang.ProcessBuilder.Redirect.to;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ChartGraphiqueController implements Initializable {

    @FXML
    private BarChart<String, Integer> chart;
    @FXML
    private CategoryAxis xaxis;
     private ObservableList<String> names = FXCollections.observableArrayList();
     private ListData listData = new ListData();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      /*  names = FXCollections
                .observableArrayList(listData.getPersons().stream()
                        .map(p -> p.getNom()).collect(Collectors.toList()));
    xaxis.setCategories(names);
    
    
    }
    public void setXYData(List<Cours> persons) {
        
       
        List<String> distinctNames=persons.stream()
                .map(p->p.getNom())
                .distinct()
                .collect(Collectors.toList());
        
       
        
        int[] nameCounter =new int[distinctNames.size()];
        
        
        for (Cours p : persons) {
          String name = p.getNom();
             nameCounter[]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

         Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }

        barChart.getData().add(series);*/
    }
        // TODO
    }    
    

