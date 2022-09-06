/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentable.DAO;

/**
 *
 * @author etienne lionnet
 */
import rentable.Client;
import java.util.ArrayList;
        
public interface ClientDao {
    
    void creer( Client client ) throws DAOException;

    Client trouver( String login, String mdp, String adresse ) throws DAOException;
    Client trouver(String login, String mdp) throws DAOException;
    Client trouver(int id) throws DAOException;
    void changerMDP(String mdp, String login) throws DAOException;
    
    
    
    //void supprimer(int id) throws DAOException;
    
    
    ArrayList<Client> listeclient () throws DAOException;
    
    void delete (int id) throws DAOException;
    
    
}
