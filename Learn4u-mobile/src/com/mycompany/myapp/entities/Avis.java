/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.entities;
import java.util.Date;
/**
 *
 * @author med
 */
public class Avis {
    private int id;
    private String rating;
    private String title;
    private String category;
    private String content;


    
    public Avis() {
    }

    public Avis(String rating, String title, String category, String content) {
       
        this.rating = rating;
        this.title = title;
        this.category = category;
        this.content = content;
       
    
    }

    public Avis(int id, String rating, String title, String category, String content) {
        this.id = id;
        this.rating = rating;
        this.title = title;
        this.category = category;
        this.content = content;
     
       
    }


    public int getId() {
        return id;
    }
    
    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

 @Override
    public String toString() {
        return "avis{" + "id=" + id + ", rating=" + rating + ", title=" + title + ", category=" + category + ", content=" + content + '}';
    }

}
