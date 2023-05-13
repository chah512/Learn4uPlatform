/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cours;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.CoursService;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class CoursDetailsController implements Initializable {

    @FXML
    private ImageView imgCar;
    @FXML
    private Label CarName;
    @FXML
    private Button btnBack;
    @FXML
    private VBox ListLayout;
    @FXML
    private Label tfCarNumber;
    @FXML
    private Label Ldescription;
    @FXML
    private Label Lbrand;
    @FXML
    private Button btnDeleteCar;
    @FXML
    private Label label;
    @FXML
    private Button btnUpdate;
    public static Cours coursPassed;
    public static String CarNamePassed;
    public static String CarNumberPassed;
    public static String CarImagePassed;
    public static String CarBrandPassed;
    public static String CarDescriptionPassed;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println(coursPassed);
        //CarName.setText(coursPassed.getNom());
//        Ldescription.setText("Description : " + coursPassed.getDescription());
        
        try {
            CoursService carServiceImp = CoursService.getInstance();
            String path = carServiceImp.getCarById(coursPassed.getId()).getNom();
            if (path != null) {
                InputStream stream = new FileInputStream(path);
                Image image = new Image(stream);
                imgCar.setImage(image);
            } else {
                System.out.println("no image");
            }
        } catch (FileNotFoundException e) {
            System.out.println("no changes ");
        }

        btnUpdate.setOnAction(event -> {
            try {
                CoursService carServiceImp = new CoursService();
                
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CoursDetails.fxml")));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
                stage.setOnHidden(event1 -> {
                    System.out.println(CarNamePassed);
                    if (CarNamePassed != null) {
                        CarName.setText(CarNamePassed);
                    }
                    if (CarNumberPassed != null) {
                        tfCarNumber.setText(CarNumberPassed);
                    }
                    if (CarImagePassed != null) {
                        InputStream stream = null;
                        try {
                            stream = new FileInputStream(CarImagePassed);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Image image = new Image(Objects.requireNonNull(stream));
                        imgCar.setImage(image);
                    }
                    if (CarBrandPassed != null) {
                        Lbrand.setText("Brand : " + CarBrandPassed);
                    }
                    if (CarDescriptionPassed != null) {
                        Ldescription.setText("Description : " + CarDescriptionPassed);
                    }
                });

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnDeleteCar.setOnAction(event -> {
            CoursService carServiceImp = CoursService.getInstance();
            carServiceImp.Delete_Car(coursPassed.getNom());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/CarSample.fxml"));
            try {

                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CoursDetails.fxml")));
                Scene scene = new Scene(parent);
                Stage stage;
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        btnBack.setOnAction(event -> {
            try {
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CoursDetails.fxml")));
                Scene scene = new Scene(parent);
                Stage stage;
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying agencies");
            }
        });
    }
    
}
