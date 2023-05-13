/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author MSI GF63
 */

    public class Event {

    private int id;
    private String titre;
    private String description;
    private String prix;
    private String image;
    private Date date;

    public Event(String titre, String description, String prix) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
    }

    
    public Event(int id, String titre, String description, String prix, String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.image = image;
    }

    public Event(String titre, String description, String prix, String image) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
       
    
    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", prix=" + prix + ", image=" + image + '}';
    }
    

}
