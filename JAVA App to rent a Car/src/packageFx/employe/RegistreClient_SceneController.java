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
import rentable.Client;
import rentable.DAO.ClientDaoImpl;
import rentable.DAO.DAOConfigurationException;
import rentable.DAO.DAOFactory;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class RegistreClient_SceneController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private GridPane gridVoiture;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<Client> registreClient = new ArrayList<Client>();
        
        DAOFactory daofactory = null;
        try {
            daofactory = DAOFactory.getInstance();
        } catch (DAOConfigurationException ex) {
            Logger.getLogger(RegistreVoiture_SceneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistreVoiture_SceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ClientDaoImpl test= new ClientDaoImpl(daofactory);
        registreClient = test.listeclient();
        
        for(int i=0;i<registreClient.size();i++){
            Text id = new Text();
            String str1 = Integer.toString(registreClient.get(i).getID());
            id.setText(str1);
            id.setFill(Color.WHITE);
            id.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text nom = new Text();
            nom.setText(registreClient.get(i).getNom());
            nom.setFill(Color.WHITE);
            nom.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text prenom = new Text();
            prenom.setText(registreClient.get(i).getPrenom());
            prenom.setFill(Color.WHITE);
            prenom.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text adresse = new Text();
            adresse.setText(registreClient.get(i).getAdresse());
            adresse.setFill(Color.WHITE);
            adresse.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text login = new Text();
            login.setText(registreClient.get(i).getLogin());
            login.setFill(Color.WHITE);
            login.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text mdp = new Text();
            mdp.setText(registreClient.get(i).getMDP());
            mdp.setFill(Color.WHITE);
            mdp.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text membre = new Text();
            String str2 = Integer.toString(registreClient.get(i).getMembre());
            membre.setText(str2);
            membre.setFill(Color.WHITE);
            membre.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text tel = new Text();
            tel.setText(registreClient.get(i).getTelephone());
            tel.setFill(Color.WHITE);
            tel.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text statut = new Text();
            String str3 = Integer.toString(registreClient.get(i).getStatut());
            statut.setText(str3);
            statut.setFill(Color.WHITE);
            statut.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text paiement = new Text();
            paiement.setText(registreClient.get(i).getPaiement());
            paiement.setFill(Color.WHITE);
            paiement.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            gridVoiture.add(statut,7,i+1,1,1);GridPane.setHalignment(statut, HPos.CENTER);
            gridVoiture.add(tel,6,i+1,1,1);GridPane.setHalignment(tel, HPos.CENTER);
            gridVoiture.add(membre,5,i+1,1,1);GridPane.setHalignment(membre, HPos.CENTER);
            gridVoiture.add(login,4,i+1,1,1);GridPane.setHalignment(login, HPos.CENTER);
            gridVoiture.add(adresse,3,i+1,1,1);GridPane.setHalignment(adresse, HPos.CENTER);
            gridVoiture.add(prenom,2,i+1,1,1);GridPane.setHalignment(prenom, HPos.CENTER);
            gridVoiture.add(nom,1,i+1,1,1);GridPane.setHalignment(nom, HPos.CENTER);
            gridVoiture.add(id,0,i+1,1,1);GridPane.setHalignment(id, HPos.CENTER);
        }
        
    }
    
}