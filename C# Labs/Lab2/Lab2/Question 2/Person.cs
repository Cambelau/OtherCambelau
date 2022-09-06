using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab2.Question_2
{
    internal class Person
    {

        private String firstName, lastName, dateBirth;

        public Person(string firstName, string lastName, string dateBirth)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.dateBirth = dateBirth;
        }

        public virtual void Display()
        {
            Console.WriteLine( "Hello i am : " + firstName + " " + lastName + " and " + dateBirth);
        }
    }
}
