/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentable.DAO;

import rentable.Employes;

/**
 *
 * @author sienn
 */
public interface EmployeDAO {
    
    Employes trouver(String login, String mdp) throws DAOException;
    
}
