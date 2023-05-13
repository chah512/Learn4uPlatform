/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Formation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.formationcrud;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AfficherPersonneController implements Initializable {

    @FXML
    private Label admin;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;
    @FXML
    private AnchorPane slider;
    @FXML
    private HBox ooooh;
    @FXML
    private Button smsbut;
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
     public static Formation thisArticle;
     public static Formation article= null;
     public static String nom_recuppp;
    public static String decriptionrecuppp;
    public static String imgrecuppp;
    public static Date date_deprecuppp;
    @FXML
    private ImageView vv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formationcrud Service = new formationcrud();
        List<Formation> articles = formationcrud.getInstance().readAll();
        Node[] nodes = new Node[articles.size()];
        for (int i = 0; i < nodes.length; i++) {
     //       ida= (articles.get(i)).getId();
            try {
                final int j = i;
                article = articles.get(i);
                nodes[i] = FXMLLoader.load(getClass().getResource("/view/ItemB.fxml"));
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
        }

    }

    @FXML
    private void deco(MouseEvent event) {
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

    @FXML
    private void sendd(ActionEvent event) {
    }

    @FXML
    private void oooohOut(MouseEvent event) {
    }

    @FXML
    private void oooohIn(MouseEvent event) {
    }

    @FXML
    private void reclam(MouseEvent event) {
    }

    @FXML
    private void demande(MouseEvent event) {
    }

    @FXML
    private void vvv(MouseEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterEvent.fxml"));
            Stage stage = (Stage) vv.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    
}
