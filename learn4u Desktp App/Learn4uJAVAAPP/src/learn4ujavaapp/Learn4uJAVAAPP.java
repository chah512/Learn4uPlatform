/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn4ujavaapp;

import entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.UserService;
import GUI.UsersList.FXMLUserController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import services.AuthService;
import utils.SessionManager;
/**
 *
 * @author MSI GF63
 */
public class Learn4uJAVAAPP extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       
            
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/login/FXMLLogin.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    @Override
    public void handle(WindowEvent t) {
        AuthService service=new AuthService();
        service.Logout(SessionManager.getEmail());
        Platform.exit();
        System.exit(0);
    }
});
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       
                
        
    
    }
    
}
