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
    /// Logique d'interaction pour StudentsManagementForm.xaml
    /// </summary>
    public partial class StudentsManagementForm : Window
    {
        Business_Form1 bf = new Business_Form1();
        public StudentsManagementForm()
        {
            InitializeComponent();
        }

        private void endButton(object sender, RoutedEventArgs e)
        {
            try
            {
                resetErrorMessage();
                var student = bf.Select();
                IdTextBox.Text = student.Rows[student.Rows.Count - 1]["StudentId"].ToString();
                nameTextBox.Text = student.Rows[student.Rows.Count - 1]["Name"].ToString();
                familyTextBox.Text = student.Rows[student.Rows.Count - 1]["Family"].ToString();
                birthDateTime.Text = student.Rows[student.Rows.Count - 1]["BirthDate"].ToString();

            }
            catch (Exception ex)
            {
                errorText.Visibility = Visibility.Visible;
                errorText.Content += ex.Message;
            }

        }

        private void nextButton(object sender, RoutedEventArgs e)
        {
            try
            {
                resetErrorMessage();
                int studentSearched = Int16.Parse(IdTextBox.Text) +1;
                var student = bf.SelectOnId(studentSearched);
                IdTextBox.Text = student.Rows[0]["StudentId"].ToString();
                nameTextBox.Text = student.Rows[0]["Name"].ToString();
                familyTextBox.Text = student.Rows[0]["Family"].ToString();
                birthDateTime.Text = student.Rows[0]["BirthDate"].ToString();
            }
            catch (Exception ex)
            {
                errorText.Visibility = Visibility.Visible;
                errorText.Content = "There is no next";
            }
        }

        private void previousButton(object sender, RoutedEventArgs e)
        {
            try
            {
                resetErrorMessage();
                int studentSearched = Int16.Parse(IdTextBox.Text) - 1;
                var student = bf.SelectOnId(studentSearched);
                IdTextBox.Text = student.Rows[0]["StudentId"].ToString();
                nameTextBox.Text = student.Rows[0]["Name"].ToString();
                familyTextBox.Text = student.Rows[0]["Family"].ToString();
                birthDateTime.Text = student.Rows[0]["BirthDate"].ToString();
            }
            catch (Exception ex)
            {
                errorText.Visibility = Visibility.Visible;
                errorText.Content = "There is no previous";
            }

        }

        private void startButton(object sender, RoutedEventArgs e)
        {
            try
            {
                resetErrorMessage();
                var student = bf.Select();
                IdTextBox.Text = student.Rows[0]["StudentId"].ToString();
                nameTextBox.Text = student.Rows[0]["Name"].ToString();
                familyTextBox.Text = student.Rows[0]["Family"].ToString();
                birthDateTime.Text = student.Rows[0]["BirthDate"].ToString();

            }
            catch (Exception ex)
            {
                errorText.Visibility = Visibility.Visible;
                errorText.Content +=ex.Message;
            }
        }

        private void NewButton(object sender, RoutedEventArgs e)
        {
            //new button reset all textbox with empty string
            familyTextBox.Text = String.Empty;
            IdTextBox.Text = String.Empty;
            nameTextBox.Text = String.Empty;    
            birthDateTime.Text = DateTime.Today.ToString();
            resetErrorMessage();
        }

        private void SearchButton(object sender, RoutedEventArgs e)
        {
            try
            {
                resetErrorMessage();
                int studentSearched = Int16.Parse(IdTextBox.Text);
                var student = bf.SelectOnId(studentSearched);
                IdTextBox.Text = student.Rows[0]["StudentId"].ToString();
                nameTextBox.Text = student.Rows[0]["Name"].ToString();
                familyTextBox.Text = student.Rows[0]["Family"].ToString();
                birthDateTime.Text = student.Rows[0]["BirthDate"].ToString();
            }
            catch (Exception ex)
            {
                errorText.Visibility = Visibility.Visible;
                errorText.Content += ex.Message;
            }
        }

        private void AddButton(object sender, RoutedEventArgs e)
        {
            try
            {
                resetErrorMessage();
                bf.AddData(IdTextBox.Text, nameTextBox.Text, familyTextBox.Text, birthDateTime.Text);

            }
            catch (Exception ex)
            {
                errorText.Visibility = Visibility.Visible;
                errorText.Content += ex.Message;
            }
        }

        private void EditButton(object sender, RoutedEventArgs e)
        { 
            try
            {
                resetErrorMessage();
                bf.UpdateData(IdTextBox.Text, nameTextBox.Text, familyTextBox.Text, birthDateTime.Text);

            }
            catch (Exception ex)
            {
                errorText.Visibility = Visibility.Visible;
                errorText.Content += ex.Message;
            }
        }

        private void DeleteButton(object sender, RoutedEventArgs e)
        {
            try
            {
                resetErrorMessage();
                bf.DeleteData(IdTextBox.Text);

            }
            catch (Exception ex)
            {
                errorText.Visibility = Visibility.Visible;
                errorText.Content += ex.Message;
            }

        }

        private void exitApplication(object sender, RoutedEventArgs e)
        {
            MessageBoxResult result = MessageBox.Show("Do you really want to do that?",
            "Warning", MessageBoxButton.YesNo, MessageBoxImage.Question);
            if (result == MessageBoxResult.Yes)
            {
                bf.deco();
                this.Close();//fermeture de la fenetre
            }
        }

        private void resetErrorMessage()
        {
            errorText.Visibility = Visibility.Hidden;
            errorText.Content = "Error Happened ! ";
        }

    }
}
