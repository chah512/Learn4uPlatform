/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Avis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author med
 */
public class ServiceAvis implements IService<Avis> {
    
     private Connection cnx = MyConnection.getInstance().getConnection();
    private Statement ste;
    private PreparedStatement pre;
 public ServiceAvis() {
        try {
            ste = cnx.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
        @Override
    public void addA(Avis t) {
        try {
            String req = "INSERT INTO avis (rating, title,category,content) VALUES ('" + t.getRating() + "','" + t.getTitle() + "','" + t.getCategory() + "','" +t.getContent()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Avis ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
    
     

     @Override
    public void updateA(Avis t) {
        try {
            String req = "UPDATE avis SET rating='" + t.getRating() + "',title='" + t.getTitle() + "',category='" + t.getCategory() + "',content='" + t.getContent() + "' WHERE id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Avis modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteA(Avis t) {
        try {
            String req = "DELETE FROM avis where id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Avis supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<Avis> afficher() {
        List<Avis> list = new ArrayList<>();
        
        try {
            String req = "SELECT * FROM avis";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Avis(rs.getInt(1), rs.getString("rating"), rs.getString("title"), rs.getString("category"), rs.getString("content")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
    }

    
    
    
