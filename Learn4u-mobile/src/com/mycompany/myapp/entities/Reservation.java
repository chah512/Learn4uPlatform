/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author MSI GF63
 */
public class Reservation {
    private String id,duree,offre;

    public Reservation() {
    }

    public Reservation(String duree) {
        this.duree = duree;
    }

    
    
    public Reservation(String duree, String offre) {
        this.duree = duree;
        this.offre = offre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getOffre() {
        return offre;
    }

    public void setOffre(String offre) {
        this.offre = offre;
    }
    
    
    
}
