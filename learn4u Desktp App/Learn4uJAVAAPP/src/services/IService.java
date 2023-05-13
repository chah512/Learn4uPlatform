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
public interface IService<Avis>  {
    
    public void addA(Avis t);
  
    public void updateA(Avis t);
    
      public void deleteA(Avis t);
  
    public List<Avis> afficher();

    
}
