/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.Preferences;
import com.codename1.ui.Image;

/**
 *
 * @author MSI GF63
 */
public class SessionManager {
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String username ; 
    private static String email; 
    private static String passowrd ;
    private static String photo;
    private static String fullname;
    private static String roles;
    private static String birthday;
    
   
    
    

    public static Preferences getPref() {
        return pref;
    }
    

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    

     public static String getBirthday() {
        return pref.get("birthday",birthday);
    }

    public static void setBirthday(String birthday) {
        pref.set("birthday",birthday);
    }
    
    
    public static String getFullname() {
        return pref.get("fullname",fullname);
    }

    public static void setFullname(String fullname) {
        pref.set("fullname",fullname);
    }


    
    public static int getId() {
        return pref.get("id",id);
    }

    public static void setId(int id) {
        pref.set("id",id);
    }

    public static String getUserName() {
        return pref.get("username",username);
    }

    public static void setUserName(String userName) {
         pref.set("username",userName);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }

    public static String getPhoto() {
        return pref.get("photo",photo);
    }

    public static void setPhoto(String photo) {
         pref.set("photo",photo);
    }

    public static String getRoles() {
       return pref.get("roles",roles);
    }

    public static void setRoles(String roles) {
        pref.set("roles",roles);
    }
    
    
}
