/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.formationcrud;
import entity.Formation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Houssem
 */
public class ListData3 {
    
     
    private ObservableList<Formation> events=FXCollections.observableArrayList();

    public ListData3() {
        
        formationcrud udao=formationcrud.getInstance();
        events= (ObservableList<Formation>) udao.readAll();
        
    }
    
    public ObservableList<Formation> getevents(){
        return events;
    }
    
}
