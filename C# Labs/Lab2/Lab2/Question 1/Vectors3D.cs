using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab2
{
    internal class Vectors3D : Vectors2D
    {
        private int Z;

        public Vectors3D(int X, int Y,int Z, int vectorNumbers) : base(X, Y, vectorNumbers)
        {
            this.X = X;
            this.Y = Y;
            this.Z = Z;
            this.vectorNumbers = vectorNumbers;
        }

        public override bool Equals(object obj)
        {
            return obj is Vectors3D d &&
                   X == d.X &&
                   Y == d.Y &&
                   Z == d.Z;
        }

        public override string ToString()
        {
            return "X = " + X + "- Y = " + Y + " - Z = " + Z;
        }
        private double normVector()
        {
            double norm = Math.Sqrt((X * X) + (Y * Y) + (Z * Z));
            return norm;
        }
    }
}
