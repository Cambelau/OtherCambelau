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
import java.util.ArrayList;

import rentable.Voiture;


/**
 *
 * @author sienn
 */
public class VoitureDAOImpl implements VoitureDAO{
    
    private DAOFactory          daoFactory;
    private static final String SQL_SELECT_VOITURE_PAR_ID = "select * from voitures where id = ?";
    private static final String SQL_CREER = "INSERT INTO voitures (Prix, NbrePortes, EmissionCO2,Disponiblité,NbreBaggages,Poid,Volume,taille,options,NbrePassagers,BoiteVitesse,énergie,Nom, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?, ?)";
    private static final String SQL_SUPPRIMER_VOITURE = "delete from voitures where id = ?";
    private static final String SQL_SELECT_ALL = "select * from voitures";
    private static final String SQL_DELETE_CLIENT = "delete from voitures where id = ?";
    private static final String SQL_UPDATE_DISPO ="UPDATE voitures SET Disponiblité =   ?  WHERE voitures.`id` = ?;";
    public VoitureDAOImpl(DAOFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }
    
    
    @Override
    public Voiture trouver(int id) throws DAOException
    {
        return trouver(SQL_SELECT_VOITURE_PAR_ID, id);
    }
    
    @Override
     public void creer(Voiture v) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            //Prix, NbrePortes, EmissionCO2,Disponiblité,NbreBaggages,Poid,Volume,taille,option,NbrePassagers,BoiteVitesse,énergie,Nom
            preparedStatement = initialisationRequetePreparee(connexion, SQL_CREER, true, v.getPrix(),v.getNombrePortes(),v.getEmissionCO2(),v.getDisponibilite(),
                    v.getNombreBaggages(),v.getMasse(),v.getVolumeTotal(),v.getTaille(),v.getOption(),v.getNombrePassager(),v.getBoiteDeVitesse(),v.getEnergie(),v.getNom(), v.getImage());
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
    @Override
     public void setdipo(int id, int dispo) throws DAOException
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
            System.out.println(id);
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE_DISPO, false,dispo, id);
             System.out.println(preparedStatement );
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        } 
    }
     
    public void supprimer(int id) throws DAOException {
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
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SUPPRIMER_VOITURE, false,id);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
    }
        
        private Voiture trouver(String sql, Object... objets) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Voiture voiture = null;

        try {
            // RÃ©cupÃ©ration d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();
            /*
             * PrÃ©paration de la requÃªte avec les objets passÃ©s en arguments
             * (ici, uniquement une adresse email) et exÃ©cution.*/
             
            preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnÃ©es retournÃ©e dans le ResultSet */
            if (resultSet.next()) {
                voiture = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return voiture;
        }
        
    @Override
        public ArrayList<Voiture> listeVoiture() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Voiture> listvoiture = new ArrayList<>();

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
                
                listvoiture.add(map(resultSet));
                
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return listvoiture;
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
     
        
        private static Voiture map(ResultSet resultSet) throws SQLException
        {
            //id, prix, nbrepassager, emissionco2, disponibilite, nbreBaggage, masse, volumeTotal, taille, 
            //option, nbreportes, boitedevitesse, energie, nom
            Voiture voiture = new Voiture((int) resultSet.getLong("id"), resultSet.getDouble("Prix"), 
                    (int) resultSet.getLong("NbrePortes"), resultSet.getDouble("EmissionCO2"),
                    (int) resultSet.getLong("Disponiblité"), (int) resultSet.getLong("NbreBaggages"),
                    resultSet.getDouble("Poid"), resultSet.getDouble("Volume"), resultSet.getDouble("taille"),
                    resultSet.getString("options"), (int) resultSet.getLong("NbrePassagers"), resultSet.getString("BoiteVitesse"),
                    resultSet.getString("énergie"), resultSet.getString("Nom"), resultSet.getString("image"));
            return voiture;
        }
    }

