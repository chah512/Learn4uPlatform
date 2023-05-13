/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author bhk
 */
public class AddReclamationForm extends Form{

    public AddReclamationForm(Form previous) {
       
        setTitle("Add a new task");
        setLayout(BoxLayout.y());
         ComboBox type = new ComboBox();
        type.addItem("probleme_technique");
        type.addItem("problemede_paiment");
        type.addItem("Autres");
      //   names.addItem("Bassem HTIRA");
      
     //  previous.add(type);
       System.out.println( type.getSelectedItem().toString());
        TextField tfType = new TextField("","ReclamationType");
        TextField tfDescription= new TextField("", "Description: 0 - 1");
        Button btnValider = new Button("Add task");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((type.getSelectedItem().toString().length()==0)||(tfDescription.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", "Ok",null);
                else
                {
                    try {
                        Reclamation t = new Reclamation( type.getSelectedItem().toString(),tfDescription.getText());
                      
                        if( ServiceReclamation.getInstance().addTask(t))
                        {
                           Dialog.show("Success","Connection accepted","OK",null);
                        }else
                            Dialog.show("ERROR", "Server error","OK",null);
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", "Ok",null);
                    }
                    
                }
                
                
            }
        });
        
        addAll(type,tfDescription,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    
    
    }
    
    
}
