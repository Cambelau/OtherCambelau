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
import static rentable.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static rentable.DAO.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import rentable.Client;

public class ClientDaoImpl implements ClientDao {

    private static final String SQL_SELECT_PAR_LOGIN_MDP_ADRESSE = "select * from client where login = ? and password = ? and adresse = ? " ;
    private static final String SQL_SELECT_PAR_LOGIN_ET_MDP = "select * from client where login = ? and password = ?";
    private static final String SQL_SELECT_PAR_ID = "select * from client where id = ?";
    private static final String SQL_SELECT_ID = "select * from client where id = ?";
    private static final String SQL_UPDATE_MDP = "update client set password = ? where login = ?";
    private static final String SQL_DELETE_CLIENT = "delete from client where ID = ?";
    private static final String SQL_INSERT = "INSERT INTO client (Nom,Prenom,Adresse,Login,Password,Membre,Coordonnees,Type,Paiement) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL = "select * from client";


    private DAOFactory daoFactory;

    public ClientDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
        
    public Client rechercheLogin(String login) throws DAOException
    {
        return trouver(SQL_SELECT_ID, login);
    }
    
    public void changerMDP(String mdp, String login) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            /* RÃ©cupÃ©ration d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * PrÃ©paration de la requÃªte avec les objets passÃ©s en arguments
             * (ici, uniquement une adresse email) et exÃ©cution.
             */
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE_MDP, false, mdp, login);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        } 
    }

    /* ImplÃ©mentation de la mÃ©thode dÃ©finie dans l'interface UtilisateurDao */
    @Override
    public Client trouver(String login, String mdp, String adresse) throws DAOException {
        return recherche(SQL_SELECT_PAR_LOGIN_MDP_ADRESSE, login, mdp, adresse);
    }
    
    @Override
    public Client trouver(String login, String mdp) throws DAOException
    {
        return recherche(SQL_SELECT_PAR_LOGIN_ET_MDP, login, mdp);
    }
    
    @Override
    public Client trouver(int id) throws DAOException
    {
        return recherche(SQL_SELECT_PAR_ID, id);
    }
    
   
    public void creer(Client client) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, client.getNom(), client.getPrenom(), client.getAdresse(), client.getLogin(), client.getMDP(), client.getMembre(), client.getTelephone(), client.getStatut(), client.getPaiement());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Ã‰chec de la crÃ©ation de l'utilisateur, aucune ligne ajoutÃ©e dans la table.");
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                // client.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException("Ã‰chec de la crÃ©ation de l'utilisateur en base, aucun ID auto-gÃ©nÃ©rÃ© retournÃ©.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    /*
     * MÃ©thode gÃ©nÃ©rique utilisÃ©e pour retourner un utilisateur depuis la base
     * de donnÃ©es, correspondant Ã  la requÃªte SQL donnÃ©e prenant en paramÃ¨tres
     * les objets passÃ©s en argument.
     */
    private Client recherche(String sql, Object... objets) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Client client = null;

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
                client = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return client;
    }

    @Override
    public ArrayList<Client> listeclient() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Client> listclient = new ArrayList<>();

        try {
            /* RÃ©cupÃ©ration d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * PrÃ©paration de la requÃªte avec les objets passÃ©s en arguments
             * (ici, uniquement une adresse email) et exÃ©cution.
             */
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnÃ©es retournÃ©e dans le ResultSet */
            while (resultSet.next()) {
                
                listclient.add(map(resultSet));
                
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return listclient;
    }
    
    @Override
    public void delete(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            /* RÃ©cupÃ©ration d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * PrÃ©paration de la requÃªte avec les objets passÃ©s en arguments
             * (ici, uniquement une adresse email) et exÃ©cution.
             */
            preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE_CLIENT, false,id);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        
        
    }


    /*
     * Simple mÃ©thode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */
    private static Client map(ResultSet resultSet) throws SQLException {
        Client client = new Client((int) resultSet.getLong("ID"), resultSet.getString("Nom"), resultSet.getString("Prenom"), resultSet.getString("Adresse"), resultSet.getString("Login"), resultSet.getString("Password"), resultSet.getInt("Membre"), resultSet.getInt("Type"), resultSet.getString("Coordonnees"), resultSet.getString("Paiement"));
        return client;
    }



}

