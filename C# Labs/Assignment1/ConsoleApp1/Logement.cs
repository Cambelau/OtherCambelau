using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
     class Logement : Bien
    {
        private int nombre_Personne;
        private int superficie;
        public int Nombre_personne
        {
            get { return this.nombre_Personne; }
            set { this.nombre_Personne = Nombre_personne; }
        }
        public int Superficie
        {
            get { return superficie; }
            set { this.superficie = Superficie; }
        }
        public Logement(int i, int p, int value, int cost_entretien, int item_number) : base(value, cost_entretien, item_number)
        {
            this.Nombre_personne = i;
            this.Superficie = p;
        }
        public Logement()
        {
            this.Nombre_personne = 0;
            this.Superficie = 0;
        }
        public Logement(Logement previousLogement)
        {
            this.Nombre_personne = previousLogement.Nombre_personne;
            this.Superficie = previousLogement.Superficie;
            this.value = previousLogement.value;
            this.cost_entretien = previousLogement.cost_entretien;
            this.item_number = previousLogement.item_number;
        }
        public override String getInfo()
        {
            return "Les informations du logement sont : \nValeur : " + this.value + "\nCoup d'entretien : " 
                + this.cost_entretien + "\nNombre de bien : " + this.item_number + "\nSueperficie : "+ this.Superficie 
                + "\nNombe de personnes : " + this.Nombre_personne;
        }
    }
}
