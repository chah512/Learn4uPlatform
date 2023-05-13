/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.regex.Pattern;

/**
 *
 * @author Ahmed
 */
public class DataValidationUtils {
    
    
    public static boolean isNomValid(String nom) {return Pattern.matches("^[a-zA-Z]{3,}$", nom);}

   // public static boolean isProductNameValid(String description) {return Pattern.matches("^[a-zA-Z]{3,}$", description);}

   
    public static boolean isDescriptionValid(String description) {return Pattern.matches("^[a-zA-Z]{3,}$", description);}


    public static boolean price(String prix) {
        return Pattern.matches("^[0-9]{1,}$", prix);
    }

    

}
    

