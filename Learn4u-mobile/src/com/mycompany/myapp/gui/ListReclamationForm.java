/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.animations.ComponentAnimation;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author bhk
 */
public class ListReclamationForm extends Form {

    public ListReclamationForm(Form current) {
        setTitle("List tasks");
        current.setScrollableY(true);
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceReclamation.getInstance().getAllTasks().toString());
        add(sp);
        ComponentAnimation anim = current.getToolbar().getTitleComponent().createStyleAnimation("Title", 200);
    current.getAnimationManager().onTitleScrollAnimation(current, anim);
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> current.showBack());
        Form hi = new Form("ToastBarDemo", BoxLayout.y());
         ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("wait...");
  status.setShowProgressIndicator(true);
  status.show();
   status.setExpires(6000);
/*
Button basic = new Button("Basic");
Button progress = new Button("Progress");
Button expires = new Button("Expires");
Button delayed = new Button("Delayed");
hi.add(basic).add(progress).add(expires).add(delayed);

basic.addActionListener(e -> {
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Hello world");
  status.show();
  //...  Some time later you must clear the status
  // status.clear();
});

progress.addActionListener(e -> {
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Hello world");
  status.setShowProgressIndicator(true);
  status.show();
  // ... Some time later you must clear it
});

expires.addActionListener(e -> {
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Hello world");
  status.setExpires(3000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
});

delayed.addActionListener(e -> {
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Hello world");
  status.showDelayed(300); // Wait 300 ms to show the status
  // ... Some time later, clear the status... this may be before it shows at all
});

hi.show();*/
                 
  /*      setLayout(new BorderLayout()); 
    Container contentContainer = new Container(BoxLayout.y());
    contentContainer.setScrollableY(true);

    // add some elements so we have something to scroll
    for (int i = 0; i < 50; i++)
        contentContainer.add(new Label("Entry " + i));

    current.add(BorderLayout.CENTER, contentContainer); // use this line instead of the above for the animation to break

    ComponentAnimation anim = current.getToolbar().getTitleComponent().createStyleAnimation("Title", 200);
    current.getAnimationManager().onTitleScrollAnimation(contentContainer, anim);*/
    }

}
