/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author MSI GF63
 */
public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources theme) {
//        Image profilePic = theme.getImage("user-picture.jpg");
  //      Image mask = theme.getImage("round-mask.png");
    //   mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
      //  profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        //Label profilePicLabel = new Label("  TO DO", profilePic, "SideMenuTitle");
        //profilePicLabel.setMask(mask.createMask());
        Label email=new Label(SessionManager.getEmail());
        Label username=new Label(SessionManager.getUserName());
       email.setUIID("WelcomeWhite");
       username.setUIID("WelcomeWhite");
       Container sidemenuTop =  BoxLayout.encloseY(
               email,
               username
      );
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Dashboard", FontImage.MATERIAL_DASHBOARD,  e -> showOtherForm(theme));
        getToolbar().addMaterialCommandToSideMenu("  Reclamation", FontImage.MATERIAL_TRENDING_UP, e -> {
                                                  AddReclamationForm a= new AddReclamationForm(this);
                                                  a.show();
        });
        getToolbar().addMaterialCommandToSideMenu("  Avis", FontImage.MATERIAL_ACCESS_TIME,  e -> {
                                                  avisListForm a=new avisListForm(theme);
                                                  a.show();
        });
        getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS,  e -> {
                                                  AccountSettingForm a=new AccountSettingForm(theme);
                                                  a.show();
        });
        getToolbar().addMaterialCommandToSideMenu("  Rate our app", FontImage.MATERIAL_TRENDING_UP,   e -> {
                                                  addavisForm a=new addavisForm(theme);
                                                  a.show();
        });
        if(SessionManager.getRoles().contains("ROLE_ADMIN"))
        {getToolbar().addMaterialCommandToSideMenu("  List Reclamation", FontImage.MATERIAL_TRENDING_UP, e -> {
                                                  ListReclamationForm a= new ListReclamationForm(this);
                                                  a.show();
        });
        }
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> {
            SessionManager.pref.clearAll();//hethi bech tfara8 session bech donnés ta3 l user y93douch enregistrer ba3d logout
            new LoginForm(theme).show();});
    }
    
    protected abstract void showOtherForm(Resources res);
}
