using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
     class Vehicule : Bien
    {
        private String immatriculation;
        private int placeMax;
        public String Immatriculation
        {
            get { return this.immatriculation; }  
            set { this.immatriculation = Immatriculation; }
        }
        public int PlaceMax
        {
            get { return this.placeMax; }
            set { this.placeMax = PlaceMax; }
        }
        public Vehicule(string i, int p,int value, int cost_entretien, int item_number) :base(value,cost_entretien,item_number)
        {
            this.Immatriculation = i;
            this.PlaceMax = p;
        }
        public Vehicule()
        {
            this.Immatriculation = "";
            this.PlaceMax = 0;
        }
        public Vehicule(Vehicule previousVehicule)
        {
            this.Immatriculation = previousVehicule.Immatriculation;
            this.PlaceMax = previousVehicule.PlaceMax;
            this.value = previousVehicule.value;
            this.cost_entretien = previousVehicule.cost_entretien;
            this.item_number = previousVehicule.item_number;
        }
        public override String getInfo()
        {
            return "Les informations du véhicule sont : \nValeur : " + value + "\nCoup d'entretien : " 
                + cost_entretien + "\nNombre de bien : " + item_number + "\nImmatriculation : "+ Immatriculation 
                + "\nNombe de véhicule : " + item_number;
        }
}
}
