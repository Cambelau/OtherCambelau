using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
     abstract class Bien
    {

        protected int value;
        protected int cost_entretien;
        protected int item_number;

        public Bien(int value, int cost_entretien, int item_number)
        {
            this.value = value;
            this.cost_entretien = cost_entretien;
            this.item_number = item_number;
        }

        public Bien()
        {
            this.value = 0;
            this.cost_entretien = 0;
            this.item_number = 0;
        }

        public Bien(Bien previousBien)
        {
            this.value = previousBien.value;
            this.cost_entretien = previousBien.cost_entretien;
            this.item_number = previousBien.item_number;
        }

        public int getValue()
        {
            return value;
        }
        public int getCost_entretien()
        {
            return cost_entretien;
        }
        public int getItem_number()
        {
            return item_number;
        }

        public void setValue(int v)
        {
            this.value=v;
        }
        public void setCost_entretien(int c)
        {
            this.cost_entretien = c;
        }
        public void setItem_number(int v)
        {
            this.item_number = v;
        }

        public abstract String getInfo();

 
    }
}
