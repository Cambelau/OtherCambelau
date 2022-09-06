using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using System.Data.SqlClient;


namespace DataAccessLayer
{
    public class Data_Access
    {
        SqlConnection conn;
        SqlCommand comm;
        SqlDataAdapter da;
        DataTable dt;

        public Data_Access()
        {
            conn = new SqlConnection();
            comm = new SqlCommand();
            comm.Connection = conn;
            da = new SqlDataAdapter();
            da.SelectCommand = comm;
        }

        public void Link()
        {
            conn.ConnectionString =
            @"Data Source =DESKTOP-MATTHIE\SQLEXPRESS;Initial Catalog=GradeManagement;Integrated Security=True";
            conn.Open();
        }

        public void unLink()
        {
            conn.Close();
        }

        public DataTable SelectData(string strsql)
        {
            comm.CommandText = strsql;
            dt = new DataTable();
            da.Fill(dt);
            return dt;
        }

        public void addData(string strsql)
        {
            comm.CommandText = strsql;
            da.InsertCommand = comm;
            da.InsertCommand.ExecuteNonQuery();
        }

        public void updateData(string strsql)
        {
            comm.CommandText = strsql;
            da.UpdateCommand = comm;
            da.UpdateCommand.ExecuteNonQuery();
        }

        public void deleteData(string strsql)
        {
            comm.CommandText = strsql;
            da.DeleteCommand = comm;
            da.DeleteCommand.ExecuteNonQuery();
        }
    }
}
