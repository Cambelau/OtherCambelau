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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import packageFx.Main;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class ConnectedClient_PageController implements Initializable {
    
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private BorderPane midRoot;
    @FXML
    private Text npClient;
    
    @FXML
    private  void rechercherBtn() throws Exception{
        HBox  root = FXMLLoader.load(getClass().getResource("/packageFx/client/Recherche_scene.fxml"));
        midRoot.setCenter(root);
    }
    @FXML
    private  void profilBtn() throws Exception{
        HBox  root = FXMLLoader.load(getClass().getResource("/packageFx/client/ProfilClient_scene.fxml"));
        midRoot.setCenter(root);
    }
    @FXML
    private  void deconnectBtn() throws Exception{
        Stage actualStage = (Stage)midRoot.getScene().getWindow();
        actualStage.close();
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../connectionPage/NotConnected_Page.fxml"));
        stage.setTitle("RentAble designed by Tom Etienne Matthieu");
        stage.setMaximized(true);
        stage.getIcons().add(new Image("/image/icone.png"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        npClient.setText(Main.getDataBase().getClientConnecte().getPrenom() + "  " +  Main.getDataBase().getClientConnecte().getNom());
        
        try {
            HBox  root = FXMLLoader.load(getClass().getResource("/packageFx/client/ProfilClient_scene.fxml"));
            midRoot.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(ConnectedClient_PageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
