/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.Date;

/**
 *
 * @author Ahmed
 */
public class Cours {
     private int id;
     private int idFormation;
    private String nom;
    private String description;
    private String video;
    private Date dteAjout;
    public Formation formation;
    
    public Cours(int id, String nom, String description, String video) {
         this.id = id;
        this.nom = nom;
        this.description = description;
        this.video = video; //To change body of generated methods, choose Tools | Templates.
    }

    public Cours(String video, String nom, String description) {
         this.video = video;
        this.nom = nom;
        this.description = description;
         //To change body of generated methods, choose Tools | Templates.
    }
     public Cours(int id, String nom, String description) {
         this.id = id;
        this.nom = nom;
        this.description = description;
         //To change body of generated methods, choose Tools | Templates.
    }

    public Cours(int id) {
        this.id = id; //To change body of generated methods, choose Tools | Templates.
    }

    public Cours( String nom, String description, String video,int id) {
       this.id = id;
        this.nom = nom;
        this.description = description;
        this.video = video; //To change body of generated methods, choose Tools | Templates.
    }
    
    public Cours( String nom, String description, String video, Formation formation) {
        this.nom = nom;
        this.description = description;
        this.video = video; //To change body of generated methods, choose Tools | Templates.
        this.formation = formation;
    }

    public Cours(int id,String nom, String description, int idFormation) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.idFormation = idFormation;
           //To change body of generated methods, choose Tools | Templates.
    }

    public Cours(int id, String nom, String description, String video, Formation formation) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.video = video ;
        this.formation = formation;
    }

    public Cours(String nom, String description, java.sql.Date dteAjout, int idFormation) {
        this.nom = nom;
        this.description = description;
        this.dteAjout = dteAjout;
        this.idFormation = idFormation;
        
//To change body of generated methods, choose Tools | Templates.
    }

    public Cours(String nom, String description, int idFormation) {
          this.nom = nom;
        this.description = description;
         
        this.idFormation = idFormation;
   
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Date getDteAjout() {
        return dteAjout;
    }

    public void setDteAjout(Date dteAjout) {
        this.dteAjout = dteAjout;
    }

    public Cours( String nom, String description, String video, Date dteAjout) {
        
        this.nom = nom;
        this.description = description;
        this.video = video;
        this.dteAjout = dteAjout;
    }

    public Cours() {
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    @Override
    public String toString() {
        return "Cours{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", video=" + video + ", dateajoutt=" + dteAjout + ", formations_id=" + idFormation + '}';
    }

    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

   

    
    
    
    
    
}
