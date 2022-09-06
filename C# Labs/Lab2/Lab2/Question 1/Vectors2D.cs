using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab2
{
    internal class Vectors2D
    {

        protected int X, Y, vectorNumbers;

        public Vectors2D(int X, int Y, int vectorNumbers)
        {
            this.X = X;
            this.Y = Y;
            this.vectorNumbers = vectorNumbers;
        }

        public override bool Equals(object obj)
        {
            return obj is Vectors2D d &&
                   X == d.X &&
                   Y == d.Y;
        }

        public override string ToString()
        {
            return "X = " + X + " - Y = " + Y;
        }

        private double normVector()
        {
            double norm = Math.Sqrt((X*X)+(Y*Y));
            return norm;
        }
    }
}
