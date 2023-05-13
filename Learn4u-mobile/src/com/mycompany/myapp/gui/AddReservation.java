/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ReservationService;

/**
 *
 * @author MSI GF63
 */
public class AddReservation extends Form{

    
    
    ComboBox<String> nbre= new ComboBox<String>("Un personne","Deux personnes","Troix Personnes","Quatre Personnes");
    TextField email= new TextField("","Email verification",20,TextArea.EMAILADDR);
    
    Button signup=new Button("Reserve now !");
    Button back=new Button("back");

    public AddReservation(Resources theme,int id) {
         super("Reservation List",BoxLayout.y());
        setUIID("LoginForm");
        getTitleArea().setUIID("Container");
        
       
        //this.add(firstname);
       
        //this.add(lastname);
        
        //this.add(email);
         email.getAllStyles().setMargin(LEFT, 0);
        
        //this.add(username);
        signup.setUIID("LoginButton");
       add(email);
       add(nbre);
       signup.addActionListener(l->{
           
           Reservation r= new Reservation(nbre.getSelectedItem());
       if(ReservationService.getInstance().add(r, id,email.getText()))
       {      EventFront a= new EventFront(theme);
              Dialog.show("Success","","OK",null);  
              a.show();
            
       }
       
       });
       add(signup);
       
    }
    
    
    
    
    
    
    
}
