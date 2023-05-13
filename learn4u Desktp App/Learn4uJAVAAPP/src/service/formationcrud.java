/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import pidevv.Database;

import entity.Formation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidevv.Datasource;
//import org.controlsfx.control.Notifications;

/**
 *
 * @author Ahmed
 */
public class formationcrud implements NewInterface {
    private PreparedStatement pst;


    private static formationcrud instance;
    private Statement st;
    private ResultSet rs;
    Connection myConnex;
    //cnxBD myc = cnxBD.myc.getIstance();
    Connection cnx = Datasource.getInstance().getConnection();

    public formationcrud() {
        Database cs = Database.getInstance();
        try {
            st = cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(formationcrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static formationcrud getInstance() {
        if (instance == null) {
            instance = new formationcrud();
        }
        return instance;
    }

    public void ajouterEvent(Formation ev) {
        System.out.println("yodkhel lel fnc Formation");
        String req = "insert into formmattion"
                + " (nom,prix,description,image)"
                + " values ('" + ev.getNom() + "','" + ev.getPrix()  +"','" + ev.getDescription()+"','" + ev.getImage() + "')";

        try {
            st.executeUpdate(req);
            System.out.println("formation bien ajouter");

        } catch (SQLException ex) {
            Logger.getLogger(formationcrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   

    public void supprimerevent(String nom) {

        try {
            String req2 = "DELETE FROM formmattion  WHERE  nom='" + nom + "'";

            st.executeUpdate(req2);
            System.out.println("formation bien supprimer");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
     public boolean modifierFormation(Formation f,int id) throws SQLException{
        //java.sql.Date sqlDate=new java.sql.Date(f.getStart_date().getTime());
        String req="update formmattion set nom='"+f.getNom()+"',image ='"+f.getImage()+"',description='"+f.getDescription()+"',prix='"+f.getPrix()+"' where id="+id;
        System.out.println(f.getId());
        try {
           if(st.executeUpdate(req) > 0)
               return true;
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    public boolean update(Formation ev, int ref, Date date_dep, Date date_fin) {
        String qry = "UPDATE formmattion SET nom = '" + ev.getNom() + "', prix = '" + ev.getPrix() + "',datede = '" + date_dep + "',datefi = '" + date_fin + "',description = '" + ev.getDescription() + "'WHERE id = '" + ref + "'";

        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Formation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public List<Formation> afficher() {
        List<Formation> formations = new ArrayList<>();
        String requete = "SELECT * FROM formmattion";

        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
//                Personne p = new Personne();
//                p.setId(rs.getInt("id"));
//                p.setNom(rs.getString(2));
//                p.setPrenom(rs.getString("prenom"));
//                personnes.add(p);                
                formations.add(new Formation(rs.getInt("id"), rs.getString("nom"), rs.getString("description")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(formationcrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        return formations;
    }
    public List <Formation> readAll(){
            
            String req="select* from formmattion";
            List <Formation> list=new ArrayList<>();
            
        try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while(rs.next()){
                list.add(new Formation(rs.getInt("id"),rs.getString("nom"),rs.getInt("prix"),rs.getString("description"),rs.getString("image"),rs.getDate("datede")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(formationcrud.class.getName()).log(Level.SEVERE, null, ex);
        }   
            return list;
        }
     public List <Formation> tri (String critere,String facon){
               
            if(critere.equals("name"))
                critere="nom";
            //else if(critere.equals("price"))
               // critere="prix";
            else if(critere.equals("descriptio"))
                critere="description";
            
            String req="select* from formmattion order by  "+critere+" "+facon;
            List <Formation> list=new ArrayList<>();
            try {
                st=cnx.createStatement();
                rs=st.executeQuery(req);
                while(rs.next()){
                    list.add(new Formation(rs.getInt("id"),rs.getString("nom"),rs.getInt("prix"),rs.getString("description"),rs.getString("image"),rs.getDate("datede")));                
                }
            } catch (SQLException ex) {
                 Logger.getLogger(formationcrud.class.getName()).log(Level.SEVERE, null, ex);
            }   
       
            return list;
        }
     public void modifierFormationPST(Formation f) throws SQLException{
        String req="update formmattion set nom=? ,description =?  ,prix=?  where nom=? ";
        try {
            pst=cnx.prepareStatement(req);
            
            pst.setString(1, f.getNom());
           
            pst.setString(2,f.getDescription());
        
            
            pst.setInt(3,f.getPrix());
            pst.setString(4, f.getNom());
            
            
           // java.sql.Date sqlDate=new java.sql.Date(f.getDteAjout().getTime());
            
            //pst.setDate(4,sqlDate);
            
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
            pst=cnx.prepareStatement(req);
        }
    
     }
     public void ajouterFormationPST(Formation c){
    
          
          
        String req="insert into formmattion (nom,description,prix,datede,datefi,image) values (?,?,?,?,?,?)";
        try {
            pst=cnx.prepareStatement(req);  
            pst.setString(1, c.getNom());
            pst.setString(2,c.getDescription());
            pst.setInt(3,c.getPrix());
            
            
            pst.setDate(4,c.getStart_date());
            
            
            pst.setDate(5,c.getEnd_date());
            pst.setString(6, c.getImage());
            
            
            
           
            
            
  //          System.out.println(id_formation);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("hne");
            Logger.getLogger(formationcrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    



     }
     public String countFormation() {

        String req = "SELECT COUNT(*) FROM formmattion";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            pst.executeQuery(req);
            ResultSet rs = pst.getResultSet();
            rs.next();
            return ("  " + rs.getInt("count(*)") + " formations");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }}
     public Formation getCarById(String id) {
        String query = "SELECT * FROM formmattion WHERE nom ='" + id + "'";

        try {
            rs = st.executeQuery(query);
            while (rs.next()) {
                Formation car = new Formation(rs.getInt("id"), rs.getString("nom"), rs.getString("description"), rs.getString("image"));
                return car;

            }
        } catch (SQLException e) {
            e.printStackTrace();
    
}
        return null;

}
}
