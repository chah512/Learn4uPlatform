/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.SmsTwillio;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class SendsmsController implements Initializable {

    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;
    @FXML
    private AnchorPane slider;
    @FXML
    private TextField num;
    @FXML
    private TextArea msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void form(MouseEvent event) {
    }

    @FXML
    private void abon(MouseEvent event) {
    }

    @FXML
    private void stat(MouseEvent event) {
    }

    @FXML
    private void promo(MouseEvent event) {
    }

    @FXML
    private void envoyer(ActionEvent event) {
        //try {
            // Construct data
          //  String data = "";
            /*
             * Note the suggested encoding for certain parameters, notably
             * the username, password and especially the message.  ISO-8859-1
             * is essentially the character set that we use for message bodies,
             * with a few exceptions for e.g. Greek characters.  For a full list,
             * see:  https://www.bulksms.com/developer/eapi/submission/character-encoding/
             */
            /*data += "username=" + URLEncoder.encode("ahmedda", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("Loujayn123456", "ISO-8859-1");
            data += "&message=" + URLEncoder.encode(msg.getText(), "ISO-8859-1");
            data += "&want_report=1";
            data += "&msisdn=21626921663";
            // Send data
            // Please see the FAQ regarding HTTPS (port 443) and HTTP (port 80/5567)
            URL url = new URL("https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            System.out.println();
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Print the response output...
                System.out.println(line);
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
    }*/
    
      SmsTwillio.sms (msg.getText());
 }}