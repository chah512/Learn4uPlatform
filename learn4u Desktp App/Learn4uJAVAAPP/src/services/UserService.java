/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author MSI GF63
 */
public class UserService {
    
    public boolean Add(User c) {
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
            pst.setString(5, c.getRoles());
            pst.setString(6, c.getBirthday());
            pst.setInt(7, c.getIsVerified());
            pst.setInt(8, c.getIsbanned());
            pst.setString(9, c.getPhoto());

          System.out.print(pst);
                  
            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("  success !");
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
    
    
    
     public boolean Update(User c,int id) {
        boolean userupdated = false;
        System.out.print("processing...");
        try {
           
            String requete = "UPDATE `learn4u`.`user` SET `email` = ?, `roles` = ?,`password` = ?,`username` = ?,`is_banned` = ?,`naissance` = ?,`fullname` = ?,`photo` = ? WHERE (`id` = ?);";
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete); 
            Argon2 argon2jvm = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 16, 32);
            String arg2pwd = argon2jvm.hash(10, 65536, 1, c.getPassword());
            pst.setString(1, c.getEmail());
            pst.setString(2, c.getRoles());
            pst.setString(3, arg2pwd);
            pst.setString(4, c.getUsername());
            pst.setInt(5, c.getIsbanned());
            pst.setString(6, c.getBirthday());
            pst.setString(7, c.getFullname());
            pst.setString(8, c.getPhoto());
            pst.setInt(9, id);

          System.out.print(pst);
                  
            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("  success !");
                userupdated = true;
            }
            else {
                System.out.println(" failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userupdated;
    }
    
    
    
    
    
    public boolean delete(User c) {
        boolean userdeleted = false;
        System.out.print("processing...");
        try {
            
            String requete = "DELETE FROM `learn4u`.`user` WHERE (`id` = ?)";
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete); 
            
           
            pst.setInt(1, c.getId());

          System.out.print(pst);
                  
            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("  success !");
                userdeleted = true;
            }
            else {
                System.out.println(" failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userdeleted;
    }
    
    
     public ObservableList<User> GetUsers() {
        ObservableList<User> myList = FXCollections.observableArrayList();
        try {
            String query = "SELECT id,fullname,username,email,password,naissance,roles,is_verified,is_banned,photo FROM user";
            Statement st = MyConnection.getInstance().getConnection().createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                User c = new User();
                c.setId(res.getInt("id"));
                c.setFullname(res.getString("fullname"));
                c.setUsername(res.getString("username"));
                c.setEmail(res.getString("email"));
                c.setBirthday(res.getString("naissance"));
                c.setIsVerified(res.getInt("is_verified"));
                c.setRoles(res.getString("roles"));
                c.setIsbanned(res.getInt("is_banned"));
                c.setPhoto(res.getString("photo"));
                c.setPassword(res.getString("password"));

                myList.add(c);
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

     
     
      public boolean Ban(User c) {
        boolean userbanned = false;
        System.out.print("processing...");
        try {
           
            String requete = "UPDATE `learn4u`.`user` SET `is_banned` = '1' WHERE (`id` = ?);";
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete); 
            
           
            pst.setInt(1, c.getId());

          System.out.print(pst);
                  
            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("  success !");
                userbanned = true;
            }
            else {
                System.out.println(" failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userbanned;
    }
      
      public boolean Unban(User c) {
        boolean userunbanned = false;
        System.out.print("processing...");
        try {
           
            String requete = "UPDATE `learn4u`.`user` SET `is_banned` = '0' WHERE (`id` = ?);";
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete); 
            
           
            pst.setInt(1, c.getId());

          System.out.print(pst);
                  
            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("  success !");
                userunbanned = true;
            }
            else {
                System.out.println(" failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userunbanned;
    }
      
      public ObservableList<User> SearchUsers(String critera) {
        ObservableList<User> myList = FXCollections.observableArrayList();
        try {
            String query = "select id,fullname,username,email,password,naissance,roles,is_verified,is_banned,photo from learn4u.user where (email LIKE '%"+critera+"%' or username LIKE '%"+critera+"%' or fullname LIKE '%"+critera+"%' or roles LIKE '%"+critera+"%');";
            Statement st = MyConnection.getInstance().getConnection().createStatement();
            ResultSet res = st.executeQuery(query);
            System.out.print(st);
            

            while (res.next()) {
                User c = new User();
                c.setId(res.getInt("id"));
                c.setFullname(res.getString("fullname"));
                c.setUsername(res.getString("username"));
                c.setEmail(res.getString("email"));
                c.setBirthday(res.getString("naissance"));
                c.setIsVerified(res.getInt("is_verified"));
                c.setRoles(res.getString("roles"));
                c.setPassword(res.getString("password"));
                c.setIsbanned(res.getInt("is_banned"));
                c.setPhoto(res.getString("photo"));

                myList.add(c);
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
      
      
      public boolean UpdatePassword( String password , int id ) {
        boolean userupdated = false;
        System.out.print("processing...");
        try {
           
            String requete = "UPDATE `learn4u`.`user` SET `password` = ? WHERE (`id` = ?);";
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete); 
            Argon2 argon2jvm = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 16, 32);
            String arg2pwd = argon2jvm.hash(10, 65536, 1, password);
            pst.setString(1, arg2pwd);
            pst.setInt(2,id);

          System.out.print(pst);
                  
            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("  success !");
                userupdated = true;
            }
            else {
                System.out.println(" failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userupdated;
    }
      
       public boolean UpdateUsername( String username , int id ) {
        boolean userupdated = false;
        System.out.print("processing...");
        try {
           
            String requete = "UPDATE `learn4u`.`user` SET `username` = ? WHERE (`id` = ?);";
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete); 
           
            
            pst.setString(1, username);
            pst.setInt(2, id);

          System.out.print(pst);
                  
            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("  success !");
                userupdated = true;
            }
            else {
                System.out.println(" failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userupdated;
    }
    
        public boolean UpdateFullname( String fullname ,int id) {
        boolean userupdated = false;
        System.out.print("processing...");
        try {
           
            String requete = "UPDATE `learn4u`.`user` SET `fullname` = ? WHERE (`id` = ?);";
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete); 
           
            
            pst.setString(1, fullname);
            pst.setInt(2, id);

          System.out.print(pst);
                  
            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("  success !");
                userupdated = true;
            }
            else {
                System.out.println(" failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userupdated;
    }
 
         public boolean UpdateBirthday( String birthday ,int id ) {
        boolean userupdated = false;
        System.out.print("processing...");
        try {
           
            String requete = "UPDATE `learn4u`.`user` SET `naissance` = ? WHERE (`id` = ?);";
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete); 
           
            
            pst.setString(1, birthday);
            pst.setInt(2, id);
            

          System.out.print(pst);
                  
            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("  success !");
                userupdated = true;
            }
            else {
                System.out.println(" failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userupdated;
    }
         
         
         
         public ObservableList<User> OnlineUsers() {
        ObservableList<User> myList = FXCollections.observableArrayList();
        try {
            String query = "SELECT id,fullname,username,email,password,naissance,roles,is_verified,is_banned,photo FROM user WHERE (IsOnline = '1')";
            Statement st = MyConnection.getInstance().getConnection().createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                User c = new User();
                c.setId(res.getInt("id"));
                c.setFullname(res.getString("fullname"));
                c.setUsername(res.getString("username"));
                c.setEmail(res.getString("email"));
                c.setBirthday(res.getString("naissance"));
                c.setIsVerified(res.getInt("is_verified"));
                c.setRoles(res.getString("roles"));
                c.setIsbanned(res.getInt("is_banned"));
                c.setPhoto(res.getString("photo"));
                c.setPassword(res.getString("password"));

                myList.add(c);
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
         
         
         public ObservableList<User> OfflineUsers() {
        ObservableList<User> myList = FXCollections.observableArrayList();
        try {
            String query = "SELECT id,fullname,username,email,password,naissance,roles,is_verified,is_banned,photo FROM user WHERE (IsOnline = '0')";
            Statement st = MyConnection.getInstance().getConnection().createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                User c = new User();
                c.setId(res.getInt("id"));
                c.setFullname(res.getString("fullname"));
                c.setUsername(res.getString("username"));
                c.setEmail(res.getString("email"));
                c.setBirthday(res.getString("naissance"));
                c.setIsVerified(res.getInt("is_verified"));
                c.setRoles(res.getString("roles"));
                c.setIsbanned(res.getInt("is_banned"));
                c.setPhoto(res.getString("photo"));
                c.setPassword(res.getString("password"));

                myList.add(c);
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
         
         public ObservableList<User> BannedUsers() {
        ObservableList<User> myList = FXCollections.observableArrayList();
        try {
            String query = "SELECT id,fullname,username,email,password,naissance,roles,is_verified,is_banned,photo FROM user WHERE (is_banned = '1')";
            Statement st = MyConnection.getInstance().getConnection().createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                User c = new User();
                c.setId(res.getInt("id"));
                c.setFullname(res.getString("fullname"));
                c.setUsername(res.getString("username"));
                c.setEmail(res.getString("email"));
                c.setBirthday(res.getString("naissance"));
                c.setIsVerified(res.getInt("is_verified"));
                c.setRoles(res.getString("roles"));
                c.setIsbanned(res.getInt("is_banned"));
                c.setPhoto(res.getString("photo"));
                c.setPassword(res.getString("password"));

                myList.add(c);
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

     
     
 
       
    
}
