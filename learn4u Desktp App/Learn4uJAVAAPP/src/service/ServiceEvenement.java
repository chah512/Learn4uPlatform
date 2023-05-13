/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import utils.DataSource;

/**
 *
 * @author solta
 */
public class ServiceEvenement implements IService<Evenement> {

    Connection cnx = DataSource.getInstance().getConnection();

    @Override
    public void ajouter(Evenement e) {
        try {
            String req = "INSERT INTO calendar (titre, description, start, end , nbr) VALUES ('" + e.getNom_ev() + "','" + e.getDescription() + "','" + e.getDate_deb() + "','" + e.getDate_fin() + "','" + e.getNbr() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement ajoutée !");
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Evenement e) {
        try {
            String req = "DELETE  FROM calendar where id=" + e.getId_ev();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("EVENEMENT supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Evenement e) {
        try {
            String req = "UPDATE calendar SET titre='"+e.getNom_ev()+"',description='" + e.getDescription()+ "',nbr='" + e.getNbr() + "' WHERE id_ev =" + e.getId_ev();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("evenement modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public List<Evenement> afficher() {
        List<Evenement> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM calendar";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Evenement(rs.getInt(1), rs.getString("titre"), rs.getString("description"), rs.getDate("start"), rs.getDate("end"), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public void Trier() {
        ServiceEvenement se = new ServiceEvenement();
        List<Evenement> Trier = se.afficher().stream().sorted(Comparator.comparing(Evenement::getNbr)).collect(Collectors.toList());
        Trier.forEach(System.out::println);
    }

    public void rechercher(String nom_ev) {
        List<Evenement> result = afficher().stream().filter(line -> nom_ev.equals(line.getNom_ev())).collect(Collectors.toList());
        System.out.println("----------");
        result.forEach(System.out::println);

    }

    @Override
    public List<Evenement> recherche(Evenement t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*    public void modifierR(Evenement t) {
        try {
            String req = "UPDATE evenement SET nom_ev='"+t.getNom_ev()+"',description='" + t.getDescription()  + "',date_deb='" + t.getDate_deb() + "',date_fin='" + t.getDate_fin() + "',nbr='" + t.getNbr() + "' WHERE id_ev =" + t.getId_ev();
    Statement st = new DataSource().getConnection().createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    */
    /*
     @Override
    public void modifier(Evenement e) {
        try {
            String req = "UPDATE evenement SET nom_evenement='"+e.getNom_ev()+"',description_evenement='" + e.getDescription()  + "',nombre_max_participants='" + e.getNbr() + "' WHERE id_evenement =" + e.getId_ev();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("evenement modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
*/
    @Override
    public void modifierR(Evenement t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
    
    



    
    
    
