/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.EventService;
import com.mycompany.myapp.services.ReservationService;
import java.util.ArrayList;

/**
 *
 * @author MSI GF63
 */
/**
 *
 * @author MSI GF63
 */
public class ReservationList extends SideMenuBaseForm {
public static Event currentOffre = null;
    Resources theme = UIManager.initFirstTheme("/theme");
    Button addBtn;
    Label titreLabel, descriptionLabel, prixLabel, imageLabel;
    ImageViewer imageIV;
    Button editBtn, deleteBtn;
    Container btnsContainer;
   
    
    public ReservationList(Resources theme) 
    {
        super("Reservation List",BoxLayout.y());
    
            ArrayList<Reservation> List = new ArrayList<Reservation>();
            List=ReservationService.getInstance().Allreservs();
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
                                new Label("  Reservations", "WelcomeBlue"),
                                new Label("List", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
             setupSideMenu(theme);        
     
    
    
    for(Reservation usr : List){
        
        addButton(usr,theme);

    }
    
    }

    @Override
    protected void showOtherForm(Resources res) {
      new Choice_Form(theme).show();
    }

    private void addButton(Reservation usr, Resources theme) {
        Label Id=new Label("Id :"+usr.getId());
        Label username=new Label("nombre de reservation :"+usr.getDuree());
        Label fullname=new Label("evenement :"+usr.getOffre());
       
       
        
        Label line=new Label("____________________________________________");
        //Button remove=new Button("remove");
        Button update=new Button("update");
        update.addActionListener(l->{
        
        });
       
        add(BoxLayout.encloseX(Id)) ;
        add(BoxLayout.encloseX(username)) ;
        add(BoxLayout.encloseX(fullname)) ;
        add(line);
        
                
               
        
        
    }
}
