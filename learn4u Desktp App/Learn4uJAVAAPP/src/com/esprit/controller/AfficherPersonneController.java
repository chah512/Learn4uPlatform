/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AfficherPersonneController implements Initializable {

    @FXML
    private TableView<?> personsTable;
    @FXML
    private TableColumn<?, ?> NomColonne;
    @FXML
    private TableColumn<?, ?> PrenomColonne;
    @FXML
    private Label idLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Button btn_pie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
