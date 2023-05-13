/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.codename1.ui.Image;
import java.util.Date;

/**
 *
 * @author MSI GF63
 */
public class User {
    private int id;
    private String email,fullname,Username,password,Isbanned;
    private  String roles;
    private Date birthday;
    private String isVerified;
    private String photo;

    public User(String email, String fullname, String Username, Date birthday,String password) {
        this.email = email;
        this.fullname = fullname;
        this.Username = Username;
        this.birthday = birthday;
        this.password = password;
    }

    public User(String email, String fullname, String Username, String password, Date birthday, String photo) {
        this.email = email;
        this.fullname = fullname;
        this.Username = Username;
        this.password = password;
        this.birthday = birthday;
        this.photo = photo;
    }

    

    public User() {
    }

    public String getRoles() {
        return roles;
    }
   
    public void setRoles(String roles){
       this.roles=roles;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsbanned() {
        return Isbanned;
    }

    public void setIsbanned(String Isbanned) {
        this.Isbanned = Isbanned;
    }
    
    
    
    
    
    
            
}
