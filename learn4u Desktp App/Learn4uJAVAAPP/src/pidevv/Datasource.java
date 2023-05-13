/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pidevv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author remo
 */
public class Datasource {
    
  // JDBC driver name and database URL
static final String jdbcDriver = "com.mysql.jdbc.Driver";
static final String databaseUrl = "jdbc:mysql://localhost:3306/learn4u" ;
static final String username="root";
static final String password = "";
private Connection cnx;
    private static Datasource instance;


    private Datasource() {
        try {
            this.cnx = DriverManager.getConnection(databaseUrl, username, password);
            System.out.println("Database connected");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public static Datasource getInstance() {
        if(instance == null){
            instance = new Datasource();
        }
        return instance;
    }

    public Connection getConnection() {
        return cnx; //To change body of generated methods, choose Tools | Templates.
    }
      
}
