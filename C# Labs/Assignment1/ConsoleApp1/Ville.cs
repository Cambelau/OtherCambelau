using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace ConsoleApp1
{
    [Serializable]
    class Ville
    {

        static List<Bien> element = new List<Bien>();

        public Ville(List<Bien> elem)
        {
            element = elem;
        }
        public void addVehicule(Vehicule newvehicule)
        {
            element.Add(newvehicule);
        }
        public void addLogement(Vehicule newlogement)
        {
            element.Add(newlogement);
        }
        public Ville()
        {
            Logement bien1 = new Logement(0, 1, 2, 3, 4);
            element.Add(bien1);
        }

        private String getInfoBien(int index)
        {
            return element[index].getInfo();
        }
        
        private int getNombreTotalVehicule()
        {
            int res = 0;
            foreach (Bien elem in element)
            {
                if (elem.Equals(typeof(Vehicule)))
                    res++;
            }
                return res;
        }
        private int getCoutEntretienTotal()
        {
            int res = 0;
            foreach (Bien elem in element)
            {
                res+=elem.getCost_entretien();
            }
            return res;
        }

        private int getNombrePersonneTotal()
        {
            int res = 0;
            foreach (Logement elem in element)
            {
                res+=elem.Nombre_personne;
            }
            return res;
        }

        private int getCoutEntretienTotalVehicule()
        {
            int res = 0;
            foreach (Vehicule elem in element)
            {
                if (elem.Equals(typeof(Vehicule)))
                    res += elem.getCost_entretien();
            }
            return res;
        }

        public static void enregisterBien(string nom_fichier)
        {
           /* FileStream f = File.Open(nom_fichier, FileMode.Open);
            XmlSerializer s = new XmlSerializer(typeof(List<Bien>));
            s.Serialize(f, element);
            f.Close();*/
            System.Xml.Serialization.XmlSerializer writer =
                 new System.Xml.Serialization.XmlSerializer(typeof(List<Bien>));

            var path = Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments) 
                + "C://Users//Owner//Documents//Life//ECE//~ING 4.2//C#//ConsoleApp1//ConsoleApp1//test.xml";

            System.IO.FileStream file = System.IO.File.Create(path);

            writer.Serialize(file, element);
            file.Close();
        }
    }
}
