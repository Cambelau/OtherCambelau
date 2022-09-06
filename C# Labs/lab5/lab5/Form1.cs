using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace lab5
{
    public partial class Form1 : Form
    {
        DataClasses1DataContext db = new DataClasses1DataContext();
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            dataGridView1.DataSource = db.Students.ToList();
            comboBox1.Text = "Select first filter";
            comboBox2.Text = "Select second filter";

            int i = 0;
            do
            {
                comboBox1.Items.Add(dataGridView1.Columns[i].Name.ToString());
                comboBox2.Items.Add(dataGridView1.Columns[i].Name.ToString());
                i++;
            } while (i != dataGridView1.ColumnCount);
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {

        }

        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
        }

        private void button5_Click(object sender, EventArgs e)
        {
            
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            //SEARCH Button
            dataGridView1.ClearSelection();
            // query
            var query = from students in db.Students
                        where students.Name == textBox1.Text
                        select students;

            dataGridView1.DataSource = query;
        }
    }
}
