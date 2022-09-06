/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package packageFx.client;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import packageFx.Main;
import rentable.DAO.DAOConfigurationException;
import rentable.DAO.DAOFactory;
import rentable.DAO.LocationDAOImpl;
import rentable.Location;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class Reservation_SceneController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private Text idText;
    @FXML
    private Text nomText;
    @FXML
    private Text prixJText;
    @FXML
    private Text bagageText;
    @FXML
    private Text porteText;
    @FXML
    private Text passagetText;
    @FXML
    private Text adresseText;
    @FXML
    private Text duree;
    @FXML
    private Text options;
    @FXML
    private Text errorText;
    @FXML
    private Text prixTotalText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String idS = String.valueOf(Main.getDataBase().getVoitureLoue().getID());
        idText.setText(idS);
        String idN = String.valueOf(Main.getDataBase().getVoitureLoue().getNom());
        nomText.setText(idN);
        String idPJ = String.valueOf(Main.getDataBase().getVoitureLoue().getPrix());
        prixJText.setText(idPJ);
        String idB = String.valueOf(Main.getDataBase().getVoitureLoue().getNombreBaggages());
        bagageText.setText(idB);
        String idP = String.valueOf(Main.getDataBase().getVoitureLoue().getNombrePassager());
        passagetText.setText(idP);
        String idPo = String.valueOf(Main.getDataBase().getVoitureLoue().getNombrePortes());
        porteText.setText(idPo);
        String idO = String.valueOf(Main.getDataBase().getVoitureLoue().getOption());
        options.setText(idO);
        adresseText.setText(Main.getDataBase().getAdresseSearch());
        String idD = Long.toString(Main.getDataBase().getDuree());
        duree.setText(idD);
        int iDuree = (int) Main.getDataBase().getDuree();
        int prixT = (int) (iDuree * Main.getDataBase().getVoitureLoue().getPrix());
        String idDu = String.valueOf(prixT);
        prixTotalText.setText(idDu);
    }
    
    @FXML
    private void annulerBtn(){
        Stage actualStage = (Stage)idText.getScene().getWindow();
        actualStage.close();
    }
    @FXML
    private void reserverBtn(){
        if(Main.getDataBase().getClientConnecte()==null){
            errorText.setText("Connectez vous avant de réserver !");
        }else{
            double prixTot = Double.parseDouble( prixTotalText.getText());
            Location newLoc = new Location(0,Main.getDataBase().getAdresseSearch(),Main.getDataBase().getVoitureLoue().getID(),
                    duree.getText(),prixTot,Main.getDataBase().getClientConnecte().getID(),Main.getDataBase().getDateDebut(),Main.getDataBase().getDateFin());
            
            DAOFactory daofactory = null;
            try {
                daofactory = DAOFactory.getInstance();
            } catch (DAOConfigurationException | FileNotFoundException ex) {
            }
            LocationDAOImpl test = new LocationDAOImpl(daofactory);
            test.reserver(newLoc);
            
            //On referme la fenetre 
            Stage actualStage = (Stage)idText.getScene().getWindow();
            actualStage.close();
        }
        
    }
    
}
