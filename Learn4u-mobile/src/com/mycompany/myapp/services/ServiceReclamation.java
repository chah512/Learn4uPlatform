/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.location.Geofence;
import com.codename1.location.LocationManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceReclamation {

    public ArrayList<Reclamation> tasks;
    
    public static ServiceReclamation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReclamation() {
         req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean addTask(Reclamation t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
   //    String url = Statics.BASE_URL + "create";
    String url =Statics.BASE_URL+"/backend_reclamation/addReclamationJSON/new?Type="+t.getType()+"&Description="+t.getDescription();
       req.setUrl(url);
        req.setPost(false);
       System.out.println(url);
       
Message m = new Message("Body of message");
//m.getAttachments().put(textAttachmentUri, "text/plain");
//m.getAttachments().put(imageAttachmentUri, "image/png");
Display.getInstance().sendMessage(new String[] {"ayedboukadida@gmail.com"}, "vous avez un nouveau reclamation", m);
   

Message m1 = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
m1.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
boolean success = m.sendMessageViaCloudSync("Codename One", "ayedboukadida@gmail.com", "Name Of User", "Message Subject",
                            "Check out Codename One at https://www.codenameone.com/");

// req.setPost(false);
 //     req.addArgument("Type", t.getType()+"");
   //    req.addArgument("Description", t.getDescription());

       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
              
                  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(resultOK);
        return resultOK;
    }

    public ArrayList<Reclamation> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Reclamation t = new Reclamation();
                  float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               //  t.setType(obj.get("User").toString());
                 
                 if (obj.get("type")==null)
                    t.setType("null");
                else
                 {
                    t.setType(obj.get("type").toString());}
                 
              
              //  t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                if (obj.get("Description")==null)
                     t.setDescription("null");
                else
                    t.setDescription(obj.get("Description").toString());
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Reclamation> getAllTasks(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"/backend_reclamation/AllReclamations";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
        public boolean deleteEvent(Reclamation t) {
        String url = Statics.BASE_URL +"/deleteReclamationJSON/"+t.getId();
//création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener       
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(resultOK);
        return resultOK;
    }
     public boolean updateEvent(Reclamation t) {
       String url =Statics.BASE_URL+"/updateReclamationJSON/"+t.getId();
//création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener       
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(resultOK);
        return resultOK;
    }
}
