/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashSet;
import java.util.Set;
import java.util.prefs.Preferences;



/**
 *
 * @author MSI GF63
 */
public final class SessionManager {
 
    private static SessionManager instance;
 
    private static String username,fullname,email,birthday;
    private static int id;
    private static int isbanned;
    private static String role;
 
    private SessionManager(String userName, int id,String email,String birthday,String fullname,int isbanned, String privileges) {
        SessionManager.username = userName;
        SessionManager.id = id;
        SessionManager.birthday=birthday;
        SessionManager.email=email;
        SessionManager.fullname=fullname;
        SessionManager.isbanned=isbanned;
        SessionManager.role = privileges;
    }
 
    /**
    *
    * @param userName
    * @param employeeId
    * @param privileges
    * @return
    */
    public static SessionManager getInstace(String userName, int id,String email,String birthday,String fullname,int isbanned, String privileges) {
        if(instance == null) {
            instance = new SessionManager( userName, id, email, birthday,fullname , isbanned,  privileges);
        }
        return instance;
    }

    public static String getFullname() {
        return fullname;
    }

    public static String getEmail() {
        return email;
    }

    public static String getBirthday() {
        return birthday;
    }

    public static int getIsbanned() {
        return isbanned;
    }

    public static String getRole() {
        return role;
    }
    
 
    public static  String getUsername() {
        return username;
    }
         
    public static int getId() {
        return id;
    }
         
    public static String getrole() {
        return role;
    }

    public static void setFullname(String fullname) {
        SessionManager.fullname = fullname;
    }

    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    public static void setBirthday(String birthday) {
        SessionManager.birthday = birthday;
    }

    public static void setId(int id) {
        SessionManager.id = id;
    }

    public static void setIsbanned(int isbanned) {
        SessionManager.isbanned = isbanned;
    }
    
    
    public static void cleanUserSession() {
       username ="";
        id = 0;
        birthday="";
        email="";
       fullname="";
        isbanned=0;
       role = "";
    }
 
    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + username + '\'' +
                "email='" + email + '\'' +
                "fullname='" + fullname + '\'' +
                "birthday='" + birthday + '\'' +
                "id = '" + id + '\'' +
                "isbanned='" + isbanned + '\'' +
                ", privileges=" + role +
                
            '}';
    }
 
    
    static class cleanUserSession {
 
        public cleanUserSession() {
           username ="";
        id = 0;
        birthday="";
        email="";
       fullname="";
        isbanned=0;
       role = "";
        }
    }
}