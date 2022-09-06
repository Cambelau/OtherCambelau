/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentable;

import java.time.Period;
import java.time.LocalDate;

/**
 *
 * @author sienn
 */
public class Location {
    private int iD;
    private String adresseRecuperation;
    private int iDVehicule;
    private String dureeLocation;
    private double prix;
    private int iDClient;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    
    public Location(int iD, String adresseRecuperation, int iDVehicule, String dureeLocation, double prix, int iDClient, String dateDebut, String dateFin)
    {
        this.iD = iD;
        this.adresseRecuperation = adresseRecuperation;
        this.iDVehicule = iDVehicule;
        this.dureeLocation = dureeLocation;
        this.prix = prix;
        this.iDClient = iDClient;
        this.dateDebut = LocalDate.parse(dateDebut);
        this.dateFin = LocalDate.parse(dateFin);
    }
    
    public Location()
    {
        this.iD = iD;
        this.adresseRecuperation = "ECE Paris";
        this.iDVehicule = iDVehicule;
        this.dureeLocation = "1 jour";
        this.prix = 50;
        this.iDClient = iDClient;
    }
    
    public int getID()
    {
        return iD;
    }
    
    public String getAdresseRecuperation()
    {
        return adresseRecuperation;
    }    
    
    public int getIDVehicule()
    {
        return iDVehicule;
    }
    
    public String getDureeLocation()
    {
        return dureeLocation;
    }
    
    public double getPrix()
    {
        return prix;
    }
    
    public int getIDClient()
    {
        return iDClient;
    }
    
    public LocalDate getDateDebut()
    {
        return dateDebut;
    }
    
    public LocalDate getDateFin()
    {
        return dateFin;
    }
    
    public void setId(int id)
    {
        this.iD = id;
    }
    
    public long getDuree()
    {
        Period period = Period.between(dateDebut, dateFin);
        int duree = (int)period.getDays();
        return duree;
    }
        
}
