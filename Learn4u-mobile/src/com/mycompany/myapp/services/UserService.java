/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.gui.SessionManager;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
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
public class UserService  {
    public ArrayList<User> users;
    public Resources res;
     public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public UserService() {
         req = new ConnectionRequest();
    }
    
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
     public boolean SignUp(User t) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String url = Statics.BASE_URL+"/registerJSON?email="+t.getEmail()+"&fullname="+t.getFullname()+"&username="+t.getUsername()+"&birthday="+formatter.format(t.getBirthday())+"&plainPassword="+t.getPassword(); //création de l'URL  TODO
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
    public void signin(TextField email,TextField password)
    {
        String url = Statics.BASE_URL+"/loginJSON?email="+email.getText()+"&password="+password.getText();
     req.setUrl(url);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser j= new JSONParser();
                String json = new String(req.getResponseData())+""; 
                try {
                if(json.equals("failed")||json.equals("password not found"))
                { Dialog.show("Login failed","email or password incorrect ", "OK",null);
                }
                else{
                        System.out.println("data =="+json);
                        Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        
                //Session 
                Float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setFullname(user.get("fullname").toString());
               SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setUserName(user.get("Username").toString());
                SessionManager.setId(id.intValue());
                
                SessionManager.setEmail(user.get("email").toString());
                SessionManager.setRoles(user.get("roles").toString());
                  
                     System.out.println(SessionManager.getId());
                     System.out.println(SessionManager.getEmail());
                //photo 
                
                if(user.get("photo") != null)
                    SessionManager.setPhoto(user.get("photo").toString());
                       if(user.size()>0)
                       { if(user.get("banned").toString().equals("false")){
                           Form B = new Choice_Form(res);
                           B.show();
                       }else
                           Dialog.show("Your Account is Blocked ","Contact us for more informations ", "OK",null);
                       }
                } 
                }
                catch (IOException ex) {
                        ex.printStackTrace();
                    }
                req.removeResponseListener(this);
            }
      });
             NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public boolean resetpwd(TextField email) {
        
        String url = Statics.BASE_URL+"/reset-password/JSON?email="+email.getText(); //création de l'URL  TODO
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
    
    public ArrayList<User> Allusers()
    { ArrayList<User> result = new ArrayList<>();
     String url = Statics.BASE_URL+"/Userjson";
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
                     User re = new User();
                     Float id = Float.parseFloat(obj.get("id").toString());
                     String username = obj.get("Username").toString();
                     String fullname = obj.get("fullname").toString();
                     String email = obj.get("email").toString();
                     String roles= obj.get("roles").toString();
                     String Isbanned = obj.get("IsBanned").toString();
                     String Isveified = obj.get("isVerified").toString();
                     re.setEmail(email);
                     re.setFullname(fullname);
                     re.setId(id.intValue());
                     re.setRoles(roles);
                     re.setIsbanned(Isbanned);
                     re.setUsername(username);
                     re.setIsVerified(Isveified);
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
    public boolean ban(int id) {
        
        String url = Statics.BASE_URL+"/banjson?id="+id; 
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public boolean unban(int id) {
        
        String url = Statics.BASE_URL+"/unbanjson?id="+id; //création de l'URL  TODO
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     public boolean Update(User t,int id) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String url = Statics.BASE_URL+"/editJSON?id="+id+"&email="+t.getEmail()+"&fullname="+t.getFullname()+"&username="+t.getUsername()+"&birthday="+formatter.format(t.getBirthday())+"&plainPassword="+t.getPassword()+"&role="+t.getRoles(); //création de l'URL  TODO
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public boolean Updatepwd(String a,int id) {
        String url = Statics.BASE_URL+"/editpassword?id="+id+"&Password="+a;
        System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean Updatefullname(String a,String b,int id) {
        String url = Statics.BASE_URL+"/editfullname?id="+id+"&Fullname="+a+" "+b;
        System.out.println(url); 
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    } 
     public boolean Updatephoto(Image a,int id) {
        String url = Statics.BASE_URL+"/editphoto?id="+id+"&Photo="+a;
        System.out.println(url); 
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    } 
    
}
