/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentable;

import java.util.Comparator;

/**
 *
 * @author sienn
 */
public class Voiture {
    protected int iD;
    protected double prix;
    protected int nombrePassager;
    protected double emissionCO2;
    protected int disponibilite;
    protected int nombreBaggages;
    protected double masse;
    protected double volumeTotal;
    protected double taille;
    protected String option;
    protected int nombrePortes;
    protected String boiteDeVitesse;
    protected String energie;
    protected String nom;
    protected String image;
    
    public Voiture(int iD, double prix, int nombrePassager, double emissionCO2, int disponibilite, int nombreBaggage, double masse, double volumeTotal, double taille, String option, int nombrePortes, String boiteDeVitesse, String energie, String nom, String image)
    {
        this.iD = iD;
        this.prix = prix;
        this.nombrePassager = nombrePassager;
        this.emissionCO2 = emissionCO2;
        this.disponibilite = disponibilite;
        this.nombreBaggages = nombreBaggage; 
        this.masse = masse;
        this.volumeTotal = volumeTotal;
        this.taille = taille;
        this.option = option;
        this.nombrePortes = nombrePortes;
        this.boiteDeVitesse = boiteDeVitesse;
        this.energie = energie;
        this.nom = nom;
        this.image = image;
    }
    
    public Voiture()
    {
        this.iD = iD;
        this.prix = 50;
        this.nombrePassager = 4;
        this.emissionCO2 = 10;
        this.disponibilite = 1;
        this.nombreBaggages = 4; 
        this.masse = 200;
        this.volumeTotal = 100;
        this.taille = 300;
        this.option = "Camera de recul";
        this.nombrePortes = 4;
        this.boiteDeVitesse = "Automatique";
        this.energie = "Electrique"; 
        this.nom = "Multiplat";
        this.image = "A";
    }
    
    public int getID()
    {
        return iD;
    }
    
    public double getPrix()
    {
        return prix;
    }
    
    public int getNombrePassager()
    {
        return nombrePassager;
    }
    
    public double getEmissionCO2()
    {
        return emissionCO2;
    }
    
    public int getDisponibilite()
    {
        return disponibilite;
    }
    
    public int getNombreBaggages()
    {
        return nombreBaggages;
    }
    
    public double getMasse()
    {
        return masse;
    }
    
    public double getVolumeTotal()
    {
        return volumeTotal;
    }
    
    public double getTaille()
    {
        return taille;
    }
    
    public String getOption()
    {
        return option;
    }
    
    public int getNombrePortes()
    {
        return nombrePortes;
    }
    
    public String getBoiteDeVitesse()
    {
        return boiteDeVitesse;
    }
    
    public String getEnergie()
    {
        return energie;
    }
    
    public String getNom()
    {
        return nom;
    }
    
    public String getImage()
    {
        return image;
    }
    public static Comparator<Voiture> ComparatorNom = new Comparator<Voiture>() {
      
        @Override
        public int compare(Voiture e1, Voiture e2) {
            return e1.getNom().compareTo(e2.getNom());
        }
    };

 public static Comparator<Voiture> ComparatorPrix = new Comparator<Voiture>() {
     
        @Override
        public int compare(Voiture e1, Voiture e2) {
            return (int) (e1.getPrix()- e2.getPrix());
        }
    };

public static Comparator<Voiture> ComparatorPassager = new Comparator<Voiture>() {
     
        @Override
        public int compare(Voiture e1, Voiture e2) {
            return (int) (e1.getNombrePassager()- e2.getNombrePassager());
        }
    };
}