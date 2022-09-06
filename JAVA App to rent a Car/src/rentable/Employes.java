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
public class Employes extends Personne{
    
    public Employes(int iD, String nom, String prenom, String obj, String login, String mdp)
    {
        super(iD, nom, prenom, obj, login, mdp);
    }
    
    public Employes()
    {
        super();
    }
}
