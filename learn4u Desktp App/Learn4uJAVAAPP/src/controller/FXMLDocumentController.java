/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import static controller.ConsulterEventController.Posta;
import entity.Formation;
import java.awt.AWTException;

import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.activation.DataSource;
import pidevv.Datasource;
import service.formationcrud;


/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField search_field;
    @FXML
    private Button instructor_button;
    @FXML
    private Label label1;
    @FXML
    private Button button1;
    @FXML
    private Label idf1;
    
    @FXML
    private GridPane gird;
    @FXML
    private Label p;
    @FXML
    private Button xx;
    @FXML
    private AnchorPane dds;
    @FXML
    private ImageView ws;
    public static Formation carPassed;
    @FXML
    private Label p1;
    @FXML
    private Label p2;
    @FXML
    private Label label11;
    @FXML
    private Label label12;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         label1.setText(ConsulterEventController.nom_recup);
         label12.setText(ConsulterEventController.decriptionrecup);
         p.setText(String.valueOf(ConsulterEventController.prixrecup));
         label11.setText(String.valueOf(ConsulterEventController.date_deprecup));
         formationcrud se =new formationcrud();
        Formation k = new Formation();
        //ArrayList<Product> k = se.affichageEventById(product.getProductName());
        


        ImageView img = new ImageView();
            ws.setImage(new Image("http://localhost/FirstProject-main/public/uploads/"+ConsulterEventController.img_recup,200,200,true,true));

       // gird.setText(ConsulterEventController.decriptionrecup);
        // TODO
        /*try {
            formationcrud carServiceImp = formationcrud.getInstance();
            String path = carServiceImp.getCarById(carPassed.getNom()).getImage();
            if (path != null) {
                InputStream stream = new FileInputStream(path);
                javafx.scene.image.Image image = new javafx.scene.image.Image(stream);
                ws.setImage(image);
            } else {
                System.out.println("no image");
            }
        } catch (FileNotFoundException e) {
            System.out.println("no changes ");
        }*/
        
          /*Connection cnxx = MyConnection.getInstance().getCnx();
            String req = "SELECT * FROM formmattion WHERE id = ?";

            PreparedStatement pst;
            try {
                //int iddd = Integer.parseInt(tfid.getText());
                pst = cnxx.prepareStatement(req);
                
                ResultSet resulSet = pst.executeQuery();
                if (resulSet.first()) {
    
                InputStream inputStream = new ByteArrayInputStream(resulSet.getBytes("image"));
                javafx.scene.image.Image image = new javafx.scene.image.Image(inputStream, 1366, 350, false, false);
 
 
                ws.setImage(image);
     
                p.setText(resulSet.getString("description"));
                label1.setText(resulSet.getString("nom"));
                

                }
                
            }catch (SQLException ex) {
            System.err.println(ex.getMessage())
                  
        } */
            
    
        
      
           
             
             
    
    
    }    

    @FXML
    private void search(KeyEvent event) {
    }

    @FXML
    private void pp(ActionEvent event) {
    }

    @FXML
    private void becomeInstructor(ActionEvent event) {
    }

    @FXML
    private void ffff(MouseEvent event) {
    }
    public void displayTray() throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        //Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/img/menu.png"));

       // TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        //trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
       // trayIcon.setToolTip("System tray icon demo");
       // tray.add(trayIcon);

       // trayIcon.displayMessage("Hello, World", "notification demooooom", TrayIcon.MessageType.INFO);
    }


    @FXML
    private void panier(MouseEvent event) {
    }

    @FXML
    private void abon(MouseEvent event) {
    }

    @FXML
    private void stat(MouseEvent event) {
    }


    @FXML
    private void ajouter(ActionEvent event) throws DocumentException, SQLException {
       
        Datasource cnx = Datasource.getInstance();

        PreparedStatement pst=null;
        ResultSet rs=null;
        String guery = " select * from formmattion";
        try {

            pst= cnx.getCnx().prepareStatement(guery);
            rs= pst.executeQuery();

//            String file_name = Posta.getNom()+".pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(ConsulterEventController.nom_recup+".pdf"));

            doc.open();
            com.itextpdf.text.Image imgl = com.itextpdf.text.Image.getInstance("C:\\xampp\\htdocs\\FirstProject-main\\public\\uploads\\logo.png");
            imgl.scaleAbsoluteWidth(100);
            imgl.scaleAbsoluteHeight(100);
            imgl.setAlignment(com.itextpdf.text.Image.ALIGN_RIGHT);
            doc.add(imgl);
         //   doc.add(new Paragraph(" "));
          //  doc.add(new Paragraph(" "));

            com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("C:\\xampp\\htdocs\\FirstProject-main\\public\\uploads\\"+ConsulterEventController.img_recup);
            img.scaleAbsoluteWidth(200);
            img.scaleAbsoluteHeight(150);
            img.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(ConsulterEventController.nom_recup,FontFactory.getFont(FontFactory.TIMES_BOLD,20,BaseColor.BLUE)));
            doc.add(new Paragraph(ConsulterEventController.decriptionrecup,FontFactory.getFont(FontFactory.TIMES,10,BaseColor.BLUE)));
            doc.add(new Paragraph((String.valueOf(ConsulterEventController.prixrecup)),FontFactory.getFont(FontFactory.TIMES,10,BaseColor.BLUE)));
            doc.add(new Paragraph((String.valueOf(ConsulterEventController.date_deprecup)),FontFactory.getFont(FontFactory.TIMES,10,BaseColor.BLUE)));

            doc.add(img);
            Paragraph Pargr = new Paragraph();
            doc.add(new Paragraph(" "));
            //Pargr=(new Paragraph(Posta.getPrix(),FontFactory.getFont(FontFactory.COURIER,20,BaseColor.DARK_GRAY)));
            doc.add(Pargr);

            doc.add(new Paragraph(" "));
       //     doc.add(new Paragraph(Posta.getTitle(),FontFactory.getFont(FontFactory.TIMES_BOLD,20,BaseColor.LIGHT_GRAY)));
         //   doc.add(new Paragraph(Posta.getCategory(),FontFactory.getFont(FontFactory.COURIER_BOLD,20,BaseColor.LIGHT_GRAY)));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
           // doc.add(new Paragraph(Posta.g,FontFactory.getFont(FontFactory.TIMES,30,BaseColor.BLACK)));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(30);
            table.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell cell;
            //cell = new PdfPCell (new Phrase(Posta.getAuthor(), FontFactory.getFont("Comic Sans MS",12)));
           // cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           // cell.setBackgroundColor(BaseColor.BLACK);
            //doc.add(cell);

            cell = new PdfPCell (new Phrase(String.valueOf(ConsulterEventController.date_deprecup), FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBackgroundColor(BaseColor.BLACK);
            doc.add(cell);
            cell = new PdfPCell (new Phrase(String.valueOf(ConsulterEventController.prixrecup), FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBackgroundColor(BaseColor.BLACK);
            doc.add(cell);
             



            System.out.println("done");
            doc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Formation details exported to PDF Sheet");
        alert.show();




    

    }

    @FXML
    private void cc(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterEvent.fxml"));
            Stage stage = (Stage) xx.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
