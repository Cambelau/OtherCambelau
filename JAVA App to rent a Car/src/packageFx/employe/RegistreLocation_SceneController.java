/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageFx.employe;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import rentable.DAO.LocationDAOImpl;
import rentable.DAO.DAOConfigurationException;
import rentable.DAO.DAOFactory;
import rentable.Location;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class RegistreLocation_SceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private GridPane gridLocation;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<Location> registreLocation = new ArrayList<Location>();
        
        DAOFactory daofactory = null;
            try {
                daofactory = DAOFactory.getInstance();
            } catch (DAOConfigurationException ex) {
                Logger.getLogger(RegistreLocation_SceneController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RegistreLocation_SceneController.class.getName()).log(Level.SEVERE, null, ex);
            }

        
        LocationDAOImpl test= new LocationDAOImpl(daofactory);
        registreLocation = test.listeLocation();
        
        for(int i=0;i<registreLocation.size();i++){
            Text id = new Text();
            String str1 = Integer.toString(registreLocation.get(i).getID());
            id.setText(str1);
            id.setFill(Color.WHITE);
            id.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text idClient = new Text();
            String str2 = Integer.toString(registreLocation.get(i).getIDClient());
            idClient.setText(str2);
            idClient.setFill(Color.WHITE);
            idClient.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text idVoiture = new Text();
            String str3 = Integer.toString(registreLocation.get(i).getIDVehicule());
            idVoiture.setText(str3);
            idVoiture.setFill(Color.WHITE);
            idVoiture.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text dateDebut = new Text();
            dateDebut.setText(registreLocation.get(i).getDateDebut().toString());
            dateDebut.setFill(Color.WHITE);
            dateDebut.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text dateFin = new Text();
            dateFin.setText(registreLocation.get(i).getDateFin().toString());
            dateFin.setFill(Color.WHITE);
            dateFin.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text AdresseRecup = new Text();
            AdresseRecup.setText(registreLocation.get(i).getAdresseRecuperation());
            AdresseRecup.setFill(Color.WHITE);
            AdresseRecup.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text duree = new Text();
            String str4 = Long.toString(registreLocation.get(i).getDuree());
            duree.setText(str4);
            duree.setFill(Color.WHITE);
            duree.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text prix = new Text();
            String str5 = Double.toString(registreLocation.get(i).getPrix());
            prix.setText(str5);
            prix.setFill(Color.WHITE);
            prix.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            gridLocation.add(prix,7,i+1,1,1);GridPane.setHalignment(prix, HPos.CENTER); 
            gridLocation.add(duree,6,i+1,1,1);GridPane.setHalignment(duree, HPos.CENTER);
            gridLocation.add(AdresseRecup,5,i+1,1,1);GridPane.setHalignment(AdresseRecup, HPos.CENTER);
            gridLocation.add(dateFin,4,i+1,1,1);GridPane.setHalignment(dateFin, HPos.CENTER);
            gridLocation.add(dateDebut,3,i+1,1,1);GridPane.setHalignment(dateDebut, HPos.CENTER);           
            gridLocation.add(idVoiture,2,i+1,1,1);GridPane.setHalignment(idVoiture, HPos.CENTER);
            gridLocation.add(idClient,1,i+1,1,1);GridPane.setHalignment(idClient, HPos.CENTER);
            gridLocation.add(id,0,i+1,1,1);GridPane.setHalignment(id, HPos.CENTER);
        }
        
   }    
}
