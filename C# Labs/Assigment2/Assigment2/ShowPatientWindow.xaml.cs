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
    /// Logique d'interaction pour ShowPatientWindow.xaml
    /// </summary>
    public partial class ShowPatientWindow : Window
    {
        PatientDataContext dbPatient = new PatientDataContext();
        AppointmentDataContext dbAppointment = new AppointmentDataContext();

        public ShowPatientWindow()
        {
            InitializeComponent();
        }

        private void resetErrorMessage()
        {
            errorText.Visibility = Visibility.Hidden;
            errorText.Content = "Error Happened ! ";
        }

        private void SearchButton(object sender, RoutedEventArgs e)
        {
            resetErrorMessage();
            try
            {
                //récupérer le code et le transformer en int
                int patientIdSearched = Int16.Parse(PatientCodeTextBox.Text);
                // requete sql du Patient recherché
                var queryPatient = from patient in dbPatient.Patient where patient.PatientId == patientIdSearched select patient;
                if (queryPatient.Count() == 0)
                    throw new NullReferenceException("Patient dont exist.");

                foreach (var patient in queryPatient)
                {
                    //remplir les champs 
                    PatientNameTextBox.Text = patient.PatientName;
                    AddressTextBox.Text = patient.PatientAdress;
                    var parsedDate = DateTime.Parse(patient.BirthDate);
                    BirthDateTextBox.Text = parsedDate.ToString();
                }

                // requete sql du Patient recherché
                var queryAppointment = from app in dbAppointment.Appointment where app.PatientId == patientIdSearched select app;
                dgAppointment.ItemsSource = queryAppointment;
            }
            catch (Exception ex)
            {
                //en cas d'erreur affichage du message d'erreur
                errorText.Visibility = Visibility.Visible;
                errorText.Content += ex.Message;
            }
        }

        private void CancelButton(object sender, RoutedEventArgs e)
        {
            MessageBoxResult result = MessageBox.Show("Do you really want to do that?",
            "Warning", MessageBoxButton.YesNo, MessageBoxImage.Question);
            if (result == MessageBoxResult.Yes)
            {
                this.Close();//fermeture de la fenetre
            }
        }

        private void dataGrid_SelectedCellsChanged(object sender, SelectedCellsChangedEventArgs e)
        {
            Appointment app = new Appointment();
            foreach (var obj in dgAppointment.SelectedItems)
            {
                app = obj as Appointment;
                codeTextBox.Text = app.AppointmentCode.ToString();
                dateTextBox.Text = app.AppointmentDate.ToString();  
                docTextBox.Text = app.DoctorId.ToString();
                timeTextBox.Text = app.AppointmentTime.ToString();

            }
        }
    }
}
