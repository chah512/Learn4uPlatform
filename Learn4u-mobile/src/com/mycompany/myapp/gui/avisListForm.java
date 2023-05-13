/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.services.AvisService;
import java.util.ArrayList;

/**
 *
 * @author MSI GF63
 */
public class avisListForm extends SideMenuBaseForm{

    public avisListForm(Resources theme) {
            super("Rate List",BoxLayout.y());
            ArrayList<Avis> List = new ArrayList<Avis>();
            List=AvisService.getInstance().Allavis();
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
                                new Label("  Rate", "WelcomeBlue"),
                                new Label("List", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
             setupSideMenu(theme);        
     
    
    
    for(Avis av : List){
        
        addButton(av,theme);

    }
   
    
    } 
   
    
    
    
    
    @Override
    protected void showOtherForm(Resources theme) {
             new Choice_Form(theme).show();
    }

    private void addButton( Avis av,Resources theme) {
        Label id=new Label("Id :"+av.getId());
        Label rating=new Label("Rating :"+av.getRating());
        Label title=new Label("Title :"+av.getTitle());
        Label category=new Label("Category :"+av.getCategory());
        Label content=new Label("Content :"+av.getContent());
        
        Label line=new Label("____________________________________________");
        Button remove=new Button("remove");
        //Button update=new Button("update");
        remove.addActionListener(l->{
         AvisService.getInstance().deleteAvis(av.getId()) ;;
        
        });
        
    
        
        
        add(BoxLayout.encloseX(id)) ;
        add(BoxLayout.encloseX(rating)) ;
        add(BoxLayout.encloseX(title)) ;
        add(BoxLayout.encloseX(category)) ;
        add(BoxLayout.encloseX(content)) ;
        //add(BoxLayout.encloseX(remove))
        add(line);
        
                
               
        
        
    }

    

    

    
    
}
