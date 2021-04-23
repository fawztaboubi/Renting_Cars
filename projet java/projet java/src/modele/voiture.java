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
public class voiture {
    String plaque , modele , marque ,transmisiom;
    float prix;

    public String getPlaque() {
        return plaque;
    }

    public String getModele() {
        return modele;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setTransmisiom(String transmisiom) {
        this.transmisiom = transmisiom;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getMarque() {
        return marque;
    }

    public String getTransmisiom() {
        return transmisiom;
    }

    public float getPrix() {
        return prix;
    }

    public voiture(String plaque, String marque, String modele, String transmisiom, float prix) {
        this.plaque = plaque;
        this.modele = modele;
        this.marque = marque;
        this.transmisiom = transmisiom;
        this.prix = prix;
    }
    
}

