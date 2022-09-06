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
public class Client extends Personne{
    protected Integer membre;
    //protected double discount;
    protected Integer statut; //Individual/Business
    protected String telephone;
    protected String paiement;
    
    /**
     * 
     * 
     * Creation d'un Client
     */
    public Client(int iD, String nom, String prenom, String obj, String login, String mdp, Integer membre, Integer statut, String telephone, String paiement)//double discount,
    {
        super(iD, nom, prenom, obj, login, mdp);
        this.iD = iD;
        this.membre = membre; 
        //this.discount = discount;
        this.statut = statut;
        this.telephone = telephone;
        this.paiement = paiement;
        
    }
    
    public Client()
    {
        super();
        this.iD = iD;
        this.membre = 1; 
        //this.discount = 0;
        this.statut = 1;
        this.telephone = "0610111213";
        this.paiement = "CB";  
    }
    
    public Integer getMembre()
    {
        return membre;
    }
    
    /*public double getDiscount()
    {
        return discount;
    }*/
    
    public Integer getStatut()
    {
        return statut;
    }
    
    public String getTelephone()
    {
        return telephone;
    }
    
    public String getPaiement()
    {
        return paiement;
    }
    
    public void setStatut(int statut)
    {
        this.statut = statut;
    }
    
    public void setTelephone(String tel)
    {
        this.telephone = tel;
    }
    
    public void setPaiement(String paiement)
    {
        this.paiement = paiement;
    }
}
