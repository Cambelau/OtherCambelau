using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab2.Question_2
{
    internal class Employe : Person
    {
        private int salary;
        public Employe(string firstName, string lastName, string dateBirth)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.dateBirth = dateBirth;

        }

        public new void Display()
        {
            Console.WriteLine( "Salary : " + salary);

        }
}
}
