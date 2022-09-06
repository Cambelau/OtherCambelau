/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentable;

/**
 *
 * @author sienn
 */
public class Personne {
    protected int iD;
    protected String nom;
    protected String prenom;
    protected String adresse;
    protected String login;
    protected String mdp;
    
    
    public Personne(int iD, String nom, String prenom, String obj, String login, String mdp)
    {
        this.iD = iD;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = obj;
        this.login = login;
        this.mdp = mdp;
    }
    
    public Personne()
    {
        this.iD = iD;
        this.nom = "DEFAULT";
        this.prenom = "";
        this.adresse = "";
        this.login = "root";
        this.mdp = "";
    }
    
    public int getID()
    {
        return iD;
    }
    
    public String getNom()
    {
        return nom;
    }
    
    public String getPrenom()
    {
        return prenom;
    }
    
    public String getAdresse()
    {
        return adresse;
    }
    
    public String getLogin()
    {
        return login;
    }
    
    public String getMDP()
    {
        return mdp;
    }
    
    public void setID(int ID)
    {
        this.iD = iD;
    }
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }
    
    public void setLogin(String login)
    {
        this.login = login;
    }
    
    public void setMDP(String mdp)
    {
        this.mdp = mdp;
    }
    
    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }
}
