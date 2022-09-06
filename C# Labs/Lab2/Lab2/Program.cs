using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab2
{
    internal class Program
    {
        static void Main(string[] args)
        {

            Vectors2D MonpetitVecteur = new Vectors2D(5, 6, 0);
            Vectors2D MonpetitVecteur2 = new Vectors2D(5, 6, 0);


            Console.WriteLine(MonpetitVecteur.ToString());
            Console.WriteLine(MonpetitVecteur.Equals(MonpetitVecteur2));
            Console.WriteLine(MonpetitVecteur2.ToString());

            Console.ReadLine();
        }
    }
}
