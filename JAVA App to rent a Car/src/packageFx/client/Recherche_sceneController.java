/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package packageFx.client;

import java.net.URL;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import packageFx.Main;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class Recherche_sceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private HBox midSearch;
    @FXML
    private MenuButton adressePane;//adressePane.getText();
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private Text errorText;

    @FXML
    private void rechercherBtn() throws Exception {
        //  if()//les trois critères sont remplus     ///adressePane.getText()=="Adresse"
        if ("Adresse".equals(adressePane.getText()) || dateDebut.getValue() == null || dateFin.getValue() == null) {
            errorText.setText("Remplir tous les champs !");
        } else {
            Period period = Period.between(dateDebut.getValue(), dateFin.getValue());
            int duree = (int) period.getDays();
            if (duree < 0) {
                   errorText.setText("Les dates sont mal indiquées !");
            } else {
                Main.getDataBase().setAdresseSearch(adressePane.getText());
                Main.getDataBase().setDateDebut(dateDebut.getValue());
                Main.getDataBase().setdateFin(dateFin.getValue());

                HBox root = FXMLLoader.load(getClass().getResource("/packageFx/client/Resultat_scene.fxml"));
                midSearch.getChildren().setAll(root);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> adresse = new ArrayList();
        adresse = Main.getDataBase().getAdresse();

        for (String a : adresse) {

            MenuItem menuItemNew = new MenuItem(a);
            adressePane.getItems().addAll(menuItemNew);

            menuItemNew.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                    public void handle(ActionEvent t) {
                    adressePane.setText(a);
                }
            });
        }
    }

}
