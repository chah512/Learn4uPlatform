/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.EventService;
import com.mycompany.myapp.services.UserService;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author MSI GF63
 */
public class EventListForm extends SideMenuBaseForm {
public static Event currentOffre = null;
    Resources theme = UIManager.initFirstTheme("/theme");
    Button addBtn;
    Label titreLabel, descriptionLabel, prixLabel, imageLabel;
    ImageViewer imageIV;
    Button editBtn, deleteBtn;
    Container btnsContainer;
    Button add=new Button("manage");
    
    public EventListForm(Resources theme) 
    {
        super("Events List",BoxLayout.y());
    
            ArrayList<Event> List = new ArrayList<Event>();
            List=EventService.getInstance().getAll();
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
                                new Label("  Event", "WelcomeBlue"),
                                new Label("List", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
             setupSideMenu(theme);        
     
    
    
    for(Event usr : List){
        
        addButton(usr,theme);

    }
    add(add);
    add.addActionListener(l->{
    new ManageEventForm(theme).show();
    });
    }

    @Override
    protected void showOtherForm(Resources res) {
      new Choice_Form(theme).show();
    }

    private void addButton(Event usr, Resources theme) {
        Label Id=new Label("Id :"+usr.getId());
        Label username=new Label("Titre :"+usr.getTitre());
        Label fullname=new Label("Description :"+usr.getDescription());
        Label email=new Label("Prix :"+usr.getPrix());
       
        
        Label line=new Label("____________________________________________");
        //Button remove=new Button("remove");
        Button update=new Button("update");
        update.addActionListener(l->{
        
        });
       
             
        
        
        
        add(BoxLayout.encloseX(Id)) ;
        add(BoxLayout.encloseX(username)) ;
        add(BoxLayout.encloseX(fullname)) ;
        add(BoxLayout.encloseX(email)) ;
        add(line);
        
                
               
        
        
    }
}
   
    
   

  

    
   

