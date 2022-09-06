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
using System.Windows.Navigation;
using System.Windows.Shapes;
using BusinessLogicLayer;


namespace Assignment3
{
    /// <summary>
    /// Logique d'interaction pour MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        void MainWindow_Load(object sender, EventArgs e)
        {
            try
            {
                Business_Form1 bf = new Business_Form1();               
            }
            catch (Exception ex)
            { 
                MessageBox.Show(ex.Message);    
            }
        }

        private void exitApplication(object sender, RoutedEventArgs e)
        {
            //MessageBox.Show("App closing");
            //System.Windows.Application.Current.Shutdown();
            MessageBoxResult result = MessageBox.Show("Do you really want to do that?",
            "Warning", MessageBoxButton.YesNo, MessageBoxImage.Question);
            if (result == MessageBoxResult.Yes)
            {
                System.Windows.Application.Current.Shutdown();
            }
        }

        private void OpenGradesManagementWindow(object sender, RoutedEventArgs e)
        {
            GradesManagementForm gradeForm = new GradesManagementForm();
            gradeForm.Show();
        }

        private void OpenStudentsManagementWindow(object sender, RoutedEventArgs e)
        {
            StudentsManagementForm studentForm = new StudentsManagementForm();
            studentForm.Show();
            
        }

        private void OpenConsultationManagement(object sender, RoutedEventArgs e)
        {
            ConsultationForm consultationForm = new ConsultationForm();
            consultationForm.Show();

        }
    }
}
