/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import entities.User;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;
import utils.SessionManager;

/**
 *
 * @author MSI GF63
 */
public class AuthService {
     public boolean Signup(User c) {
        boolean usercreated = false;
        System.out.print("processing...");
        try {
            String query = "create table IF NOT EXISTS user (id int auto_increment key,"
                    + "email varchar(180) , "
                    + "fullname varchar(255) , "
                    + "username varchar(255) , "
                    + "password varchar(255),"
                    + "roles longtext,"
                    + "naissance Date,"
                    + "is_banned tinyint(1) ,"
                    + "is_verified tinyint(1) ,"
                    + " UNIQUE (email) )";
            Statement st = MyConnection.getInstance().getConnection().createStatement();

            st.execute(query);
            String requete = "INSERT INTO user (email,fullname,username,password,roles,naissance,is_verified,is_banned,photo) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete); 
            Argon2 argon2jvm = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 16, 32);
            String arg2pwd = argon2jvm.hash(10, 65536, 1, c.getPassword());
            pst.setString(1, c.getEmail());
            pst.setString(2, c.getFullname());
            pst.setString(3, c.getUsername());
            pst.setString(4, arg2pwd);
            pst.setString(5, "a:1:{i:0;s:9:\"ROLE_USER\";}");
            pst.setString(6, c.getBirthday());
            pst.setInt(7, 0);
            pst.setInt(8, 0);
            pst.setString(9, c.getPhoto());

          System.out.print(pst);
                  
            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("  Signed !");
                usercreated = true;
            }
            else {
                System.out.println(" failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return usercreated;
    }
     
     
     
     public boolean Login(String email,String password) {
        
        try {
            String query = "SELECT id,fullname,username,email,password,naissance,roles,is_verified,is_banned,photo FROM user WHERE (email = ? )";
           
            
             Argon2 argon2jvm = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 16, 32);
             
            
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(query); 
            pst.setString(1, email);
 
            System.out.print(pst);
            ResultSet res = pst.executeQuery();
            
           
            if (res.next()) {
                
               
                if(argon2jvm.verify(res.getString("password"), password))
                {   
                    
                    
                    String queryLogin="UPDATE `learn4u`.`user` SET `IsOnline` = '1' WHERE (email = ?)";
                    PreparedStatement pstLogin = MyConnection.getInstance().getConnection().prepareStatement(queryLogin); 
                    pstLogin.setString(1, email);
                    int ok=pstLogin.executeUpdate();
                    if(ok != -1)
                    {SessionManager.getInstace(res.getString("username"),res.getInt("id"),res.getString("email"),res.getString("naissance"),res.getString("fullname"),res.getInt("is_banned"),res.getString("roles"));
                     return true;
                    }
                }
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
     public boolean ResetPwd(String email, String pwd) {
        boolean userexiste = false;

        try {
            String query = "update user set password=? where email=?";

            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(query);
             Argon2 argon2jvm = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 16, 32);
            String arg2pwd = argon2jvm.hash(10, 65536, 1, pwd);
            pst.setString(1, arg2pwd);
            pst.setString(2, email);

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("Password user successfully updated ! ");
                userexiste = true;
            }
            else {
                System.out.println("something went wrong");
            }

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userexiste;
    }
     
     public boolean Logout(String email) 
     {
         try {
             String queryLogin="UPDATE `learn4u`.`user` SET `IsOnline` = '0' WHERE (email = ?)";
             PreparedStatement pstLogin = MyConnection.getInstance().getConnection().prepareStatement(queryLogin);
             pstLogin.setString(1, email);
             int ok=pstLogin.executeUpdate();
             if(ok != -1)
             { return true;
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(AuthService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return false;
     }
}
