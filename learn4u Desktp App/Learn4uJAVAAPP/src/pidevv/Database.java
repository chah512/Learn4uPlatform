/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import service.formationcrud;

/**
 *
 * @author asus
 */
public class Database {
      private static Database instance;
    private Connection cnx;
    String url = "jdbc:mysql://localhost:3306/learn4u";
    
     public Database() {
        System.out.println("Instantiation de CNX");
        try {
            cnx = DriverManager.getConnection(url, "root", "");
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.err.println("Connexion impossible");
            System.err.println(ex);
        }
    }
     
      public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return cnx;
    }



	


    
  
}

