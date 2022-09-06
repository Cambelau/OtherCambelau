/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentable;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import rentable.DAO.ClientDaoImpl;
import rentable.DAO.DAOConfigurationException;
import rentable.DAO.DAOFactory;

/**
 *
 * @author sienn
 * Classe mémoire qui garde les éléments chargés dans la bdd  pour les utiliser dans la view
 */
public class DataBase {
    protected ArrayList<String> adresse = new ArrayList<String>();
    protected LocalDate dateDebut = LocalDate.parse("2020-11-24");
    protected LocalDate dateFin = LocalDate.parse("2020-11-30");
    protected Client cConnecte = new Client();
    protected Employes eConnecte = new Employes();
    protected ArrayList<Voiture> disponible = new ArrayList<Voiture>(); 
    protected Location terminee = new Location();
    protected ArrayList<Client> registreClient = new ArrayList<Client>();
    protected String adresseSearch;
    protected Voiture reservation;
    
    /**
     * Ajout des adresses de bases du logiciel
     */
    public DataBase()
    {
        adresse.add("37 Quai de Grenelle, 75015 Paris");
        adresse.add("Place de la Republique");        
        adresse.add("13 Rue d'Amsterdam, 75008 Paris");        
        adresse.add("8 Boulevard de Bercy, 75012 Paris");      
        cConnecte=null;
    }
    /**
     * Get CLient en fonction d'un login/mdp en utilisant la dao
     * @param login
     * @param mdp
     * @throws DAOConfigurationException
     * @throws FileNotFoundException 
     */
    public void getClient(String login, String mdp) throws DAOConfigurationException, FileNotFoundException
    {
        DAOFactory daofactory = DAOFactory.getInstance();
        ClientDaoImpl test= new ClientDaoImpl(daofactory);
        this.cConnecte=test.trouver(login, mdp);
        
    }
    /**
     * Ajout d'une voiture dans la database
     * @param voiture 
     */
    public void addVoiture(Voiture voiture){
        this.disponible.add(voiture);
    }
    /**
     * Retrun toutes les voitures disponibles pour les affichher plus tard
     * @return 
     */
    public ArrayList<Voiture> getdisponible(){
       return this.disponible; 
    } 
    public  ArrayList getAdresse(){
        return  adresse;
    }
    
    public void setLoginClientConnecte(String n){
        cConnecte.setLogin(n);
    }
    
    public void setMDPClientConnecte(String n)
    {
        cConnecte.setMDP(n);
    }
    
    public void setLoginEmployeConnecte(String n)
    {
        eConnecte.setLogin(n);
    }
    
    public void setMDPEmployeConnecte(String n)
    {
        eConnecte.setMDP(n);
    }
    
    public void setClientConnecte(Client c){
        cConnecte=c;
    }
    
    public void setEmployeConnecte(Employes e){
        eConnecte=e;
    }
    
    public Client getClientConnecte(){
       return  cConnecte;
    }
    
    public Employes getEmployeConnecte(){
        return eConnecte;
    }
    
    public String getTelClient(String l, String pw)
    {
        String resultat = "";
        for(int i = 0; i < registreClient.size(); i++)
        {
            if(registreClient.get(i).login.equals(l) && registreClient.get(i).mdp.equals(pw))
            {
                resultat = registreClient.get(i).telephone;
            }
            else
            {
                resultat = "Login ou mot de passe invalide";
            }
        }
        return resultat;
    }
    
    public String getDateDebut()
    {
        String dateD = String.valueOf(this.dateDebut);
        return dateD;
    }
    public void setDateDebut(LocalDate dateDebut){
        
        this.dateDebut=dateDebut;
    }
    
    public String getDateFin()
    {
        String dateF = String.valueOf(this.dateFin);
        return dateF;
    }
     public void setdateFin(LocalDate dateFin){
        
        this.dateFin = dateFin;
    }
    
    public String getAdresseSearch()
    {
        return adresseSearch;
    }
    public void setAdresseSearch(String adressesearch){
        this.adresseSearch = adressesearch;
    }
    
    public long getDuree()
    {
        Period period = Period.between(dateDebut, dateFin);
        int duree = (int)period.getDays();
        return duree;
    }
    
    public void setVoitureLoue(Voiture v){
        reservation=v;
    }
    public Voiture getVoitureLoue(){
        return reservation;
    }
    
    
}
