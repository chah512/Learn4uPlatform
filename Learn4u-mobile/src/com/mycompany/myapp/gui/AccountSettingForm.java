/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
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
public class AccountSettingForm extends SideMenuBaseForm {
         Label pwd=new Label("Change Password","WelcomeBlue");
         TextField password= new TextField(""," New Password",20,TextArea.PASSWORD);
         TextField verifypassword= new TextField("","Verify Password",20,TextArea.PASSWORD);
         Button submit=new Button("Change");
         TextField firstname= new TextField("","First Name",40,TextArea.ANY);
         TextField lastname= new TextField("","Last Name",40,TextArea.ANY); 
         Button submitfullname=new Button("change");
         Label fullname=new Label("Full Name","WelcomeBlue");
         Label pic=new Label("Profile pictrue","WelcomeBlue");
         Button profilepic=new Button("Upload");
         Button submitpic=new Button("Save");
         Label profilePicLabel = new Label();
         
    public AccountSettingForm(Resources theme){
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
                                new Label(SessionManager.getEmail(), "WelcomeBlue"),
                                new Label("Setting", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
             setupSideMenu(theme);  
         
         submit.setUIID("LoginButton");
         submit.addActionListener(l->{
         if(password.getText().equals(verifypassword.getText())&&(password.getText().length()>0))
                 {
         if(UserService.getInstance().Updatepwd(password.getText(),SessionManager.getId()))
                 {
                 Dialog.show("Success", "Password changed", "OK", null);
                 //todo redirection
                 }
         }else
             Dialog.show("Fail","please fill correctly the passwords boxes","OK",null);
         });
         submitfullname.setUIID("LoginButton");
         submitfullname.addActionListener(l->{
         if((firstname.getText().length()>0)&&(lastname.getText().length()>0))
                 {
         if(UserService.getInstance().Updatefullname(firstname.getText(),lastname.getText(),SessionManager.getId()))
                 {
                 Dialog.show("Success", "full name changed", "OK", null);
                 //todo redirection
                 }
         }else
             Dialog.show("Fail","please fill first name and last name boxes","OK",null);
         });
        
         profilepic.addActionListener(l->{
         String path=Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
             if(path!= null)
                 {
                 try{
                     Image img=Image.createImage(path);
                     Image mask = theme.getImage("round-mask.png");
                     img=img.fill(mask.getWidth(), mask.getHeight());
                     
                      profilePicLabel = new Label(img, "ProfilePic");
                     profilePicLabel.setMask(mask.createMask());
                     this.add(profilePicLabel);
                     this.add(submitpic);
                     
                     this.revalidate();
                 }
                 catch(Exception ex)
                 {
                     ex.printStackTrace();
                 }
                 
                 }
         });
         submitpic.addActionListener(l->{
              if(UserService.getInstance().Updatephoto(profilePicLabel.getIcon(),SessionManager.getId()))
                 {
                 Dialog.show("Success", "Profile Photo changed", "OK", null);
                 //todo redirection
                 }
                    else
                   Dialog.show("Fail","please upload a photo","OK",null);
            });
             
         add(pwd);
         add(password);
         add(verifypassword);
         add(submit);
         add(new Label("__________________________________"));
         add(fullname);
         add(firstname);
         add(lastname);
         add(submitfullname);
         add(new Label("__________________________________"));
         add(pic);
         add(profilepic);
         
       }
    @Override
      protected void showOtherForm(Resources res) {
          new UserListAdmin(res).show();
    }
    
}
