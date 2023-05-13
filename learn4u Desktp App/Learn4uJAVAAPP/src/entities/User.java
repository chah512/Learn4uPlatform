/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MSI GF63
 */
public class User {
    
    private int id;
    private String email,fullname,Username,password;
    private  String roles="[ROLE_USER]";
    private String birthday;
    private int isVerified=0,Isbanned=0;
    private String photo;

    public User(String email, String fullname, String Username, String password,String birthday) {
        this.email = email;
        this.fullname = fullname;
        this.Username = Username;
        this.birthday = birthday;
        this.password = password;
    }

    public User(String email, String fullname, String Username, String password, String birthday, String photo) {
        this.email = email;
        this.fullname = fullname;
        this.Username = Username;
        this.password = password;
        this.birthday = birthday;
        this.photo = photo;
    }

    public User() {
        
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public int getIsbanned() {
        return Isbanned;
    }

    public void setIsbanned(int Isbanned) {
        this.Isbanned = Isbanned;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
}
