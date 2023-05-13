/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author solta
 */
public interface IService<Evenement>  {
    
    public void ajouter(Evenement t);
    public void modifier(Evenement t);
    public void supprimer(Evenement t);
    public List<Evenement> afficher();
    public List<Evenement> recherche(Evenement t);
    public void modifierR(Evenement t);

    
}
