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
import rentable.Location;

/**
 *
 * @author sienn
 */
public class LocationDAOImpl implements LocationDAO{
    
    private static final String SQL_RESERVER = "insert into location (IdClient, IdVoiture, DateDebut, DateFin, AdresseRecup, durée, PrixTotal) values(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL = "select * from location";
    
    private DAOFactory          daoFactory;

    public LocationDAOImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    public void reserver(Location l) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        try
        {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connection, SQL_RESERVER, true, l.getIDClient(), l.getIDVehicule(), l.getDateDebut(), l.getDateFin(), l.getAdresseRecuperation(), (int) l.getDuree(), l.getPrix());
            int statut = preparedStatement.executeUpdate();
            
            if ( statut == 0 ) {
                throw new DAOException( "Ã‰chec de la crÃ©ation de l'utilisateur, aucune ligne ajoutÃ©e dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                l.setId( (int)valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Ã‰chec de la crÃ©ation de l'utilisateur en base, aucun ID auto-gÃ©nÃ©rÃ© retournÃ©." );
            }
           
        }
        catch (SQLException e) {
            throw new DAOException(e);
        } 
        finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connection);
        }
        VoitureDAOImpl test=new VoitureDAOImpl(daoFactory);
        test.setdipo(l.getIDVehicule(), 1);
    }
    
    public ArrayList<Location> listeLocation() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Location> listlocation = new ArrayList<>();

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
                
                listlocation.add(map(resultSet));
                
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return listlocation;
    }
    
    private static Location map(ResultSet resultSet) throws SQLException {
        Location location = new Location((int) resultSet.getLong("ID"), resultSet.getString("AdresseRecup"), (int)resultSet.getLong("IDVoiture"), resultSet.getString("durée"), resultSet.getDouble("PrixTotal"), (int)resultSet.getLong("iDClient"), resultSet.getString("dateDebut"), resultSet.getString("dateFin"));
        //utilisateur.setId( resultSet.getLong( "id" ) );
        //utilisateur.setEmail( resultSet.getString( "email" ) );
        //utilisateur.setMotDePasse( resultSet.getString( "mot_de_passe" ) );
        //utilisateur.setNom( resultSet.getString( "nom" ) );
        //utilisateur.setDateInscription( resultSet.getTimestamp( "date_inscription" ) );
        return location;
    }
}
