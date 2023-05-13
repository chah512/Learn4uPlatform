/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.services.AvisService;
import javafx.scene.control.ComboBox;


/**
 *
 * @author med
 */
public class addavisForm extends Form{
    TextField rating= new TextField("","Rating",40,TextArea.ANY);   
    TextField title= new TextField("","Title",40,TextArea.ANY);    
    TextField category= new TextField("","Category",40,TextArea.ANY);    
    TextField content= new TextField("","Content",40,TextArea.ANY);
  
    Button Send=new Button("Send rate");
    Button back=new Button("back");

    public addavisForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        /*setUIID("LoginForm");
        getTitleArea().setUIID("Container");*/
        
       
        //this.add(title);
        title.getAllStyles().setMargin(LEFT, 0);
        //this.add(content);
        content.getAllStyles().setMargin(LEFT, 0);
        back.addActionListener(l->{
        
        EventListForm a= new EventListForm(theme);
        a.show();
        });
        
        //this.add(Send);
       /*Send.setUIID("LoginButton");*/
                 Send.addActionListener(l->{
                             if((title.getText()!="")&&(content.getText()!=""))
                          {
                              Avis k=new Avis(rating.getText(),title.getText(),category.getText(),content.getText());
                              if (AvisService.getInstance().addA(k))
                           {   Dialog.show("Success","Rate's added", "OK",null); 
                              /* LoginForm F1 = new LoginForm(theme);*/
                               /*  F1.showBack();*/
                           }
                           else
                               ToastBar.showErrorMessage("error",5);
                            //Dialog.show("Success","Account created",new Command("ok"));
                            //else
                            // Dialog.show("ERROR", "Server error", new Command("OK"));
                             }
                              else
                          {
                              ToastBar.showErrorMessage("Fill all blanks",5);
                          }
                         
                 });
        //this.add(back);
           /*   back.addActionListener(l->{
             LoginForm F1 = new LoginForm(theme);
               F1.showBack();
              });*/
            
              
    
     Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
           Container by = BoxLayout.encloseY(
                
                spaceLabel,
                BorderLayout.center(rating),
                BorderLayout.center(title),
                BorderLayout.center(category),
                BorderLayout.center(content),       
           
                        
                Send,
                back
        );
        add(BorderLayout.CENTER, by);
    
    }
    
      
    
    
}
