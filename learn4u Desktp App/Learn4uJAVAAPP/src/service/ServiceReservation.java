/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Evenement;
import entity.Reservations;
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
public class ServiceReservation implements IServiceR<Reservations> {
     Connection cnx = DataSource.getInstance().getConnection();

    
    public void ajouterR(Reservations r) {
        try {
            String req = "INSERT INTO events_reservation ( Tel ,reservations) VALUES ('" + r.getTel() + "','" + r.getNbrReservations() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation ajoutée !");
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    
}
