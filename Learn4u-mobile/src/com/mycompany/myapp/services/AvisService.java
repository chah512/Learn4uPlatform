/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;



import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.gui.addavisForm;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author med
 */
public class AvisService {
   
    public ArrayList<Avis> Aviss;
    public Resources res;
     public static AvisService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public AvisService() {
         req = new ConnectionRequest();
    }

    public static AvisService getInstance() {
        if (instance == null) {
            instance = new AvisService();
        }
        return instance;
    }
    
    
    public ArrayList<Avis> Allavis()
    { ArrayList<Avis> result = new ArrayList<>();
     String url = Statics.BASE_URL+"/avis/Allavis";
     req.setUrl(url);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
         @Override
         public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp ;
                jsonp = new JSONParser();
                 try {
                    Map<String,Object>users = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) users.get("root");
                    
                     for(Map<String,Object> obj : listOfMaps){
                     Avis re = new Avis();
                     Float id = Float.parseFloat(obj.get("id").toString());
                     String rating = obj.get("rating").toString();
                     String title = obj.get("title").toString();
                     String category = obj.get("category").toString();
                     String content= obj.get("content").toString();
                     
                     re.setRating(rating);
                     re.setTitle(title);
                     re.setId(id.intValue());
                     re.setCategory(category);
                     re.setContent(content);
                     
                     result.add(re);
                     

                     }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
         }
     });
             NetworkManager.getInstance().addToQueueAndWait(req);
             return result;
    }

   public boolean addA(Avis t) {
      // DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String url = Statics.BASE_URL+"/avis/addavisJSON/new?rating="+t.getRating()+"&title="+t.getTitle()+"&category="+t.getCategory()+"&content="+t.getContent(); //création de l'URL  TODO
        System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);     
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    /*  public void deleteAvis(int id) {
              String url = Statics.BASE_URL + "/deleteAvisJSON?id=" +id;
        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            String str = new String(req.getResponseData());
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }*/
     
      public int deleteAvis(int id) {
         String url = Statics.BASE_URL + "/avis/deleteAvisJSON?id=" +id;
           System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });

        try {
            req.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return req.getResponseCode();
    }
      
}
