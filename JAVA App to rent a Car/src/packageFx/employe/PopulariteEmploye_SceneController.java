/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageFx.employe;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import rentable.DAO.DAOConfigurationException;
import rentable.DAO.DAOFactory;
import rentable.Location;
import rentable.DAO.LocationDAOImpl;
import rentable.DAO.VoitureDAOImpl;
import rentable.Voiture;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class PopulariteEmploye_SceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private NumberAxis xAxis;
    @FXML
    private CategoryAxis yAxis;
    @FXML
    private BarChart<String, Number> statCar;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        CategoryAxis xAxis = new CategoryAxis();
       xAxis.setLabel("Car Modele");
       ArrayList<Integer> nbr=new ArrayList<>();
        nbr.add(0);
        nbr.add(0);
        nbr.add(0);
        nbr.add(0);
 
       NumberAxis yAxis = new NumberAxis();
       yAxis.setLabel("Number");
 
      DAOFactory daofactory = null;
        try {
            daofactory = DAOFactory.getInstance();
        } catch (DAOConfigurationException ex) {
            
        } catch (FileNotFoundException ex) { Logger.getLogger(PopulariteEmploye_SceneController.class.getName()).log(Level.SEVERE, null, ex);
          }
        ArrayList<Location> listlocation = new ArrayList<>();
        LocationDAOImpl test = new LocationDAOImpl(daofactory);
        VoitureDAOImpl test2 = new VoitureDAOImpl(daofactory);
        listlocation=test.listeLocation();
        
        
        for(int i=0;i<listlocation.size();++i){
            int id=listlocation.get(i).getIDVehicule();
            if(test2.trouver(id)==null){
                
            }
            else{
                Voiture voiture=test2.trouver(id);
            
            if(null == voiture.getNom()){
            }
            else switch (voiture.getNom()) {
                case "Utilitaire":
                    {
                        int temp= 0;
                        temp= nbr.get(0);
                        ++temp;
                        nbr.set(0, temp);
                      
                        break;
                    }
                case "Familliale":
                    {
                        int temp= 0;
                        temp= nbr.get(1);
                        ++temp;
                        nbr.set(1, temp);
                        
                        break;
                    }
                case "Citadine":
                    {
                        int temp= 0;
                        temp= nbr.get(2);
                        ++temp;
                        nbr.set(2, temp);
                        
                        break;
                    }
                case "Sportive":
                    {
                        int temp= 0;
                        temp= nbr.get(3);
                        
                        ++temp;
                        nbr.set(3, temp);
                        break;
                    }
                default:
                    break;
            }
            }
            
            }
            
        
 
       // Series 1 - Car 1
       XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
       dataSeries1.setName("Utilitaire");
       dataSeries1.getData().add(new XYChart.Data<String, Number>("Utilitaire", nbr.get(0)));
       // Series 2 - Car 2
       XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
       dataSeries2.setName("Familliale");
       dataSeries2.getData().add(new XYChart.Data<String, Number>("Familliale", nbr.get(1)));
       
       // Series 2 - Car 3
       XYChart.Series<String, Number> dataSeries3 = new XYChart.Series<String, Number>();
       dataSeries3.setName("Citadine");
       dataSeries3.getData().add(new XYChart.Data<String, Number>("Citadine", nbr.get(2)));
       
       // Series 2 - Car 4
       XYChart.Series<String, Number> dataSeries4 = new XYChart.Series<String, Number>();
       dataSeries4.setName("Sportive");
       dataSeries4.getData().add(new XYChart.Data<String, Number>("Sportive",nbr.get(3)));

       statCar.getData().add(dataSeries1);
       statCar.getData().add(dataSeries2);
       statCar.getData().add(dataSeries3);
       statCar.getData().add(dataSeries4);
                  
       statCar.setTitle("Popularité des voitures");
   
          }    
    
}
