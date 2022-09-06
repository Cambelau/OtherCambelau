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
    /// Logique d'interaction pour SearchAppointmentWindow.xaml
    /// </summary>
    public partial class SearchAppointmentWindow : Window
    {
        PatientDataContext dbPatient = new PatientDataContext();
        DoctorsDataContext dbDoctors = new DoctorsDataContext();
        AppointmentDataContext dbAppointment = new AppointmentDataContext();
        public SearchAppointmentWindow()
        {
            InitializeComponent();
        }

        private void OkButton(object sender, RoutedEventArgs e)
        {
            try
            {
                //récupérer le code et le transformer en int
                String appDate = DateSearchTextBox.Text;
                // requete sql du Patient recherché
                var queryAppointment = from app in dbAppointment.Appointment where app.AppointmentDate == appDate select app;
                dgAppointment.ItemsSource = queryAppointment;
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);    
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
            try
            {

            Appointment app = new Appointment();
            foreach (var obj in dgAppointment.SelectedItems)
            {
                app = obj as Appointment;
                var queryDoctor = from doct in dbDoctors.Doctors where doct.DoctorId == app.DoctorId select doct;

                foreach (var doctor in queryDoctor)
                {
                    //remplir les champs 
                    speDocTextBox.Text = doctor.DoctorSpecialism;
                    nameDocTextBox.Text = doctor.DoctorName;
                }
                var queryPatient = from patient in dbPatient.Patient where patient.PatientId == app.PatientId select patient;

                    foreach (var patient in queryPatient)
                    {
                        //remplir les champs 
                        namePatientTextBox.Text = patient.PatientName;
                        birthPatientTextBox.Text = patient.BirthDate;
                    }

                }
            }catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}
