/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageFx.employe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import packageFx.Main;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class ProfilEmploye_SceneController implements Initializable {

    @FXML
    private Text Nom;
    @FXML
    private Text Prenom;
    @FXML
    private Text Adresse;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Nom.setText(Main.getDataBase().getEmployeConnecte().getNom());
        Prenom.setText(Main.getDataBase().getEmployeConnecte().getPrenom());
        Adresse.setText(Main.getDataBase().getEmployeConnecte().getAdresse());
    }    
    
}
