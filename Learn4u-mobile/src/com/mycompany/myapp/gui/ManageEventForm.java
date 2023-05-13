package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.EventService;
import com.mycompany.myapp.services.UserService;


import java.io.IOException;

public class ManageEventForm extends SideMenuBaseForm {
    TextField titre= new TextField("","Titre",40,TextArea.ANY);
    TextField description= new TextField("","Description",40,TextArea.ANY);    
    
    TextField prix= new TextField("","prix",40,TextArea.ANY);
    
   
   
    Button signup=new Button("Ajouter");
    Button back=new Button("cancel");
    public ManageEventForm(Resources theme )
    {
         super("Events List",BoxLayout.y());
             Toolbar tb = getToolbar();
            tb.setTitleCentered(false);
            
            
                Button menuButton = new Button("");
                menuButton.setUIID("Title");
                FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
                menuButton.addActionListener(e -> getToolbar().openSideMenu());
                
                 Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label("  Event ", "WelcomeBlue"),
                                new Label("Add", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
             setupSideMenu(theme);  
             
             //this.add(firstname);
        //firstname.getAllStyles().setMargin(LEFT, 0);
        //this.add(lastname);
        //lastname.getAllStyles().setMargin(LEFT, 0);
        //this.add(email);
         //email.getAllStyles().setMargin(LEFT, 0);
        
        //this.add(username);
       // username.getAllStyles().setMargin(LEFT, 0);
        //this.add(password);
        //password.getAllStyles().setMargin(LEFT, 0);
        //this.add(verifypassword);
        //verifypassword.getAllStyles().setMargin(LEFT, 0);
        //this.add(bd);
        //bd.getAllStyles().setMargin(LEFT, 0);
        //this.add(birthday);
        //birthday.getAllStyles().setMargin(RIGHT, 0);
        //this.add(signup);
        signup.setUIID("LoginButton");
                 signup.addActionListener(l->{
                             if((titre.getText()!="")&&(description.getText()!="")&&(prix.getText()!=""))
                          {
                              
                            String   fullname=titre.getText()+" "+description.getText();
                           Event event=new Event(titre.getText(),description.getText(),prix.getText());
                         
                           if (EventService.getInstance().add(event))
                           {   Dialog.show("Success"," ", "OK",null); 
                               EventListForm a= new EventListForm(theme);
                                a.show();
                           }
                           else
                               ToastBar.showErrorMessage("error",5);
                            //Dialog.show("Success","Account created",new Command("ok"));
                            //else
                            // Dialog.show("ERROR", "Server error", new Command("OK"));
                          
                                  ToastBar.showErrorMessage("Make sure of your typing", 5);
                              }
                              else
                          {
                              ToastBar.showErrorMessage("Fill all blanks",5);
                          }
                         
                 });
                 
                 back.addActionListener(l->{
                EventListForm a= new EventListForm(theme);
                a.show();
                 });
                 
                 Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
               
                
                add(spaceLabel);
                add(titre);
                add(description);
               
                add(prix);      
                       
                add(signup);
                add(back);
       
    }

    @Override
    protected void showOtherForm(Resources res) 
    {
                 new Choice_Form(res).show();    
    }
 }