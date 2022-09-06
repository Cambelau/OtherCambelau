/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package packageFx.employe;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import rentable.*;
import rentable.DAO.*;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class RegistreVoiture_SceneController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private GridPane gridVoiture;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayGrid();
    }
    
    private void displayGrid(){
        
        ArrayList<Voiture> registreVoiture = new ArrayList<Voiture>();
        
        DAOFactory daofactory = null;
        try {
            daofactory = DAOFactory.getInstance();
        } catch (DAOConfigurationException ex) {
            Logger.getLogger(RegistreVoiture_SceneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistreVoiture_SceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        VoitureDAOImpl test= new VoitureDAOImpl(daofactory);
        registreVoiture = test.listeVoiture();
        
        
        for(int i=0;i<registreVoiture.size();i++){
            Image image;
            ImageView icon = new ImageView();
            String chemin = registreVoiture.get(i).getImage();
            if("A".equals(chemin))
            {
                image = new Image("@../../image/voitureA.jpg");
                icon.setImage(image);
            }
             if("B".equals(chemin))
            {
                image = new Image("@../../image/voitureB.jpg");
                icon.setImage(image);
            }
             if("C".equals(chemin))
            {
                image = new Image("@../../image/voitureC.jpg");
                icon.setImage(image);
            }
            if("D".equals(chemin))
            {
                image = new Image("@../../image/voitureD.jpg");
                icon.setImage(image);
            }
            icon.setFitWidth(150);
            icon.setFitHeight(50);
            
            Text id = new Text();
            String str1 = Integer.toString(registreVoiture.get(i).getID());
            id.setText(str1);
            id.setFill(Color.WHITE);
            id.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text prix = new Text();
            String str2 = Double.toString(registreVoiture.get(i).getPrix());
            prix.setText(str2);
            prix.setFill(Color.WHITE);
            prix.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text nbrePortes = new Text();
            String str3 = Integer.toString(registreVoiture.get(i).getNombrePortes());
            nbrePortes.setText(str3);
            nbrePortes.setFill(Color.WHITE);
            nbrePortes.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text emissionCO2 = new Text();
            String str4 = Double.toString(registreVoiture.get(i).getEmissionCO2());
            emissionCO2.setText(str4);
            emissionCO2.setFill(Color.WHITE);
            emissionCO2.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text disponibilite = new Text();
            String str5 = Integer.toString(registreVoiture.get(i).getDisponibilite());
            disponibilite.setText(str5);
            disponibilite.setFill(Color.WHITE);
            disponibilite.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text nbreBaggages = new Text();
            String str6 = Integer.toString(registreVoiture.get(i).getNombreBaggages());
            nbreBaggages.setText(str6);
            nbreBaggages.setFill(Color.WHITE);
            nbreBaggages.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text poid = new Text();
            String str7 = Double.toString(registreVoiture.get(i).getMasse());
            poid.setText(str7);
            poid.setFill(Color.WHITE);
            poid.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text volume = new Text();
            String str8 = Double.toString(registreVoiture.get(i).getVolumeTotal());
            volume.setText(str8);
            volume.setFill(Color.WHITE);
            volume.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text taille = new Text();
            String str9 = Double.toString(registreVoiture.get(i).getTaille());
            taille.setText(str9);
            taille.setFill(Color.WHITE);
            taille.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text options = new Text();
            options.setText(registreVoiture.get(i).getOption());
            options.setFill(Color.WHITE);
            options.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text nbrePassagers = new Text();
            String str10 = Integer.toString(registreVoiture.get(i).getNombrePassager());
            nbrePassagers.setText(str10);
            nbrePassagers.setFill(Color.WHITE);
            nbrePassagers.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text BoiteVitesse = new Text();
            BoiteVitesse.setText(registreVoiture.get(i).getBoiteDeVitesse());
            BoiteVitesse.setFill(Color.WHITE);
            BoiteVitesse.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text energie = new Text();
            energie.setText(registreVoiture.get(i).getEnergie());
            energie.setFill(Color.WHITE);
            energie.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Text nom = new Text();
            nom.setText(registreVoiture.get(i).getNom());
            nom.setFill(Color.WHITE);
            nom.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            
            Image imgDelete  = new Image("@../../image/delete.png");
            ImageView iconDelete = new ImageView(imgDelete);
            
            Button deleteBtn = new Button("");
            deleteBtn.setGraphic(iconDelete);
            
            deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int x=Integer.parseInt(id.getText());
                    test.delete(x);
                    gridVoiture.getChildren().clear();
                    displayGrid();
                }
            });
            
            gridVoiture.setGridLinesVisible(true);
            gridVoiture.add(deleteBtn,0,i+1,1,1);GridPane.setHalignment(deleteBtn, HPos.CENTER);
            gridVoiture.add(id,1,i+1,1,1);GridPane.setHalignment(id, HPos.CENTER);
            gridVoiture.add(icon,3,i+1,1,1);GridPane.setHalignment(icon, HPos.CENTER);
            gridVoiture.add(prix,4,i+1,1,1);GridPane.setHalignment(prix, HPos.CENTER);
            gridVoiture.add(nbrePortes,5,i+1,1,1);GridPane.setHalignment(nbrePortes, HPos.CENTER);
            gridVoiture.add(emissionCO2,6,i+1,1,1);GridPane.setHalignment(emissionCO2, HPos.CENTER);
            gridVoiture.add(disponibilite,7,i+1,1,1);GridPane.setHalignment(disponibilite, HPos.CENTER);
            gridVoiture.add(nbreBaggages,8,i+1,1,1);GridPane.setHalignment(nbreBaggages, HPos.CENTER);
            gridVoiture.add(poid,9,i+1,1,1);GridPane.setHalignment(poid, HPos.CENTER);
            gridVoiture.add(volume,10,i+1,1,1);GridPane.setHalignment(volume, HPos.CENTER);
            gridVoiture.add(taille,11,i+1,1,1);GridPane.setHalignment(taille, HPos.CENTER);
            gridVoiture.add(options,15,i+1,1,1);GridPane.setHalignment(options, HPos.CENTER);
            gridVoiture.add(nbrePassagers,13,i+1,1,1);GridPane.setHalignment(nbrePassagers, HPos.CENTER);
            gridVoiture.add(BoiteVitesse,12,i+1,1,1);GridPane.setHalignment(BoiteVitesse, HPos.CENTER);
            gridVoiture.add(energie,14,i+1,1,1);GridPane.setHalignment(energie, HPos.CENTER);
            gridVoiture.add(nom,2,i+1,1,1);GridPane.setHalignment(nom, HPos.CENTER);
            
            
        }
    }
}
