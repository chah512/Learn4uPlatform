/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Formation;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import static controller.AfficherPersonneController.article;
import java.io.IOException;
import java.sql.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ItemBController implements Initializable {

    @FXML
    private Pane pnItem;
    @FXML
    private ImageView imNft;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblCreationDate;
    @FXML
    private Label lblCategory;
    @FXML
    private Label lblCurrency;
    private Label ida;
    private Button blogd;
    @FXML
    private Label aimg;
    private String id;  
    public static Formation r ;
    public static Formation thisArticle;
     public static Formation Posta;
    public static String ayja;
    @FXML
    private Button xx;
  

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTitle.setText(article.getNom());
        lblCategory.setText(article.getDescription());
        lblCreationDate.setText(article.getStart_date()+"");
        aimg.setText(article.getImage());
       // ida.setText(String.valueOf(article.getId()));
        id=String.valueOf(article.getId());
       // lblOwner.setText(r.getAuthor());
        ayja=article.getImage();
        thisArticle = article;
        Posta=r;
        System.out.println(ayja);
        //Integer.valueOf(id)
        thisArticle.setId(Integer.valueOf(id));
//        Posta.setId(Integer.valueOf(id));
        //lblCurrency.setText(r.getContents());
        try {
            FileInputStream inputstream = new FileInputStream("C:\\xampp\\htdocs\\FirstProject-main\\public\\uploads\\"+ayja);
            Image image = new Image(inputstream);
            imNft.setImage(image);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public void blogdetail(ActionEvent actionEvent) throws IOException {
        Posta=thisArticle;
       Posta.setId(Integer.valueOf(id));
        Posta.setNom(lblTitle.getText());
        Posta.setDescription(lblCurrency.getText());
        Posta.setPrix(Integer.parseInt(lblCategory.getText()));
        //Posta.setAuthor(lblOwner.getText());
        Posta.setStart_date(Date.valueOf(lblCreationDate.getText()));
        Posta.setImage(aimg.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemB.fxml"));
        Parent root2 = loader.load();


        blogd.getScene().setRoot(root2);
    }

    @FXML
    private void xxx(ActionEvent event) throws IOException {
        Posta=thisArticle;
       Posta.setId(Integer.valueOf(id));
        Posta.setNom(lblTitle.getText());
        Posta.setDescription(lblCurrency.getText());
//        Posta.setPrix(Integer.parseInt(lblCategory.getText()));
        //Posta.setAuthor(lblOwner.getText());
//        Posta.setStart_date(Date.valueOf(lblCreationDate.getText()));
        Posta.setImage(aimg.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemB.fxml"));
        Parent root2 = loader.load();


        xx.getScene().setRoot(root2);
    }
}
    

