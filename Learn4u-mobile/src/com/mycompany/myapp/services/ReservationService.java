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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.entities.User;
import static com.mycompany.myapp.services.UserService.instance;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI GF63
 */
public class ReservationService {
    public ArrayList<Reservation> Reservation;
    public Resources res;
     public static ReservationService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public ReservationService() {
         req = new ConnectionRequest();
    }
    
    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }
    
    public boolean add(Reservation event,int id,String e) {
          
        String url = Statics.BASE_URL+"/mobile/abonnement/add?duree="+event.getDuree()+"&id="+id+"&email="+e; //création de l'URL  TODO
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
    
    
      public ArrayList<Reservation> Allreservs()
    { ArrayList<Reservation> result = new ArrayList<>();
     String url = Statics.BASE_URL+"/mobile/abonnement";
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
                     Reservation re = new Reservation();
                    String id=obj.get("id").toString();
                     
                     String duree = obj.get("Duree").toString();
                     String offre = obj.get("Offre").toString();
                    
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
}
