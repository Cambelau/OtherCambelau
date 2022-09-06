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

namespace Assigment2
{
    /// <summary>
    /// Logique d'interaction pour ShowAll.xaml
    /// </summary>
    public partial class ShowAll : Window
    {
        PatientDataContext dbPatient = new PatientDataContext();
        DoctorsDataContext dbDoctors = new DoctorsDataContext();
        AppointmentDataContext dbAppointment = new AppointmentDataContext();
        public ShowAll()
        {
            InitializeComponent();
            dgPatient.ItemsSource = dbPatient.Patient.ToList();
            dgDoctors.ItemsSource = dbDoctors.Doctors.ToList();
            dgAppointment.ItemsSource = dbAppointment.Appointment.ToList();
        }

        private void Doctor_Checked(object sender, RoutedEventArgs e)
        {
            dgPatient.Visibility = Visibility.Hidden;
            dgDoctors.Visibility = Visibility.Visible;
            dgAppointment.Visibility = Visibility.Hidden;
        }

        private void Patient_Checked(object sender, RoutedEventArgs e)
        {
            dgPatient.Visibility = Visibility.Visible;
            dgDoctors.Visibility = Visibility.Hidden;
            dgAppointment.Visibility = Visibility.Hidden;

        }

        private void App_Checked(object sender, RoutedEventArgs e)
        {
            dgPatient.Visibility = Visibility.Hidden;
            dgDoctors.Visibility = Visibility.Hidden;
            dgAppointment.Visibility = Visibility.Visible;

        }
    }
}
