/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import entities.User;
import java.nio.charset.StandardCharsets;
import services.AuthService;
import services.UserService;
import utils.SessionManager;

/**
 *
 * @author MSI GF63
 */
public class NewMain {
    
    public static void main(String[] args)
    { Argon2 argon2jvm = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 16, 32);
        String arg2pwd = argon2jvm.hash(10, 65536, 1,"shahyn");
        System.out.print(arg2pwd);
        if(argon2jvm.verify("$argon2id$v=19$m=65536,t=10,p=1$fjoLNDOI1+ueXMJeYxre6w$0rJFwiUe+BOyYc+ezMjrrIQTTNJm0f0W58jaKebrM+M", "shahyn"))
        {System.out.print("true");
        }else
        {System.out.print("false");
        }
           // Argon2 argon2jvm = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 16, 32);
           // String arg2pwd = argon2jvm.hash(10, 65536, 1, "123456");
          //  System.out.print(arg2pwd);
   // UserService b = new UserService();   
   //  AuthService a= new AuthService();
   //   User k = new User("sadafasadfa@adada.com","chah jday","shasa1", "123456","2000/01/24");
    //    k.setRoles("[ROLE_USER]");
       //   k.setIsbanned(0);
       //  k.setIsVerified(0);
      // if(b.Add(k))
      // {System.out.print("Added");
       // }
     //  else
      //  {
      ///  System.out.print("fail");
      //  }
      // k.setId(26);
     
      // a.delete(k);
        //
       // k.setEmail("shahna@hhhhaaaa.com");
       // k.setBirthday("2000/12/24");
       // k.setIsbanned(1);
       // k.setPassword("2312231");
      //  k.setRoles("[ROLE_ADMIN]");
       // a.Update(k, 27);
        
       // System.out.print(a.GetUsers());
        
        //System.out.print("Login_______________________....");
        //if(a.Login("sadafa@adafa.com","123456"))
        //{
       // System.out.print("Success :"/*+SessionManager.getEmail()+"   "+SessionManager.getUserName()*/ );
        
       // }else
        //{System.out.print("invalid credentials");
        //}
     //   System.out.print(b.SearchUsers("chahyn"));
    }
}
