/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.CoursService;
import entity.Cours;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



/**
 *
 * @author wiemhjiri
 */
public class ListData {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Cours> persons=FXCollections.observableArrayList();

    public ListData() {
        
        CoursService pdao=CoursService.getInstance();
        persons= (ObservableList<Cours>) pdao.readAll();
        System.out.println(persons);
    }
    
    public ObservableList<Cours> getPersons(){
        return persons;
    }
   
}
