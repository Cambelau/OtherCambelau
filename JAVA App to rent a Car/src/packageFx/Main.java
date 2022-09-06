/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageFx;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import rentable.*;

/**
 *
 * @author Owner
 */
public class Main extends Application {
        
    //database contient toute les données locales du logiciel
     private static DataBase rentable = new DataBase();

    @Override
    public void start(Stage primaryStage) throws IOException {

        //declaration de la page principale et affichage
        Parent root = FXMLLoader.load(getClass().getResource("/packageFx/connectionPage/NotConnected_Page.fxml"));
        primaryStage.setTitle("RentAble designed by Tom Etienne Matthieu");
        primaryStage.getIcons().add(new Image("/image/icone.png"));
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static DataBase getDataBase(){
        return rentable;
    }  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}