/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author wafa
 */
public class Profil {
    private static String passe ="";
    private static String nom ="";

    public Profil(String passe, String nom) {
        this.passe = passe;
        this.nom = nom;
    }

    public static void setId(String passe) {
        Profil.passe = passe;
    }

    public static void setNom(String nom) {
        Profil.nom = nom;
    }

    public static String getId() {
        return passe;
    }

    public static String getNom() {
        return nom;
    }

  

   
    
    
}
