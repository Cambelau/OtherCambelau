/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentable.DAO;

import rentable.Voiture;
import java.util.ArrayList;
/**
 *
 * @author sienn
 */
public interface VoitureDAO{
    
    void creer(Voiture v) throws DAOException;
    Voiture trouver(int id) throws DAOException;
    ArrayList<Voiture> listeVoiture () throws DAOException;
    void delete (int id) throws DAOException;
    public void setdipo(int id, int dispo) throws DAOException;
    
}
