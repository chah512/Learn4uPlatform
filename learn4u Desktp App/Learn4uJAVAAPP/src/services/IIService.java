/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author med
 */

public interface IIService<Category>  {
    
    public void addC(Category t);
  
    public void updateC(Category t);
    
      public void deleteC(Category t);
  
    public List<Category> showC();

    
}