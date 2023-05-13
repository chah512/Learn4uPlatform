/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author MSI GF63
 */
public class Choice_Form extends Form{
    public Choice_Form(Resources theme){
                super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome, ", "WelcomeWhite"),
                new Label("Select your Activity", "WelcomeBlue")
        );
        Button Administration = new Button("Administration");
        Administration.setUIID("LoginButton");
        Administration.addActionListener(l->{
            if(SessionManager.getRoles().contains("ROLE_ADMIN"))
            {
        UserListAdmin a= new UserListAdmin(theme);
        a.show();
            }else
                Dialog.show("Access Denied", "You don't have administration priviledge", "OK",null);
           
        });
        
        
         // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        Button aaa=new Button("normal usage");
        aaa.setUIID("LoginButton");
        aaa.addActionListener(l->{
        new EventFront(theme).show();
        });
        Container buttons = BoxLayout.encloseY(
                Administration,
                aaa
                
        
        );
        
        Container by = BoxLayout.encloseY(
             welcome,
             spaceLabel,
             buttons   
        );
        add(BorderLayout.CENTER, by);
          // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
        
    
    
    }
}
