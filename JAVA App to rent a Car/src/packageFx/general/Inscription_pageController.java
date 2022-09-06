/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package packageFx.general;

import java.io.FileNotFoundException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import packageFx.Main;
import rentable.Client;
import rentable.DAO.ClientDaoImpl;
import rentable.DAO.DAOConfigurationException;
import rentable.DAO.DAOFactory;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class Inscription_pageController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField prenomText;
    @FXML
    private TextField nomText;
    @FXML
    private TextField adresseText;
    @FXML
    private TextField loginText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField telText;
    @FXML
    private TextField paiementText;
    @FXML
    private ToggleGroup classe;
    @FXML
    private BorderPane mainRoot;
    @FXML
    private Text errorText;
    
    @FXML
    private  void backBtn() throws Exception{
        Stage actualStage = (Stage)mainRoot.getScene().getWindow();
        actualStage.close();
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../connectionPage/NotConnected_Page.fxml"));
        stage.setTitle("RentAble designed by Tom Etienne Matthieu");
        stage.setMaximized(true);
        stage.getIcons().add(new Image("/image/icone.png"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    
    @FXML
    private  void pressValider() throws Exception{
        
        if(prenomText.getText().length()==0 || nomText.getText().length()==0 || adresseText.getText().length()==0
                || loginText.getText().length()==0 || passwordText.getText().length()==0 || telText.getText().length()==0  || classe.getSelectedToggle() == null)
            errorText.setText("Remplir tous les champs !");
        else{
            
            Stage actualStage = (Stage)mainRoot.getScene().getWindow();
            actualStage.close();
            
            Stage stage = new Stage();
            Parent root;
            
            String mdp = hachagemdp(passwordText.getText());
            
            //action creer le client dans la bdd
            DAOFactory daofactory = null;
            try {
                daofactory = DAOFactory.getInstance();
            }
            catch (FileNotFoundException ex) {
            }
            
            int type1 = 1;
            RadioButton button2 = (RadioButton) classe.getSelectedToggle();
            if("Individuel".equals(button2.getText()))
                type1 = 0;
            else
                type1 = 1;
            
            ClientDaoImpl test= new ClientDaoImpl(daofactory);
            if(test.trouver(loginText.getText(),mdp, adresseText.getText()) == null)
            {
//             System.out.println(loginText.getText() + "  " + mdp + "  " +  adresseText.getText());
                Client nvClient = new Client(0, nomText.getText(), prenomText.getText(), adresseText.getText()
                        , loginText.getText(), mdp, 1, type1, telText.getText(), paiementText.getText());
                test.creer(nvClient);
                Main.getDataBase().setClientConnecte(test.trouver(loginText.getText(), mdp));
            }
            else
            {
                errorText.setText("Le client existe deja !");
            }
            
            root = FXMLLoader.load(getClass().getResource("../connectionPage/ConnectedClient_Page.fxml"));
            stage.setTitle("RentAble designed by Tom Etienne Matthieu");
            stage.setMaximized(true);
            stage.getIcons().add(new Image("/image/icone.png"));
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    
    private String hachagemdp(String mdp){
        String passwordToHash = mdp;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        
        return generatedPassword;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) throws  DAOConfigurationException{
        // TODO
        
    }
    
}
