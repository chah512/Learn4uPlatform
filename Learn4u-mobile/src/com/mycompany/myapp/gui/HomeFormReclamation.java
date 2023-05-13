/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeFormReclamation extends Form{
Form current;
    public HomeFormReclamation() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Reclamation");
        Button btnListTasks = new Button("List Reclamation");
        
        btnAddTask.addActionListener(e-> new AddReclamationForm(current).show());
        btnListTasks.addActionListener(e-> new ListReclamationForm(current).show());
        addAll(btnAddTask,btnListTasks);
        
        
    }
    
    
}
