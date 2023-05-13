/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.formationcrud;
import entity.Formation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import pidevv.Pidevv;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import pidevv.Datasource;
import utils.SmsTwillio;


/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ConsulterEventController implements Initializable {

    @FXML
    private TableColumn<Formation,Date> date_dep;
   
    @FXML
    private TableColumn<Formation,Integer> id;
    @FXML
    private Button supp;
    @FXML
    private Button mod;
    @FXML
    private TextField filterField;
    @FXML
    private AnchorPane slider;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;
    private Connection cnx;
    public static Formation Posta;
    @FXML
    private TableColumn<Formation, String> nom;
     ObservableList<Formation> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Formation,String> description;
    
     private final ObservableList<Formation> dataList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Formation,Integer> prix;
     public static String nom_recup;
    public static String decriptionrecup;
    public static Date date_deprecup;
    public static Date date_finrecup;
    public static int prixrecup;
        public static int id_rec;
        public static String img_recup;
    
    
    @FXML
    private TableView<Formation> tbl;
 
    @FXML
    private Button ajouterr;
    @FXML
    private TableColumn<Formation,String> imgee;
    @FXML
    private TableColumn<Formation, Date> datefinn;
    private Button ccours;
    @FXML
    private Button imprimmer;
    @FXML
    private Button Stattt;
    @FXML
    private AnchorPane ge;
    @FXML
    private Label st;
    private Button ref;
    @FXML
    private Button act;
    @FXML
    private Button aff;
    @FXML
    private Button imprimer;
    @FXML
    private ImageView dfd;
    @FXML
    private ImageView shahyn;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formationcrud evcrud = new formationcrud();
        ArrayList<Formation> ev = (ArrayList<Formation>) evcrud.readAll();
        ObservableList<Formation> obs = FXCollections.observableArrayList(ev);
        //table.setItems(obs);
      nom.setCellValueFactory(new PropertyValueFactory<Formation,String>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<Formation,String>("description"));
        date_dep.setCellValueFactory(new PropertyValueFactory<Formation,Date>("datede"));
        datefinn.setCellValueFactory(new PropertyValueFactory<Formation,Date>("datefi"));
        
        prix.setCellValueFactory(new PropertyValueFactory<Formation,Integer>("prix"));
        id.setCellValueFactory(new PropertyValueFactory<Formation,Integer>("id"));
        imgee.setCellValueFactory(new PropertyValueFactory<>("image"));
         
     
 FilteredList<Formation> filteredData = new FilteredList<>(FXCollections.observableArrayList(ev), b -> true);
 	// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(events -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (events.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (events.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Formation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tbl.comparatorProperty());

        tbl.setItems(sortedData);
        // TODO
        //imprimer = new Button ('Export to excel');
        cnx = Datasource.getInstance().getCnx();
        

        imprimer.setOnAction((actionEvent -> {
        imprimer.setFont(Font.font("Sansserif", 15));
        String query = "select * from formmattion";
        try {

        Statement pst= cnx.createStatement();
        ResultSet rs = pst.executeQuery(query);

 
        // class to represent excel file format
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Formation Details");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Nom");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Price");
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.setColumnWidth(3, 256*25);//256-character width

        sheet.setZoom(150); //scale(150%

        int index = 1;
        while (rs.next()) {
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getString("id"));
            row.createCell(1).setCellValue(rs.getString("nom"));
            row.createCell(2).setCellValue(rs.getString("description"));
            row.createCell(3).setCellValue(rs.getString("prix"));
            index++;

        }
        
          String file_name = "formationn.xlsx";

        FileOutputStream fileOut = new FileOutputStream (file_name);
        wb.write(fileOut);
        fileOut.close();


        } catch (SQLException ex) {
            System.out.println("error");
        } catch (IOException ex) {
            System.out.println("error");
        }
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("formation details exported to excel Sheet");
        alert.show();
        //pst.close();
        //rs.close();

        }));
    }    

    public void setDate_dep(TableColumn<Formation,Date> date_dep) {
        this.date_dep = date_dep;
    }

  

    public void setId(TableColumn<Formation,Integer> id) {
        this.id = id;
    }

    public void setNom(TableColumn<Formation,String> nom) {
        this.nom = nom;
    }

    public void setDescription(TableColumn<Formation,String> description) {
        this.description = description;
    }

    public void setPrix(TableColumn<Formation,Integer> prix) {
        this.prix = prix;
    }

    public void setTbl(TableView<Formation> tbl) {
        this.tbl = tbl;
    }
    
    

    @FXML
    private void supprimer(ActionEvent Formation) {
         Formation ev = tbl.getSelectionModel().getSelectedItem();
        formationcrud udao = formationcrud.getInstance();
        udao.supprimerevent(ev.getNom());
        System.out.println(ev.getStart_date());
        System.out.println(ev.getDescription());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Formation supprimer!");
                        alert.show();

        tbl.refresh();
        
        SmsTwillio.smsCancelRent(ev.getNom());
    }

    @FXML
    private void modifier(ActionEvent Formation) {
         Formation ev = tbl.getSelectionModel().getSelectedItem();
        ConsulterEventController.nom_recup=ev.getNom();
        ConsulterEventController.decriptionrecup=ev.getDescription();
        ConsulterEventController.date_deprecup=ev.getStart_date();
        ConsulterEventController.date_finrecup=ev.getEnd_date();
        ConsulterEventController.prixrecup=ev.getPrix();
                ConsulterEventController.id_rec=ev.getId();

        System.out.println(ev.getId());
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/updateEvent.fxml"));
            Stage stage = (Stage) mod.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void form(MouseEvent Formation) {
    }

    @FXML
    private void promo(MouseEvent Formation) {
    }

    @FXML
    private void abon(MouseEvent Formation) {
        
    }

    @FXML
    private void stat(MouseEvent Formation) {
    }

    @FXML
    private void ajoutere(ActionEvent Formation) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ajouter_formation.fxml"));
            Stage stage = (Stage) ajouterr.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cours(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterCours.fxml"));
            Stage stage = (Stage) ccours.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Imprimmer(ActionEvent event) throws SQLException {
       /* Formation service = new Formation();
           Document pdfReport = new Document() ;
           PdfWriter.getInstance(pdfReport, new FileOutputStream("menu.pdf"));
            pdfReport.open();
            pdfReport.add(new Paragraph("Formation"));
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            
          
          PdfPTable my_report_table = new PdfPTable(9);
          
            PdfPCell  tableCellColumn = new PdfPCell(new Phrase("nom"));
           my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("description"));
          my_report_table.addCell(tableCellColumn);
          tableCellColumn = new PdfPCell(new Phrase("prix"));
            my_report_table.addCell(tableCellColumn);
       
            
            
            double h= 0;
            tbl.getItems().forEach((Formation e) -> {
                
               PdfPCell tableCell = new PdfPCell(new Phrase(e.getPrix()));
                my_report_table.addCell(tableCell);
                
                
                tableCell = new PdfPCell(new Phrase(e.getDescription()));
                my_report_table.addCell(tableCell);
                
                tableCell = new PdfPCell(new Phrase(e.getNom()));
                my_report_table.addCell(tableCell);
                
                
                
                
                
                 
            });
            /* Attach report table to PDF */
           /* pdfReport.add(my_report_table);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            
            
            
            pdfReport.close();
            
            Alert alertReservation = new Alert(Alert.AlertType.INFORMATION);
            alertReservation.setTitle("Extraction en PDF");
            alertReservation.setHeaderText(null);
            alertReservation.setContentText("PDF report has been created.\nYou'll find "
                    + "the file under: Bureau");
            alertReservation.showAndWait();*/
           Datasource cnx = Datasource.getInstance();

        PreparedStatement pst=null;
        ResultSet rs=null;
        String guery = " select * from formmattion";
        try {   

        pst= cnx.getCnx().prepareStatement(guery);
        rs= pst.executeQuery();

        String file_name = "formation.pdf";
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(file_name));

        doc.open();

        com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("C:\\xampp\\htdocs\\FirstProject-main\\public\\uploads\\logo.png");
        img.scaleAbsoluteWidth(600);
        img.scaleAbsoluteHeight(92);
        img.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
        doc.add(img);

        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("formation list",FontFactory.getFont(FontFactory.TIMES_BOLD,20,BaseColor.BLUE)));
        doc.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        PdfPCell cell;
        cell = new PdfPCell (new Phrase("Name", FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);

        cell = new PdfPCell (new Phrase("Description", FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);

        cell = new PdfPCell (new Phrase("Price", FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);

        
        /////////////////////////////////////////////////////////////////////////////////
        while(rs.next()) {
        cell = new PdfPCell (new Phrase(rs.getString("Nom").toString(), FontFactory.getFont("Arial",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell (new Phrase(rs.getString("description").toString(), FontFactory.getFont("Arial",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell (new Phrase(rs.getString("prix").toString(), FontFactory.getFont("Arial",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell); }

        doc.add(table);

        System.out.println("done");
        doc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Formation details exported to PDF Sheet");
            alert.show();

            

           
    }

    @FXML
    private void Sta(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/stat.fxml"));
            Stage stage = (Stage) ajouterr.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gest(MouseEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterCours.fxml"));
            Stage stage = (Stage) ge.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stt(MouseEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/stat.fxml"));
            Stage stage = (Stage) st.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @FXML
    private void actt(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterEvent.fxml"));
            Stage stage = (Stage) act.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afff(ActionEvent event) {
        Formation ev = tbl.getSelectionModel().getSelectedItem();
        ConsulterEventController.nom_recup=ev.getNom();
        ConsulterEventController.decriptionrecup=ev.getDescription();
        ConsulterEventController.date_deprecup=ev.getStart_date();
        ConsulterEventController.date_finrecup=ev.getEnd_date();
        ConsulterEventController.prixrecup=ev.getPrix();
                ConsulterEventController.id_rec=ev.getId();
                        ConsulterEventController.img_recup=ev.getImage();

        System.out.println(ev.getId());
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
            Stage stage = (Stage) mod.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void affr(MouseEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AfficherPersonne.fxml"));
            Stage stage = (Stage) dfd.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backtomanagement(MouseEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/UsersList/FXMLParent.fxml"));
            Stage stage = (Stage) shahyn.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void BACKKKK(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/UsersList/FXMLParent.fxml"));
            Stage stage = (Stage) MenuClose.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
    

