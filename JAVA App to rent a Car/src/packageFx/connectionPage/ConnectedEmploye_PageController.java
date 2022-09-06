/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package packageFx.connectionPage;

import java.net.URL;
import java.util.ResourceBundle;
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
public class ConnectedEmploye_PageController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private BorderPane midRoot;   
    @FXML
    private Text npEmploye;
    
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
        
        Main.getDataBase().setClientConnecte(null);
    }
    
    @FXML
    private  void profilBtn() throws Exception{
        HBox  root = FXMLLoader.load(getClass().getResource("/packageFx/employe/ProfilEmploye_scene.fxml"));
        midRoot.setCenter(root);
    }
    @FXML
    private  void ajouterVoiture() throws Exception{
        HBox  root = FXMLLoader.load(getClass().getResource("/packageFx/employe/AjouterVoiture_scene.fxml"));
        midRoot.setCenter(root);
    }
    @FXML
    private  void registreVoitureBtn() throws Exception{
        HBox  root = FXMLLoader.load(getClass().getResource("/packageFx/employe/RegistreVoiture_Scene.fxml"));
        midRoot.setCenter(root);
    }
    @FXML
    private  void registreClientBtn() throws Exception{
        HBox  root = FXMLLoader.load(getClass().getResource("/packageFx/employe/RegistreClient_Scene.fxml"));
        midRoot.setCenter(root);
    }
    @FXML
    private  void registreLocationBtn() throws Exception{
        HBox  root = FXMLLoader.load(getClass().getResource("/packageFx/employe/RegistreLocation_Scene.fxml"));
        midRoot.setCenter(root);
    }
    @FXML
    private  void populariteBtn() throws Exception{
        HBox  root = FXMLLoader.load(getClass().getResource("/packageFx/employe/PopulariteEmploye_Scene.fxml"));
        midRoot.setCenter(root);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         npEmploye.setText(Main.getDataBase().getEmployeConnecte().getPrenom() + "  " +  Main.getDataBase().getEmployeConnecte().getNom());
    }
    
}
