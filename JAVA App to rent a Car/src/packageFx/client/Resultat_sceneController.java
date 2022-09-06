/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package packageFx.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import packageFx.Main;
import rentable.DAO.DAOConfigurationException;
import rentable.DAO.DAOFactory;
import rentable.DAO.VoitureDAOImpl;
import rentable.Voiture;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class Resultat_sceneController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox resultBox;
    @FXML
    private MenuButton trierBtn;
    @FXML
    private Text adresseText;
    @FXML
    private Text dateD;
    @FXML
    private Text dateF;
    @FXML
    private Text dureeText;
    
    @FXML
    public void aucunBtn(){
        trierBtn.setText("Aucun");
    }
    @FXML
    public void prixCBtn(){
        trierBtn.setText("Prix croissant");
        Collections.sort(Main.getDataBase().getdisponible(), Voiture.ComparatorPrix);
        resultBox.getChildren().clear();
        displayVoiture();
        
    }
    @FXML
    public void prixDBtn(){
        trierBtn.setText("Prix décroissant");
        Collections.sort(Main.getDataBase().getdisponible(), Voiture.ComparatorPrix);
        Collections.reverse(Main.getDataBase().getdisponible());
        resultBox.getChildren().clear();
        displayVoiture();
    }
    @FXML
    public void Npc(){
        trierBtn.setText("Nombre de passager croissant");
        Collections.sort(Main.getDataBase().getdisponible(), Voiture.ComparatorPassager);
        resultBox.getChildren().clear();
        displayVoiture();
        
    }
    @FXML
    public void Npd(){
        trierBtn.setText("Nombre de passager décroissant");
        Collections.sort(Main.getDataBase().getdisponible(), Voiture.ComparatorPassager);
        Collections.reverse(Main.getDataBase().getdisponible());
        resultBox.getChildren().clear();
        displayVoiture();
    }
    
    private void displayVoiture(){
        for(int i=0;i<Main.getDataBase().getdisponible().size();i++){        //get liste voiture
            AnchorPane root = new AnchorPane();
            root.setPrefWidth(612);
            root.setPrefHeight(200);
            
            Image imgCar = new Image("@../../image/voitureA.jpg");
            ImageView iconCar = new ImageView();
            iconCar.setImage(imgCar);
            String chemin = Main.getDataBase().getdisponible().get(i).getImage();
            if("A".equals(chemin))
            {
                imgCar = new Image("@../../image/voitureA.jpg");
                iconCar.setImage(imgCar);
            }
            if("B".equals(chemin))
            {
                imgCar = new Image("@../../image/voitureB.jpg");
                iconCar.setImage(imgCar);
            }
            if("C".equals(chemin))
            {
                imgCar = new Image("@../../image/voitureC.jpg");
                iconCar.setImage(imgCar);
            }
            if("D".equals(chemin))
            {
                imgCar = new Image("@../../image/voitureD.jpg");
                iconCar.setImage(imgCar);
            }
            iconCar.setFitHeight(80);
            iconCar.setFitWidth(160);
            
            Image imgA = new Image("@../../image/profile-user.png");
            ImageView iconA = new ImageView();
            iconA.setImage(imgA);
            iconA.setFitHeight(65);
            iconA.setFitWidth(65);
            Image imgB = new Image("@../../image/luggage.png");
            ImageView iconB = new ImageView();
            iconB.setImage(imgB);
            iconB.setFitHeight(65);
            iconB.setFitWidth(65);
            Image imgC = new Image("@../../image/car-door.png");
            ImageView iconC = new ImageView();
            iconC.setImage(imgC);
            iconC.setFitHeight(65);
            iconC.setFitWidth(65);
            Image imgD = new Image("@../../image/nut.png");
            ImageView iconD = new ImageView();
            iconD.setImage(imgD);
            iconD.setFitHeight(65);
            iconD.setFitWidth(65);
            
            Text nom = new Text();
            nom.setFill(Color.BLACK);
            nom.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 24));
            Text id = new Text();
            id.setFill(Color.BLACK);
            id.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 24));
            Text idText = new Text();
            idText.setFill(Color.BLACK);
            idText.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 24));
            Text textA = new Text();
            textA.setFill(Color.BLACK);
            textA.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 20));
            Text textB = new Text();
            textB.setFill(Color.BLACK);
            textB.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 20));
            Text textC = new Text();
            textC.setFill(Color.BLACK);
            textC.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 20));
            Text textD = new Text();
            textD.setFill(Color.BLACK);
            textD.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 20));
            Text prixT = new Text();
            prixT.setFill(Color.BLACK);
            prixT.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 24));
            Button reserBtn = new Button("Réserver");
            
            
            root.setTopAnchor(iconCar, 20.0);
            root.setLeftAnchor(iconCar, 35.0);
            root.setTopAnchor(nom, 30.0);
            root.setLeftAnchor(nom, 220.0);
            root.setTopAnchor(id, 60.0);
            root.setLeftAnchor(id, 280.0);
            root.setTopAnchor(idText, 60.0);
            root.setLeftAnchor(idText, 220.0);
            root.setTopAnchor(iconA, 120.0);
            root.setLeftAnchor(iconA, 50.0);
            root.setTopAnchor(textA, 140.0);
            root.setLeftAnchor(textA, 140.0);
            root.setTopAnchor(iconB, 120.0);
            root.setLeftAnchor(iconB, 180.0);
            root.setTopAnchor(textB, 140.0);
            root.setLeftAnchor(textB, 270.0);
            root.setTopAnchor(iconC, 120.0);
            root.setLeftAnchor(iconC, 310.0);
            root.setTopAnchor(textC, 140.0);
            root.setLeftAnchor(textC, 400.0);
            root.setTopAnchor(iconD, 120.0);
            root.setLeftAnchor(iconD, 440.0);
            root.setTopAnchor(textD, 140.0);
            root.setLeftAnchor(textD, 530.0);
            root.setTopAnchor(prixT, 25.0);
            root.setLeftAnchor(prixT, 450.0);
            root.setTopAnchor(reserBtn, 60.0);
            root.setLeftAnchor(reserBtn, 450.0);
            
            reserBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    DAOFactory daofactory = null;
                    try {
                        daofactory = DAOFactory.getInstance();
                    } catch (DAOConfigurationException | FileNotFoundException ex) {
                        
                    }
                    VoitureDAOImpl test = new VoitureDAOImpl(daofactory);
                    int s =Integer.parseInt(id.getText());
                    Main.getDataBase().setVoitureLoue(test.trouver(s));
                    
                    try {
                        Stage stage = new Stage();
                        Parent  proot = FXMLLoader.load(getClass().getResource("Reservation_Scene.fxml"));
                        stage.setTitle("RentAble designed by Tom Etienne Matthieu");
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.getIcons().add(new Image("/image/icone.png"));
                        stage.setScene(new Scene(proot));
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(Resultat_sceneController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
            });
            
            nom.setText(Main.getDataBase().getdisponible().get(i).getNom());
            String idT =String.valueOf(Main.getDataBase().getdisponible().get(i).getID());
            id.setText(idT);
            idText.setText("ID : ");
            textA.setText(" "+Main.getDataBase().getdisponible().get(i).getNombrePassager());//voiture.get(i)
            textB.setText(" "+Main.getDataBase().getdisponible().get(i).getNombreBaggages());
            textC.setText(" "+Main.getDataBase().getdisponible().get(i).getNombrePortes());
            textD.setText(" "+Main.getDataBase().getdisponible().get(i).getTaille());
            int prixtotal=(int) (Main.getDataBase().getdisponible().get(i).getPrix() * Main.getDataBase().getDuree());
            prixT.setText("Prix :   " + prixtotal + "€");
            root.getChildren().addAll(iconCar,id,idText,nom,iconA,textA,iconB,textB,iconC,textC,iconD,textD,prixT,reserBtn);
            
            Separator separator = new Separator();
            separator.setMaxWidth(700);
            separator.setHalignment(HPos.CENTER);
            resultBox.getChildren().addAll(root);
            resultBox.getChildren().addAll(separator);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        DAOFactory daofactory = null;
        try {
            daofactory = DAOFactory.getInstance();
        } catch (DAOConfigurationException | FileNotFoundException ex) {
            
        }
        
        ArrayList<Voiture> voitures= new ArrayList<>();
        VoitureDAOImpl test = new VoitureDAOImpl(daofactory);
        
        voitures=test.listeVoiture();
        for(int i=0;i<voitures.size();++i){
            if(voitures.get(i).getDisponibilite()==0){
                Main.getDataBase().addVoiture(voitures.get(i));
            }
        }
        
        
        
        displayVoiture();
        
        adresseText.setText(Main.getDataBase().getAdresseSearch());
        dateD.setText(Main.getDataBase().getDateDebut());
        dateF.setText(Main.getDataBase().getDateFin());
        
        String d = Long.toString(Main.getDataBase().getDuree());
        dureeText.setText(d + " Jours");
    }
    
}
