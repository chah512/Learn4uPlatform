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
public class Category {
    
    private int id;
    private String name;
    private String content;
   

    public Category(String name, String content) {
        this.name = name;
        this.content = content;
        
    }
    
    
    public Category(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    
    public Category(int id) {
        this.id = id;
    }
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
    
    
}
