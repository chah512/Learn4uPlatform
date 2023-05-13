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
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.UserService;
import java.util.ArrayList;

/**
 *
 * @author MSI GF63
 */
public class UserListAdmin extends SideMenuBaseForm{

    public UserListAdmin(Resources theme) {
            super("Users List",BoxLayout.y());
            ArrayList<User> List = new ArrayList<User>();
            List=UserService.getInstance().Allusers();
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
                                new Label("  Users", "WelcomeBlue"),
                                new Label("List", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
             setupSideMenu(theme);        
     
    
    
    for(User usr : List){
        
        addButton(usr,theme);

    }
   
    
    } 
   
    
    
    
    
    @Override
    protected void showOtherForm(Resources theme) {
             new Choice_Form(theme).show();
    }

    private void addButton( User usr,Resources theme) {
        Label Id=new Label("Id :"+usr.getId());
        Label username=new Label("Username :"+usr.getUsername());
        Label fullname=new Label("Fullname :"+usr.getFullname());
        Label email=new Label("Email :"+usr.getEmail());
        Label roles=new Label("Roles :"+usr.getRoles());
        Label isbanned=new Label("IsBanned? :"+usr.getIsbanned());
        Label isverified=new Label("IsVerified? :"+usr.getIsVerified());
        Label line=new Label("____________________________________________");
        //Button remove=new Button("remove");
        Button update=new Button("update");
        update.addActionListener(l->{
        Update_userForm a=new Update_userForm(theme,usr.getId());
        a.show();
        });
        Button ban=new Button("ban");
              ban.addActionListener(l->{
              if(UserService.getInstance().ban(usr.getId()))
                      {
                          Dialog.show("Banned","you have banned :"+usr.getUsername(), "OK",null);
                          UserListAdmin a=new UserListAdmin(theme);
                          a.show();
                      }
              });
        Button unban=new Button("unban");
            unban.addActionListener(l->{
              if(UserService.getInstance().unban(usr.getId()))
                      {
                          Dialog.show("UnBanned","you have Unbanned :"+usr.getUsername(), "OK",null);
                          UserListAdmin a=new UserListAdmin(theme);
                          a.show();
                      }
              });
        
        
        
        add(BoxLayout.encloseX(Id)) ;
        add(BoxLayout.encloseX(username)) ;
        add(BoxLayout.encloseX(fullname)) ;
        add(BoxLayout.encloseX(email)) ;
        add(BoxLayout.encloseX(roles)) ;
        add(BoxLayout.encloseX(isbanned)) ;
        if(usr.getIsbanned().equals("false"))
        {
        add(BoxLayout.encloseX(isverified,update,ban)) ; //todo add remove
        }else
        {
        add(BoxLayout.encloseX(isverified,update,unban)) ; //todo add remove

        }   
        add(line);
        
                
               
        
        
    }

    

    

    
    
}
