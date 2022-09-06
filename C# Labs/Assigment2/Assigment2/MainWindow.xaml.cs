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

namespace Assigment2
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

        private void OpenDoctorManagementWindow(object sender, RoutedEventArgs e)
        {
            DoctorManagement doctorManagementWindow = new DoctorManagement();
            doctorManagementWindow.Show();
        }

        private void OpenPatientManagementWindow(object sender, RoutedEventArgs e)
        {
            PatientManagement patientManagementWindow = new PatientManagement();
            patientManagementWindow.Show();
        }

        private void OpenAppointementManagementWindow(object sender, RoutedEventArgs e)
        {
            AppointmentManagement appointmentManagementWindow = new AppointmentManagement();
            appointmentManagementWindow.Show();
        }

        private void ShowAllWindow(object sender, RoutedEventArgs e)
        {
            ShowAll ShowAllWindow = new ShowAll();
            ShowAllWindow.Show();
        }

        private void SearchAppWindow(object sender, RoutedEventArgs e)
        {
            SearchAppointmentWindow searchAppWindow = new SearchAppointmentWindow();    
            searchAppWindow.Show(); 
        }

        private void ShowPatientWindow(object sender, RoutedEventArgs e)
        {
            ShowPatientWindow showPatientWindow = new ShowPatientWindow();
            showPatientWindow.Show();
        }
    }
}
