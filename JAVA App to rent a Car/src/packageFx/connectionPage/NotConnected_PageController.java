/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package packageFx.connectionPage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class NotConnected_PageController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private BorderPane midRoot;
    
    @FXML
    private  void pressConnexion() throws Exception{
        Stage actualStage = (Stage)midRoot.getScene().getWindow();
        actualStage.close();
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../general/Connexion_page.fxml"));
        stage.setTitle("RentAble designed by Tom Etienne Matthieu");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("/image/icone.png"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private  void pressInscription() throws Exception{
        Stage actualStage = (Stage)midRoot.getScene().getWindow();
        actualStage.close();
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../general/Inscription_page.fxml"));
        stage.setTitle("RentAble designed by Tom Etienne Matthieu");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("/image/icone.png"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
            HBox  root = FXMLLoader.load(getClass().getResource("/packageFx/client/Recherche_scene.fxml"));
             midRoot.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(NotConnected_PageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
