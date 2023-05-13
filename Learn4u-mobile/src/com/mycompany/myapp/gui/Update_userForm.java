/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.UserService;
import java.util.ArrayList;

/**
 *
 * @author MSI GF63
 */
public class Update_userForm extends SideMenuBaseForm {
    TextField firstname= new TextField("","First Name",40,TextArea.ANY);
    TextField lastname= new TextField("","Last Name",40,TextArea.ANY);    
    TextField email= new TextField("","Email",40,TextArea.EMAILADDR);
    TextField username= new TextField("","Username",40,TextArea.ANY);
    TextField password= new TextField("","Password",20,TextArea.PASSWORD);
    TextField verifypassword= new TextField("","Verify Password",20,TextArea.PASSWORD);
    ComboBox<String> role= new ComboBox<String>("ROLE_USER","ROLE_ADMIN","ROLE_FORMATEUR");
    Label bd=new Label("Birthday");
    Picker birthday = new Picker();
    Button signup=new Button("Update");
    Button back=new Button("cancel");
    public Update_userForm(Resources theme , int id)
    {
         super("Users List",BoxLayout.y());
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
                                new Label("  User ", "WelcomeBlue"),
                                new Label("Update", "WelcomeWhite")
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
                             if((firstname.getText()!="")&&(lastname.getText()!="")&&(email.getText()!="")&&(username.getText()!="")&&(password.getText()!="")&&(verifypassword.getText()!=""))
                          {
                              if(password.getText().equals(verifypassword.getText()))
                              {
                            String   fullname=firstname.getText()+" "+lastname.getText();
                            User k=new User(email.getText(),fullname,username.getText(),birthday.getDate(),password.getText());
                            k.setRoles(role.getSelectedItem());
                         
                           if (UserService.getInstance().Update(k,id))
                           {   Dialog.show("Success","You have successfully updated ", "OK",null); 
                               UserListAdmin a= new UserListAdmin(theme);
                                a.show();
                           }
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
                 
                 back.addActionListener(l->{
                UserListAdmin a= new UserListAdmin(theme);
                a.show();
                 });
                 
                 Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
               
                
                add(spaceLabel);
                add(firstname);
                add(lastname);
               add(email);
                add(username);      
                add(password);
                add(verifypassword);
                add(bd);
                add(birthday);
                add(role);        
                add(signup);
                add(back);
       
    }

    @Override
    protected void showOtherForm(Resources res) 
    {
                 new Choice_Form(res).show();    
    }
 }
