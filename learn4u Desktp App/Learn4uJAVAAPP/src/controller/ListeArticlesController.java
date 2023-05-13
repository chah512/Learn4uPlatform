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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import service.formationcrud;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ListeArticlesController implements Initializable {

    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private ScrollPane scrollPaneItems;
    @FXML
    private HBox boxItems;
    @FXML
    private Button btnAddBA;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*formationcrud Service = new formationcrud();
        List<Formation> articles = formationcrud.listerArticles();
        Node[] nodes = new Node[articles.size()];
        for (int i = 0; i < nodes.length; i++) {
     //       ida= (articles.get(i)).getId();
            try {
                final int j = i;
                article = articles.get(i);
                nodes[i] = FXMLLoader.load(getClass().getResource("ItemB.fxml"));
                //give the items some effect
                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });

                int finalI = i;
                nodes[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        thisArticle = articles.get(finalI);
                    }
                });

                boxItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        // TODO
    }    

    @FXML
    private void ajout(ActionEvent event) {
    }

    @FXML
    private void backhome(ActionEvent event) {
    }
    
}
