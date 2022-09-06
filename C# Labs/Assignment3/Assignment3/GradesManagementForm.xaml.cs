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
    /// Logique d'interaction pour GradesManagementForm.xaml
    /// </summary>
    public partial class GradesManagementForm : Window
    {
        Business_Form1 bf = new Business_Form1();

        public GradesManagementForm()
        {
            InitializeComponent();
            var course = bf.SelectCourse();
            for (int i = 0; i < course.Rows.Count; i++)
            {
                CourseComboBox.Items.Add(course.Rows[i]["CoursName"].ToString());
            }
            var student = bf.Select();
            for (int i = 0; i < student.Rows.Count; i++)
            {
                IDComboBox.Items.Add(student.Rows[i]["StudentId"].ToString());
            }
        }

        private void NewButton(object sender, RoutedEventArgs e)
        {
            gradeTextBox.Text = String.Empty;
            nameTextBox.Text = String.Empty;
        }

        private void AddButton(object sender, RoutedEventArgs e)
        {
            try
            {
                string studentID = IDComboBox.Text;
                string courseID = CourseComboBox.Text;
                var course = bf.SelectCourseIdOnName(courseID);

                string grade = gradeTextBox.Text;
                bf.AddGradeData(studentID, course.Rows[0]["CourseID"].ToString(), grade);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void EditButton(object sender, RoutedEventArgs e)
        {
            try
            {
                string studentID = IDComboBox.Text;
                string courseID = CourseComboBox.Text;
                var course = bf.SelectCourseIdOnName(courseID);

                string grade = gradeTextBox.Text;
                bf.UpdateGradeData(studentID, course.Rows[0]["CourseID"].ToString(), grade);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }

        }


        private void exitApplication(object sender, RoutedEventArgs e)
        {
            MessageBoxResult result = MessageBox.Show("Do you really want to do that?",
            "Warning", MessageBoxButton.YesNo, MessageBoxImage.Question);
            if (result == MessageBoxResult.Yes)
            {
                this.Close();//fermeture de la fenetre
            }
        }

        private void onIdDrop(object sender, EventArgs e)
        {
            try
            {
                int studentSearched = Int16.Parse(IDComboBox.Text);
                var student = bf.SelectOnId(studentSearched);
                nameTextBox.Text = student.Rows[0]["Name"].ToString();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void onCourseDrop(object sender, EventArgs e)
        {
            try
            {
                string studentSearched = IDComboBox.Text;
                string courseID = CourseComboBox.Text;
                var course = bf.SelectCourseIdOnName(courseID);

                var student = bf.SelectGradeOnId(studentSearched, course.Rows[0]["CoursID"].ToString());
                gradeTextBox.Text = student.Rows[0]["Grade"].ToString();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }

        }

        private void PreviewButton(object sender, RoutedEventArgs e)
        {
            string studentId = IDComboBox.Text;
            dgGrade.ItemsSource = bf.SelectGradeOfStudent(studentId).DefaultView;
        }
    }
}
