/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevv;

import entity.Cours;
import entity.Formation;
import java.sql.SQLException;
import service.CoursService;

/**
 *
 * @author Ahmed
 */
public class nb {
      public static void main(String[] args) throws SQLException {
            // Datasource dataSource = Datasource.getInstance();
          //CoursService ps = new CoursService();
          //Formation f = new Formation(57,"terrrerree","tre","ff",145);
         // Cours ev=new Cours("rttrrrrrrrrreee","gftreeeeee","hhfffvgfff",f);
         // ps.ajouterCoursPST(ev);
        //Personne p = new Personne("Test","Maria");
        //ps.modifierFormationPST(ev);
       // ps.supprimerevent(56);
        //System.out.println(ps.search("jh"));
          
        CoursService ec = new CoursService();
       // System.out.println("le nombre de produits est " +ec.stat());
        System.out.println( ec.readAll());
         
       
    
}
    
}
