/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package packageFx.client;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import packageFx.Main;
import rentable.DAO.DAOConfigurationException;
import rentable.DAO.DAOFactory;
import rentable.DAO.LocationDAOImpl;
import rentable.DAO.VoitureDAOImpl;
import rentable.Location;
import rentable.Voiture;


/**
 * FXML Controller class
 *
 * @author Owner
 */
public class ProfilClient_sceneController implements Initializable{
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Text Nom;
    @FXML
    private Text Prenom;
    @FXML
    private Text Adresse;
    @FXML
    private Text tel;
    @FXML
    private Text Membre;
    @FXML
    private Text Paiement;
    @FXML
    private Text AdresseRecup;
    @FXML
    private Text NomVehicule;
    @FXML
    private Text DateDebut;
    @FXML
    private Text DateFin;
    @FXML
    private Text Prix;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) throws  DAOConfigurationException{
        
        Nom.setText(Main.getDataBase().getClientConnecte().getNom());
        Prenom.setText(Main.getDataBase().getClientConnecte().getPrenom());
        Adresse.setText(Main.getDataBase().getClientConnecte().getAdresse());
        tel.setText(Main.getDataBase().getClientConnecte().getTelephone());
        Membre.setText(Integer.toString(Main.getDataBase().getClientConnecte().getMembre()));
        Paiement.setText(Main.getDataBase().getClientConnecte().getPaiement());
        
        DAOFactory daofactory = null;
        try {
            daofactory = DAOFactory.getInstance();
        } catch (FileNotFoundException ex) {
            
        }
        LocationDAOImpl test = new LocationDAOImpl(daofactory);
        ArrayList<Location> listLocation = new ArrayList<>();
        listLocation = test.listeLocation();
        
        VoitureDAOImpl test1 = new VoitureDAOImpl(daofactory);
        ArrayList<Voiture> listVoiture = new ArrayList<>();
        listVoiture = test1.listeVoiture();
        
        int idClientConnectee = Main.getDataBase().getClientConnecte().getID();
        int tem=0;
        for(int i = 0; i<listLocation.size(); i++)
        {
            if(listLocation.get(i).getIDClient() == idClientConnectee)
            {   tem=1;
                AdresseRecup.setText(listLocation.get(i).getAdresseRecuperation());
                AdresseRecup.setFill(Color.WHITE);
                AdresseRecup.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                
                String nom = "";
                for(int j = 0; j < listVoiture.size(); j++)
                {
                    if(listLocation.get(i).getIDVehicule() == listVoiture.get(j).getID())
                    {
                        nom = listVoiture.get(j).getNom();
                    }
                }
                NomVehicule.setText(nom);
                NomVehicule.setFill(Color.WHITE);
                NomVehicule.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                
                DateDebut.setText(listLocation.get(i).getDateDebut().toString());
                DateDebut.setFill(Color.WHITE);
                DateDebut.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                
                DateFin.setText(listLocation.get(i).getDateFin().toString());
                DateFin.setFill(Color.WHITE);
                DateFin.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                
                String str1 = Double.toString(listLocation.get(i).getPrix());
                Prix.setText(str1);
                Prix.setFill(Color.WHITE);
                Prix.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                
                
            }
        }
        if(tem==0){
            AdresseRecup.setText("Pas de location en cours");
            NomVehicule.setText("Pas de location en cours");
            DateDebut.setText("Pas de location en cours");
            DateFin.setText("Pas de location en cours");
            Prix.setText("Pas de location en cours");
        }
    }
}