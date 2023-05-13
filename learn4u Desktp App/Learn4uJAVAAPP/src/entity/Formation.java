/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Ahmed
 */
public class Formation {
     private int id;
    private String nom;
    private int prix;
    private Date start_date;
    private Date end_date;
    private String description;
  
    private String image;

    public Formation(int id, String nom, int prix, Date start_date, Date end_date, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.image = image;
    }

    public Formation(int id, String nom,  String description) {
        this.id = id;
        this.nom = nom;
         this.description = description;
         //To change body of generated methods, choose Tools | Templates.
    }

    public Formation(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Formation(String nom, String description, String image, int prix,Date start_date,Date end_date) {
       this.nom = nom;
         this.description = description;
          this.image = image;
           this.prix = prix;
           this.start_date = start_date;
             this.end_date = end_date;
           
 //To change body of generated methods, choose Tools | Templates.
    }
     public Formation(String nom,int prix , String description) {
       this.nom = nom;
         this.description = description;
        
           this.prix = prix;
 //To change body of generated methods, choose Tools | Templates.
    }

    public Formation(int id,String nom, int prix, String description, String image,  Date start_date) {
        
        
         this.nom = nom;
         this.description = description;
          this.image = image;
           this.prix = prix; 
           this.start_date = start_date;//To change body of generated methods, choose Tools | Templates.
    }

    public Formation(int id, String nom, String description, String image, int prix) {
        this.id = id;
        this.nom = nom;
         this.description = description;
          this.image = image;
           this.prix = prix;
    }

    public Formation(int id) {
         this.id = id; //To change body of generated methods, choose Tools | Templates.
    }

    public Formation(int id, String nom, String description, String image) {
       this.id = id;
        this.nom = nom;
         this.description = description;
          this.image = image;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Formation() {
    }

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", datede=" + start_date + ", datefi=" + end_date + ", description=" + description + ", image=" + image + '}';
    }
    
    
    

    
}
