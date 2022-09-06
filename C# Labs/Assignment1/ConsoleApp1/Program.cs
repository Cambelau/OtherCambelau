using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Ville maville = new Ville();
           // maville.enregisterBien(@"newfile.xml");

            String choice;
            do { 
            Console.WriteLine("Choose a action to do : (0 ajouter Vehicule,1 ajouter Logement,2)");
            choice = Console.ReadLine();
                 switch (choice)
                 {
                        case "0":
                        Console.WriteLine("Add new Vehicule : immat, place max, value, cout entretien, nombre de vehicule ");
                        choice = Console.ReadLine(); 
                        Vehicule newcar = new Vehicule();
                        newcar.getInfo(); 
                        maville.addVehicule(newcar); 
                        break;
                        case "1":
                        Console.WriteLine("Add new Logement : superficie, nombre de personne, value, cout entretien, nombre de vehicule ");
                        choice = Console.ReadLine();
                        Vehicule newlog = new Vehicule();
                        maville.addLogement(newlog);
                        break;

                }
            } while (true);
        }
    }
}
