/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MSI GF63
 */
public class MyConnection {
    //Design Patter - Singleton

    public String url = "jdbc:mysql://localhost:3306/";
    public String login = "root";
    public String pwd = "";
    public Connection con;
    public static MyConnection instance;

    private MyConnection() {

        try {
            con = DriverManager.getConnection(url, login, pwd);
            Statement smt = con.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS learn4u";
            smt.executeUpdate(sql);
            System.out.println("Database created or already exists ! ");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn4u", login, pwd);
            System.out.println(" Connected ! ");
        }
        catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
//        finally {
//            cnx.close();
//        }
    }

    public Connection getConnection() {
        return con;
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }

}
