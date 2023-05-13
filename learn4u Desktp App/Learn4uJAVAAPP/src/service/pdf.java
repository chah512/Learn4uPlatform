/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
//import models.event;
//import utiles.DataBase;
import pidevv.Datasource;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maiss
 */
public class pdf {
        private Connection con;
        private Statement ste;
    public pdf()  {
          
               
           
}
    public void add(String file) throws FileNotFoundException, SQLException, DocumentException{
        
        /* Create Connection objects */
//                con =con.getinstance().getcnx();
               con = Datasource.getInstance().getCnx();

                ste=con.createStatement();
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C:/Users"));
                 
                 ResultSet rs=ste.executeQuery("select * from Formmattion");
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(4) ;
                //create a cell object
                PdfPCell table_cell;
                                
                              
                                table_cell=new PdfPCell(new Phrase("id"));
                                my_report_table.addCell(table_cell);
                               
                                table_cell=new PdfPCell(new Phrase("nom"));
                                my_report_table.addCell(table_cell);
                                
               
                while (rs.next()) {  
                                            
                               // int IdFacture=rs.getInt("IdFacture");
                                //table_cell=new PdfPCell(new Phrase(IdFacture));
                                //my_report_table.addCell(table_cell);
                                int id=rs.getInt("id");
                                table_cell=new PdfPCell(new Phrase(id));
                                my_report_table.addCell(table_cell);
                               // String Adresse=rs.getString("Adresse");
                                //table_cell=new PdfPCell(new Phrase(Adresse));
                                //my_report_table.addCell(table_cell);
                                String type=rs.getString("nom");
                                table_cell=new PdfPCell(new Phrase(type));
                                my_report_table.addCell(table_cell);
                              //  String prix=rs.getString("description");
                               /// table_cell=new PdfPCell(new Phrase(prix));
                               // my_report_table.addCell(table_cell);
                               // String montant=rs.getString("comments");
                               // table_cell=new PdfPCell(new Phrase(montant));
                               // my_report_table.addCell(table_cell);
                                //String quantite=rs.getString("quantite");
                                //table_cell=new PdfPCell(new Phrase(quantite));
                                //my_report_table.addCell(table_cell);
                              //  int prix =rs.getInt("prix");
                               // String pp = String.valueOf(prix);
                                //table_cell=new PdfPCell(new Phrase(prix));
                                //my_report_table.addCell(table_cell);
                               /* int prix =rs.getInt("prix");
                               // String pp = String.valueOf(prix);
                                table_cell=new PdfPCell(new Phrase(prix));
                                my_report_table.addCell(table_cell);
                                int prix =rs.getInt("prix");
                               // String pp = String.valueOf(prix);
                                table_cell=new PdfPCell(new Phrase(prix));
                                my_report_table.addCell(table_cell);
                                int prix =rs.getInt("prix");
                               // String pp = String.valueOf(prix);
                                table_cell=new PdfPCell(new Phrase(prix));
                                my_report_table.addCell(table_cell);
                                int prix =rs.getInt("prix");
                               // String pp = String.valueOf(prix);
                                table_cell=new PdfPCell(new Phrase(prix));
                                my_report_table.addCell(table_cell);
                                int prix =rs.getInt("prix");
                               // String pp = String.valueOf(prix);
                                table_cell=new PdfPCell(new Phrase(prix));
                                my_report_table.addCell(table_cell);*/
                               

                }
                                
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
               /* Close all DB related objects */
                
        
    }
     
}
