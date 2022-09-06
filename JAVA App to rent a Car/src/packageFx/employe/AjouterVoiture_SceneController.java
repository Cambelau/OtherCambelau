/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package packageFx.employe;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import rentable.DAO.DAOConfigurationException;
import rentable.DAO.DAOFactory;
import rentable.DAO.VoitureDAOImpl;

import rentable.Voiture;
/**
 * FXML Controller class
 *
 * @author Owner
 */
public class AjouterVoiture_SceneController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ToggleGroup car;
    @FXML
    private ToggleGroup vitesse;
    @FXML
    private ToggleGroup energie;
    @FXML
    private TextField prixText;
    @FXML
    private Slider slider;
    @FXML
    private Text errorText;
    @FXML
    private TextArea optionText;
    
    @FXML
    private void addVoiture() throws DAOConfigurationException, FileNotFoundException{
        DAOFactory daofactory = DAOFactory.getInstance();
        VoitureDAOImpl test = new VoitureDAOImpl(daofactory);
        String energieChoisie = "Default";
        String bdv = "Default";
        
        if(prixText.getText().length()==0  ||  car.getSelectedToggle() == null || vitesse.getSelectedToggle() == null || energie.getSelectedToggle() == null ){
                errorText.setText("Remplir tous les champs !");
        } 
        else{
            String model="";
             RadioButton carSelect = (RadioButton) car.getSelectedToggle();
             ToggleButton bdvSelect = (ToggleButton) vitesse.getSelectedToggle(); 
             ToggleButton energieSelect = (ToggleButton) energie.getSelectedToggle();
            
            if("carA".equals(carSelect.getText()))
            {
                model="A";
                int nbrePlace = 4;
                double emissionCO2 = 10;
                int disponibilite = 0;
                int nbreBaggages = 4;
                double masse = 1000;
                double volumeTotal = 1000;
                double taille = 4;
                int nbrePortes = 5;     
                String nom = "Utilitaire";
                double prix = Double.parseDouble(prixText.getText());
                if("Manuelle".equals(bdvSelect.getText()))
                {
                    bdv = "Manuelle";
                }
                else if("Automatique".equals(bdvSelect.getText()))
                {
                    bdv = "Automatique";
                }
                if("Électrique".equals(energieSelect.getText()))
                {
                    energieChoisie = "Électrique";
                    emissionCO2=0;
                }
                else if("Essence".equals(energieSelect.getText()))
                {
                    energieChoisie = "Essense";
                }
            Voiture v = new Voiture(0, prix, nbrePlace, emissionCO2, disponibilite, nbreBaggages, 
                                        masse, volumeTotal, taille, optionText.getText(), nbrePortes, bdv, energieChoisie, nom, model);

            test.creer(v);
            resetScene();                
            }
            
            else if("carB".equals(carSelect.getText()))
            {
                model="B";
                int nbrePlace = 7;
                double emissionCO2 = 20;
                int disponibilite = 0;
                int nbreBaggages = 6;
                double masse = 2000;
                double volumeTotal = 2000;
                double taille = 6;
                int nbrePortes = 5;     
                String nom = "Familliale";
                double prix = Double.parseDouble(prixText.getText());
                
                if("Manuelle".equals(bdvSelect.getText()))
                {
                    bdv = "Manuelle";
                }
                else if("Automatique".equals(bdvSelect.getText()))
                {
                    bdv = "Automatique";
                }
                if("Électrique".equals(energieSelect.getText()))
                {
                    energieChoisie = "Électrique";
                    emissionCO2=0;
                }
                else if("Essence".equals(energieSelect.getText()))
                {
                    energieChoisie = "Essense";
                }
            Voiture v = new Voiture(0, prix, nbrePlace, emissionCO2, disponibilite, nbreBaggages, 
                                        masse, volumeTotal, taille, optionText.getText(), nbrePortes, bdv, energieChoisie, nom, model);
            test.creer(v);
            resetScene();    
            }
            else if("carC".equals(carSelect.getText()))
            {
                model="C";
                int nbrePlace = 2;
                double emissionCO2 = 5;
                int disponibilite = 0;
                int nbreBaggages = 2;
                double masse = 800;
                double volumeTotal = 800;
                double taille = 2;
                int nbrePortes = 3;     
                String nom = "Citadine";
                double prix = Double.parseDouble(prixText.getText());
                
                if("Manuelle".equals(bdvSelect.getText()))
                {
                    bdv = "Manuelle";
                }
                else if("Automatique".equals(bdvSelect.getText()))
                {
                    bdv = "Automatique";
                }
                if("Électrique".equals(energieSelect.getText()))
                {
                    energieChoisie = "Électrique";
                    emissionCO2=0;
                }
                else if("Essence".equals(energieSelect.getText()))
                {
                    energieChoisie = "Essense";
                }
            Voiture v = new Voiture(0, prix, nbrePlace, emissionCO2, disponibilite, nbreBaggages, 
                                        masse, volumeTotal, taille, optionText.getText(), nbrePortes, bdv, energieChoisie, nom, model);
            test.creer(v);
            resetScene();
            }
            else if("carD".equals(carSelect.getText()))
            {
                model="D";
                int nbrePlace = 2;
                double emissionCO2 = 20;
                int disponibilite = 0;
                int nbreBaggages = 2;
                double masse = 1000;
                double volumeTotal = 1000;
                double taille = 4;
                int nbrePortes = 3;     
                String nom = "Sportive";
                double prix = Double.parseDouble(prixText.getText());
                
                if("Manuelle".equals(bdvSelect.getText()))
                {
                    bdv = "Manuelle";
                }
                else if("Automatique".equals(bdvSelect.getText()))
                {
                    bdv = "Automatique";
                }
                if("Électrique".equals(energieSelect.getText()))
                {
                    energieChoisie = "Électrique";
                    emissionCO2=0;
                }
                else if("Essence".equals(energieSelect.getText()))
                {
                    energieChoisie = "Essense";
                }
            Voiture v = new Voiture(0, prix, nbrePlace, emissionCO2, disponibilite, nbreBaggages, 
                                        masse, volumeTotal, taille, optionText.getText(), nbrePortes, bdv, energieChoisie, nom, model);
            test.creer(v);
            resetScene();
            }
        }       
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                double r = Math.floor(newValue.doubleValue() / 10.0) * 10.0;
                prixText.setText(Double.toString(r));
            }
        });
    }
    
    private void resetScene(){
        prixText.clear();
        errorText.setText("");
        optionText.setText("");
        slider.setValue(0);
        car.getSelectedToggle().setSelected(false);
        energie.getSelectedToggle().setSelected(false);
        vitesse.getSelectedToggle().setSelected(false);
    }
    
}
