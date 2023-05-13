/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevv;

import entity.Formation;
import java.sql.SQLException;
import service.formationcrud;



/**
 *
 * @author Ahmed
 */
public class Main {
   
        public static void main(String[] args)  {
             Datasource dataSource = Datasource.getInstance();
             formationcrud ps = new formationcrud();
            // Formation ev=new Formation("terrrerree","tre","ff",145);
        //Personne p = new Personne("Test","Maria");
       // ps.ajouterEvent(ev);
        //ps.modifierFormation(ev,45);
           // System.out.println(ps.tri("nom","asc"));
              System.out.println(ps.readAll());

            
    
    }
    }
    

