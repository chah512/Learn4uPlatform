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
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.UserService;


/**
 *
 * @author MSI GF63
 */
public class Sign_upForm extends Form{
    TextField firstname= new TextField("","First Name",40,TextArea.ANY);
    TextField lastname= new TextField("","Last Name",40,TextArea.ANY);    
    TextField email= new TextField("","Email",40,TextArea.EMAILADDR);
    TextField username= new TextField("","Username",40,TextArea.ANY);
    TextField password= new TextField("","Password",20,TextArea.PASSWORD);
    TextField verifypassword= new TextField("","Verify Password",20,TextArea.PASSWORD);
    Label bd=new Label("Birthday");
    Picker birthday = new Picker();
    Button signup=new Button("Sign up");
    Button back=new Button("back");

    public Sign_upForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        getTitleArea().setUIID("Container");
        
       
        //this.add(firstname);
        firstname.getAllStyles().setMargin(LEFT, 0);
        //this.add(lastname);
        lastname.getAllStyles().setMargin(LEFT, 0);
        //this.add(email);
         email.getAllStyles().setMargin(LEFT, 0);
        
        //this.add(username);
        username.getAllStyles().setMargin(LEFT, 0);
        //this.add(password);
        password.getAllStyles().setMargin(LEFT, 0);
        //this.add(verifypassword);
        verifypassword.getAllStyles().setMargin(LEFT, 0);
        //this.add(bd);
        bd.getAllStyles().setMargin(LEFT, 0);
        //this.add(birthday);
        birthday.getAllStyles().setMargin(RIGHT, 0);
        //this.add(signup);
        signup.setUIID("LoginButton");
                 signup.addActionListener(l->{
                             if((firstname.getText()!="")&&(lastname.getText()!="")&&(email.getText()!="")&&(username.getText()!="")&&(password.getText()!="")&&(verifypassword.getText()!=""))
                          {
                              if(password.getText().equals(verifypassword.getText()))
                              {
                            String   fullname=firstname.getText()+" "+lastname.getText();
                            User k=new User(email.getText(),fullname,username.getText(),birthday.getDate(),password.getText());
                         
                           if (UserService.getInstance().SignUp(k))
                           {   Dialog.show("Success","Success,Check your email to complete account verification.You can Login now !", "OK",null); 
                               LoginForm F1 = new LoginForm(theme);
                                 F1.showBack();}
                           else
                               ToastBar.showErrorMessage("error",5);
                            //Dialog.show("Success","Account created",new Command("ok"));
                            //else
                            // Dialog.show("ERROR", "Server error", new Command("OK"));
                          }else{
                                  ToastBar.showErrorMessage("Make sure of your password verification", 5);
                              }}
                              else
                          {
                              ToastBar.showErrorMessage("Fill all blanks",5);
                          }
                         
                 });
        //this.add(back);
              back.addActionListener(l->{
             LoginForm F1 = new LoginForm(theme);
               F1.showBack();
              });
            
              
    
     Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
           Container by = BoxLayout.encloseY(
                
                spaceLabel,
                BorderLayout.center(firstname),
                BorderLayout.center(lastname),
                BorderLayout.center(email),
                BorderLayout.center(username),       
                BorderLayout.center(password),
                BorderLayout.center(verifypassword),
                BorderLayout.center(bd),
                BorderLayout.center(birthday),
                        
                signup,
                back
        );
        add(BorderLayout.CENTER, by);
    
    }
    
      
    
    
}
