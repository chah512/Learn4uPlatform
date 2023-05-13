/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Category;
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
public class ServiceCategory implements IIService<Category> {
    
     private Connection cnx = MyConnection.getInstance().getConnection();
    private Statement ste;
    private PreparedStatement pre;
 public ServiceCategory() {
        try {
            ste = cnx.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
        @Override
    public void addC(Category t) {
        try {
            String req = "INSERT INTO category (name, content) VALUES ('" + t.getName() + "','" + t.getContent() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("category ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
    
     

     @Override
    public void updateC(Category t) {
        try {
            String req = "UPDATE category SET name='" + t.getName() + "',content='" + t.getContent()+ "' WHERE id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("category modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteC(Category t) {
        try {
            String req = "DELETE FROM category where id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("category supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<Category> showC() {
        List<Category> list = new ArrayList<>();
        
        try {
            String req = "SELECT * FROM category";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString("name"), rs.getString("content")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
    /*    public List<Category> searchName(String filtre) {
        List<Category> myList = new ArrayList<Category>();

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String req = "SELECT * from category where `name` like'%" + filtre + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Category p2 = new Category(rs.getInt(0));

                p2.setId(rs.getInt(1));// soit numero , soit nom de la colonne , comme id => numero (index ) =1

                Category c = new Category(rs.getInt(2));

                String req2 = "SELECT * from category WHERE id=? ";
                Statement st2 = cnx.createStatement(); // import java.sql.Statement
                ResultSet rs2 = st2.executeQuery(req2);
               // st2.setInt(1, rs.getInt(2));
                
                while (rs2.next()) {
                    c.setName(rs2.getString("name"));
                }

                //c.setLibelle("jj");
                String categ = c.getName();
                

                p2.setName(rs.getString(3));
                p2.setContent(rs.getString(4));

                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }*/
    
    }

    
    
    
