/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentable.DAO;

import static rentable.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static rentable.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rentable.Employes;
/**
 *
 * @author sienn
 */
public class EmployeDAOImpl implements EmployeDAO{
    
   private static final String SQL_SELECT_PAR_LOGIN_ET_MDP = "select * from employer where login = ? and password = ?";
    
    private DAOFactory          daoFactory;

    public EmployeDAOImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
   @Override
    public Employes trouver(String login, String mdp) throws DAOException
    {
        return trouver(SQL_SELECT_PAR_LOGIN_ET_MDP, login, mdp);
    }
    
    
    private Employes trouver(String sql, Object... objets) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employes employes = null;

        try {
            /* RÃ©cupÃ©ration d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * PrÃ©paration de la requÃªte avec les objets passÃ©s en arguments
             * (ici, uniquement une adresse email) et exÃ©cution.
             */
            preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnÃ©es retournÃ©e dans le ResultSet */
            if (resultSet.next()) {
                employes = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return employes;
     }
     private static Employes map(ResultSet resultSet) throws SQLException {
         //(int iD, String nom, String prenom, String obj, String login, String mdp
        Employes employes = new Employes((int) resultSet.getLong("ID"), resultSet.getString("Nom"), resultSet.getString("Prenom"), resultSet.getString("Adresse"), resultSet.getString("Login"), resultSet.getString("Password"));
        return employes;
    }
   
}
