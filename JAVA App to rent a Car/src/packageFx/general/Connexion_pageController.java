/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package packageFx.general;

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
import rentable.DAO.ClientDaoImpl;
import rentable.DAO.DAOFactory;
import rentable.DAO.EmployeDAOImpl;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class Connexion_pageController implements Initializable {
    
    @FXML
    private BorderPane mainRoot;
    @FXML
    private ToggleGroup type;
    @FXML
    private TextField loginText;
    @FXML
    private TextField passwordText;
    @FXML
    private Text errorText;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
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
    private  void forgetPassword() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ChangerMDP_page.fxml"));
        mainRoot.getChildren().setAll(root);
    }
    
    @FXML
    private  void pressValider() throws Exception{
        if (type.getSelectedToggle() != null) {
            String identifiant =loginText.getText();
            String mdp =passwordText.getText();
            mdp =hachagemdp(mdp);
            
            DAOFactory daofactory = DAOFactory.getInstance();
            ClientDaoImpl test= new ClientDaoImpl(daofactory);
            EmployeDAOImpl test3= new EmployeDAOImpl(daofactory);
            RadioButton button = (RadioButton) type.getSelectedToggle();
            
            if("Client".equals(button.getText())){
                if(test.trouver(identifiant,mdp)==null){
                    //client pas connecté((pas dans la bdd)
                    errorText.setText("Le login ou le mot de passe est incorrect.");
                }
                else{
                    Main.getDataBase().setClientConnecte(test.trouver(identifiant, mdp));
                    Stage actualStage = (Stage)mainRoot.getScene().getWindow();
                    actualStage.close();
                    
                    Stage stage = new Stage();
                    Parent root;
                    
                    root = FXMLLoader.load(getClass().getResource("../connectionPage/ConnectedClient_Page.fxml"));
                    
                    stage.setTitle("RentAble designed by Tom Etienne Matthieu");
                    stage.setMaximized(true);
                    stage.getIcons().add(new Image("/image/icone.png"));
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            } else{
                if(test3.trouver(identifiant,mdp)==null){
                    //empoyes pas connecté(pas dans la bdd)
                    errorText.setText("Le login ou le mot de passe est incorrect");
                }
                else{
                    Main.getDataBase().setEmployeConnecte(test3.trouver(identifiant, mdp));
                    
                    Stage actualStage = (Stage)mainRoot.getScene().getWindow();
                    actualStage.close();
                    
                    Stage stage = new Stage();
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("../connectionPage/ConnectedEmploye_Page.fxml"));
                    stage.setTitle("RentAble designed by Tom Etienne Matthieu");
                    stage.setMaximized(true);
                    stage.getIcons().add(new Image("/image/icone.png"));
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            }
        }
        else{
            errorText.setText("Remplir tout les champ avant de valider !");
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
//        System.out.println(generatedPassword);
        return generatedPassword;
    }
    
}

