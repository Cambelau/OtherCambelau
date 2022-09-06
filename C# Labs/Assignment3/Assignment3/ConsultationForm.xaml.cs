using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using BusinessLogicLayer;

namespace Assignment3
{
    /// <summary>
    /// Logique d'interaction pour ConsultationForm.xaml
    /// </summary>
    public partial class ConsultationForm : Window
    {
        Business_Form1 bf = new Business_Form1();

        public ConsultationForm()
        {
            InitializeComponent();
            var course = bf.SelectCourse();
            for (int i = 0; i < course.Rows.Count; i++)
            {
                courseComboBox.Items.Add(course.Rows[i]["CoursName"].ToString());
            }
        }

        private void onDropCourse(object sender, EventArgs e)
        {
            try
            {
            string courseName = courseComboBox.Text;
            dgCourse.ItemsSource = bf.SelectCourseElement(courseName).DefaultView;

            }catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}
