/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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

    
    public Avis(int id) {
        this.id = id;
    }
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String start) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
    
}
