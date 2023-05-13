/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.UserService;

/**
 *
 * @author MSI GF63
 */
public class LoginForm extends Form{

    public LoginForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome to, ", "WelcomeWhite"),
                new Label("Learn4u", "WelcomeBlue")
        );
        
        getTitleArea().setUIID("Container");
        
        //Image profilePic = theme.getImage("user-picture.jpg");
        //Image mask = theme.getImage("round-mask.png");
        //profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        //Label profilePicLabel = new Label(profilePic, "ProfilePic");
        //profilePicLabel.setMask(mask.createMask());
        
        TextField email = new TextField("", "Email", 20, TextField.EMAILADDR) ;
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD) ;
        email.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        
        Button loginButton = new Button("LOGIN");
        loginButton.setUIID("LoginButton");
        loginButton.addActionListener(e -> {
            UserService.getInstance().signin(email,password);
        });
        
        Button createNewAccount = new Button("CREATE NEW ACCOUNT");
        createNewAccount.setUIID("CreateNewAccountButton");
        createNewAccount.addActionListener(l->{
         Sign_upForm F1 = new Sign_upForm(theme);
              F1.show();
        });
        Button forgotpwd=new Button("Forgot password ?");
        forgotpwd.setUIID("WelcomeBlue");
        forgotpwd.addActionListener(l->{
            ResetpwdForm f = new ResetpwdForm(theme);
            f.show();
        
        });
        
        // We remove the extra space for low resolution devices so things fit better
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
                BorderLayout.center(email).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount,
                forgotpwd
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
    
}
