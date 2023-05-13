/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevv;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Ahmed
 */
public class Pidevv extends Application {
      private Stage primaryStage;
    private Parent parentPage;
    private Stage primarStage;
    
   
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hello World");
        
        parentPage = FXMLLoader.load(getClass().getResource("/view/consulterEvent.fxml"));
        Scene scene = new Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
      public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        primarStage.getScene().setRoot(pane);
    
    }
      
      
      
    

    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
   
   
    }
    
    
    

