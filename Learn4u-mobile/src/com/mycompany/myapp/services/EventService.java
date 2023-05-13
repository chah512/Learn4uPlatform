/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.InfiniteProgress;
import com.mycompany.myapp.gui.SessionManager;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.Choice_Form;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author MSI GF63
 */
public class EventService {

    public static EventService instance = null;
    public boolean resultCode;
    private ConnectionRequest cr;
    private ArrayList<Event> listOffres;

    private EventService() {
        cr = new ConnectionRequest();
    }

    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    public ArrayList<Event> getAll() {
        listOffres = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/mobile/offre");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listOffres = getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOffres;
    }

    private ArrayList<Event> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
                    
            ));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Event offre = new Event(
                        (int) Float.parseFloat(obj.get("id").toString()),
                        (String) obj.get("titre"),
                        (String) obj.get("description"),
                         obj.get("prix").toString(),
                        (String) obj.get("image")
                );

                listOffres.add(offre);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listOffres;
    }

    public boolean add(Event event) {
          DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String url = Statics.BASE_URL+"/mobile/offre/add?titre="+event.getTitre()+"&description="+event.getDescription()+"&prix="+event.getPrix(); //création de l'URL  TODO
        System.out.println(url);
       cr.setUrl(url);// Insertion de l'URL de notre demande de connexion
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode() == 200; //Code HTTP 200 OK
                cr.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cr);
        return resultCode;
    }

    public boolean edit(Event event, boolean imageEdited) {
        return manage(event, true, imageEdited);
    }

    public boolean manage(Event event, boolean isEdit, boolean imageEdited) {
        MultipartRequest cr = new MultipartRequest();
        cr.setHttpMethod("POST");
        cr.setFilename("file", "Offre.jpg");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/mobile/offre/edit");
            cr.addArgumentNoEncoding("id", String.valueOf(event.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/mobile/offre/add");
           
        }

        cr.addArgumentNoEncoding("titre", event.getTitre());
        cr.addArgumentNoEncoding("description", event.getDescription());
        cr.addArgumentNoEncoding("prix", String.valueOf(event.getPrix()));

        if (imageEdited) {
            try {
                cr.addData("file", event.getImage(), "image/jpeg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            cr.addArgument("image", event.getImage());
        }

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode()==200;
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    public int delete(int eventId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/mobile/offre/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(eventId));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }
}
