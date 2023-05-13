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
import service.formationcrud;

/**
 *
 * @author Ahmed
 */
public class mainnn {
    
     public static void main(String[] args) throws SQLException {
            CoursService ps = new CoursService();
          Formation f = new Formation(57,"terrrerree","tre","ff",145);
          Cours ev=new Cours("rttrrrrrrrrreee","gftreeeeee","hhfffvgfff",f);
         // ps.ajouterCoursPST(ev);
        //Personne p = new Personne("Test","Maria");
        //ps.modifierFormationPST(ev);
       // ps.supprimerevent(56);
        //System.out.println(ps.search("jh"));
        // System.out.println(ps.getstat());
         System.out.println(ps.afficher());
         //System.out.println(ps.getstatt());

       
    
}}
