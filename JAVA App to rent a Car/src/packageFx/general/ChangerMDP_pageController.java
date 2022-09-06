/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package packageFx.general;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import rentable.DAO.ClientDaoImpl;
import rentable.DAO.DAOFactory;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class ChangerMDP_pageController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField loginText;
    @FXML
    private TextField newPassText;
    @FXML
    private TextField valNewPassText;
    @FXML
    private BorderPane mainRoot;
    @FXML
    private Text errorText;
    
    @FXML
    private  void backBtn() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Connexion_page.fxml"));
        mainRoot.getChildren().setAll(root);
    }
    @FXML
    private void pressValider() throws IOException{
        DAOFactory daofactory = DAOFactory.getInstance();
        ClientDaoImpl test= new ClientDaoImpl(daofactory);
        if(test.rechercheLogin(loginText.getText()) == null){            //verifier si le login est dans la bdd
            if(!newPassText.getText().equals(valNewPassText.getText())){
                errorText.setText("Le mot de passe n'est pas le même");
            }else{
                
                String mdp = hachagemdp(valNewPassText.getText());
                test.changerMDP(mdp, loginText.getText());
                errorText.setText("Mdp modifié");
                
                
//                Stage actualStage = (Stage)mainRoot.getScene().getWindow();
//                actualStage.close();
//                
//                Stage stage = new Stage();
//                Parent root = FXMLLoader.load(getClass().getResource("Connexion_page.fxml"));
//                stage.setTitle("RentAble designed by Tom Etienne Matthieu");
//                stage.initModality(Modality.APPLICATION_MODAL);
//                stage.getIcons().add(new Image("/image/icone.png"));
//                stage.setScene(new Scene(root));
//                stage.show();
            }
        }else{
            errorText.setText("Ce login n'existe pas");
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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
