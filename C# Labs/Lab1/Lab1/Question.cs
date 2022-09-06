using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab1
{
    public class Question
    {
        public void question1()
        {
            /*    Console.WriteLine(Hello");
                Console.ReadLine();*/

            while (true)
            {
                Console.WriteLine("---------------------------------------------------");
                Console.Write("Price ? ");
                int price = int.Parse(Console.ReadLine());
                Console.Write("Paid ? ");
                int paid = int.Parse(Console.ReadLine());
                int change = price - paid;
                Console.WriteLine("Var : paid : " + price + " price : " + paid);
                Console.Write("Your change is : " + change + " : ");
                do
                {
                    if (change + 100 <= 0)
                    {
                        change += 100;
                        Console.Write(" 100 ");
                    }
                    else if (change + 50 <= 0)
                    {
                        change += 50;
                        Console.Write(" 50 ");
                    }
                    else if (change + 20 <= 0)
                    {
                        change += 20;
                        Console.Write(" 20 ");
                    }
                    else if (change + 5 <= 0)
                    {
                        change += 5;
                        Console.Write(" 5 ");
                    }
                    else if (change + 2 <= 0)
                    {
                        change += 2;
                        Console.Write(" 2 ");
                    }
                    else if (change + 1 <= 0)
                    {
                        change += 1;
                        Console.Write(" 1 ");
                    }

                    /* Console.Write(",");*/
                } while (change != 0);
                Console.ReadLine();
            }
        }
        public void question2()
        {
            for (int i = 0; i < 7; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    if (((j == 2 || j == 7) && i != 3 && i != 2 && i != 4))
                        Console.Write("*");
                    else if ((i == 4 || i == 2) && (j == 3 || j == 6))
                        Console.Write("*");
                    else if (j == 5 && i == 3)
                        Console.Write("*");
                    else
                        Console.Write(" ");
                }
                Console.WriteLine();
            }
        }
        public void question3()
        {
            int[] data = new int[10];
            Console.WriteLine("Enter the number of data " + data.Length);
            for (int i = 0; i < data.Length; i++)
            {
                Console.Write("Data[" + (i + 1) + "] :");
                data[i] = Int32.Parse(Console.ReadLine());
            }

            int sum = 0;
            for (int i = 0; i < data.Length; i++)
                sum += data[i];
            Console.WriteLine("Moyenne : " + (sum / data.Length));

            int median = data.Length / 2;
            if (data.Length % 2 == 1)
                Console.WriteLine("Median : " + data[median]);
            else
                Console.WriteLine("Median : " + ((data[median - 1] + data[median]) / 2.0));

            int maxValue = 0, maxCount = 0;
            for (int i = 0; i < data.Length; ++i)
            {
                int count = 0;
                for (int j = 0; j < data.Length; ++j)
                {
                    if (data[j] == data[i]) ++count;
                }
                if (count > maxCount)
                {
                    maxCount = count;
                    maxValue = data[i];
                }
            }
            Console.WriteLine("Mode : " + maxValue);

        }

        public void question4()
        {
            String choice = "";
            int var = 0;
            Console.WriteLine("Choose a conversion to do : ");
            choice = Console.ReadLine();
            switch (choice)
            {
                case "I":
                    Console.WriteLine("Convertion of inches to centimeter");
                    Console.Write("Enter number : ");
                    var = Int32.Parse(Console.ReadLine());
                    Console.WriteLine("Result is : "+ (var*2.54));
                    break;
                case "G":
                    Console.WriteLine("Convertion of gallons to litter");
                    Console.Write("Enter number : ");
                    var = Int32.Parse(Console.ReadLine());
                    Console.WriteLine("Result is : " + (var * 3.7854));
                    break;
                case "M":
                    Console.WriteLine("Convertion of miles to kilometer");
                    Console.Write("Enter number : ");
                    var = Int32.Parse(Console.ReadLine());
                    Console.WriteLine("Result is : " + (var * 1.60934));
                    break;
                case "P":
                    Console.WriteLine("Convertion of pounds to kilogrm");
                    Console.Write("Enter number : ");
                    var = Int32.Parse(Console.ReadLine());
                    Console.WriteLine("Result is : " + (var * 0.453592));
                    break;
                default:
                    Console.WriteLine();
                    break;
            }
        }

        public void question5()
        {
            Console.Write("Enter your age : ");
            int age = Int32.Parse(Console.ReadLine());
            getInt(age, 0, 150);
        }
        public void getInt(int age,int min, int max)
        {
            if(age < min)
                Console.WriteLine("Not a valid answer age must be more than : " + min);
            if(age > max)   
                Console.WriteLine("Not a valid answer age must be less than : " + max);
        }

        public void question6()
        {

            decimal f;
            Console.Write("Input a number : ");
            int num = Convert.ToInt32(Console.ReadLine());
            f = Factorial(num);
            Console.WriteLine("The factorial of {0}! is  {1}", num, f);
        }
        public decimal Factorial(int n1)
        {
            // The bottom of the recursion
            if (n1 == 0)
            {
                return 1;
            }
            // Recursive call: the method calls itself
            else
            {
                return n1 * Factorial(n1 - 1);
            }
        }
    }

}
