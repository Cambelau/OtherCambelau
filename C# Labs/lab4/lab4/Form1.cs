using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace lab4
{
    public partial class Form1 : Form
    {
        SqlConnection connection = new SqlConnection();
        SqlCommand command = new SqlCommand();
        DataSet dataSet = new DataSet();
        SqlDataAdapter dataAdapter = new SqlDataAdapter();
        CurrencyManager cr;


        public Form1()
        {
            InitializeComponent();
 
        }

        private void Connected_Click(object sender, EventArgs e)
        {
            if (connection.State != ConnectionState.Open)
            {
                connection.ConnectionString = @"Data Source=DESKTOP-MATTHIE\SQLEXPRESS;Initial Catalog=test;Integrated Security=True";
                connection.Open();
                MessageBox.Show(connection.State.ToString());
            }
            else
                MessageBox.Show("Already Connected");
        }

        private void Display_Click(object sender, EventArgs e)
        {
            string query1 = "select * from tbl1";
            command.CommandText = query1;
            command.Connection = connection;
            dataAdapter.SelectCommand = command;

            //clear the dataset
            // dataSet.Clear();
            dataAdapter.Fill(dataSet,"T1");
            dataGridView1.DataBindings.Clear();
            dataGridView1.DataBindings.Add("datasource", dataSet, "T1");



            //clear databinding of datagridview
           // dataGridView1.DataBindings.Clear();
            //adding the contains of dataset to datagridview
          //  dataGridView1.DataBindings.Add("datasource", dataSet, "T1");
            //clear databinding of textbox
          /*  txtId.DataBindings.Clear();
            txtId.DataBindings.Add("text", dataSet, "T1.Id");
            txtName.DataBindings.Clear();
            txtName.DataBindings.Add("text", dataSet, "T1.Name");
            txtfamily.DataBindings.Clear0);
            dutéani›v: Dat - Birdönnaines.Ctentig,dataset,"T2.Family"):*/
           // cr = (CurrencyManager)this.BindingContext[dataSet, "T1"]);

        }

        private void Clear_Click(object sender, EventArgs e)
        {

        }

        private void Disconnected_Click(object sender, EventArgs e)
        {

        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {
            // TODO: cette ligne de code charge les données dans la table 'customerDataSet.Table'. Vous pouvez la déplacer ou la supprimer selon les besoins.
            this.tableTableAdapter.Fill(this.customerDataSet.Table);

        }
    }
}
