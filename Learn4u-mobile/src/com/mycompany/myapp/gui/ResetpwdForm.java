/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.UserService;

/**
 *
 * @author MSI GF63
 */
public class ResetpwdForm extends Form{
       public ResetpwdForm(Resources theme){
       super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
      
       Container welcome = FlowLayout.encloseCenter(
                new Label("write your, ", "WelcomeWhite"),
                new Label("Email", "WelcomeBlue")
        );
       
       TextField email = new TextField("", "Email", 20, TextField.EMAILADDR) ;
       Button submit=new Button("send reset Link");
       submit.setUIID("LoginButton");
        submit.addActionListener(l->{
            
           if(UserService.getInstance().resetpwd(email))
           {  Dialog.show("Success","Check your email, to follow Reset instructions", "OK",null); 
              LoginForm a=new LoginForm(theme);
              a.showBack();
           }
           });
       Button cancel=new Button("cancel");
       cancel.addActionListener(l->{
       LoginForm a=new LoginForm(theme);
       a.showBack();
       });
       
         Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        Container by = BoxLayout.encloseY(
                welcome,
                //profilePicLabel,
                spaceLabel,
                email,
                submit,
                cancel
                
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
}
}
