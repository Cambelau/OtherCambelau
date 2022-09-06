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
    /// Logique d'interaction pour AppointmentManagement.xaml
    /// </summary>
    public partial class AppointmentManagement : Window
    {
        PatientDataContext dbPatient = new PatientDataContext();
        DoctorsDataContext dbDoctors = new DoctorsDataContext();
        AppointmentDataContext dbAppointment = new AppointmentDataContext();
        
        public AppointmentManagement()
        {
            InitializeComponent();
            //  dgPatient.ItemsSource = dbPatient.Patient.ToList();
            //  dgDoctors.ItemsSource = dbDoctors.Doctors.ToList();
            foreach(var patient in dbPatient.Patient.ToList())
            {
                PatientCodeComboBox.Items.Add(patient.PatientId);
            }
            foreach (var docteur in dbDoctors.Doctors.ToList())
            {
                DoctorCodeComboBox.Items.Add(docteur.DoctorId);
            }
        }
        private void NewButton(object sender, RoutedEventArgs e)
        {
            DoctorCodeComboBox.Text = String.Empty;
            PatientCodeComboBox.Text = String.Empty;
            PatientNameTextBox.Text = String.Empty;
            DoctorNameTextBox.Text = String.Empty;
            DoctorSpeTextBox.Text = String.Empty;
            FeminineRadioButton.IsChecked = false;
            MasculineRadioButton.IsChecked = false;

            AppDateTime.Text = DateTime.Today.ToString();
            AppTime.Text = "--/--";
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

        private void PatientCodeComboBox_DropDownClosed(object sender, EventArgs e)
        {
            try
            {
                //récupérer le code et le transformer en int
                int patientIdSearched = Int16.Parse(PatientCodeComboBox.Text);
                // requete sql du Patient recherché
                var queryPatient = from patient in dbPatient.Patient where patient.PatientId == patientIdSearched select patient;
                if (queryPatient.Count() == 0)
                    throw new NullReferenceException("Patient dont exist.");

                foreach (var patient in queryPatient)
                {
                    //remplir les champs 
                    PatientNameTextBox.Text = patient.PatientName;
                    if (patient.PatientGender.Equals("H"))
                        MasculineRadioButton.IsChecked = true;
                    else
                        FeminineRadioButton.IsChecked = true;
                }
            }
            catch (Exception ex)
            {
                errorText.Visibility = Visibility.Visible;
                errorText.Content += ex.Message;
            }
        }

        private void DoctorCodeComboBox_DropDownClosed(object sender, EventArgs e)
        {
            try
            {
                //récupérer le code et le transformer en int
                int doctorIdSearched = Int16.Parse(DoctorCodeComboBox.Text);
                // requete sql du Patient recherché
                var queryDoctor = from doctor in dbDoctors.Doctors where doctor.DoctorId == doctorIdSearched select doctor;
                if (queryDoctor.Count() == 0)
                    throw new NullReferenceException("Doctor dont exist.");

                foreach (var doctor in queryDoctor)
                {
                    //remplir les champs 
                    DoctorNameTextBox.Text = doctor.DoctorName;
                    DoctorSpeTextBox.Text = doctor.DoctorSpecialism;
                }
            }
            catch (Exception ex)
            {
                errorText.Visibility = Visibility.Visible;
                errorText.Content += ex.Message;
            }

        }
        private void resetErrorMessage()
        {
            errorText.Visibility = Visibility.Hidden;
            errorText.Content = "Error Happened ! ";
        }
        private void AddAppointmentButton(object sender, RoutedEventArgs e)
        {
            resetErrorMessage();
            try
            {
                Appointment app = new Appointment();


                //verification : les champs sont remplis
                if (String.IsNullOrEmpty(DoctorCodeComboBox.Text) ||
                    String.IsNullOrEmpty(PatientCodeComboBox.Text) ||
                    String.IsNullOrEmpty(PatientNameTextBox.Text) ||
                    String.IsNullOrEmpty(PatientNameTextBox.Text) ||
                    String.IsNullOrEmpty(DoctorNameTextBox.Text) ||
                    String.IsNullOrEmpty(AppDateTime.Text) ||
                    String.IsNullOrEmpty(AppTime.Text) ||
                    (FeminineRadioButton.IsChecked != true && MasculineRadioButton.IsChecked != true) ||
                    String.IsNullOrEmpty(DoctorSpeTextBox.Text)
                    )
                    throw new NullReferenceException("Fill all element.");

                //inscirption dans la db
                int doctorIdSearched = Int16.Parse(DoctorCodeComboBox.Text);
                int patientIdSearched = Int16.Parse(PatientCodeComboBox.Text);
                app.PatientId = patientIdSearched;
                app.DoctorId = doctorIdSearched;
                app.AppointmentDate = AppDateTime.Text;
                app.AppointmentTime = AppTime.Text;
                app.AppointmentCode = dbAppointment.Appointment.ToList().Last().AppointmentCode+1;
                dbAppointment.Appointment.InsertOnSubmit(app);
                dbAppointment.SubmitChanges();

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
                errorText.Visibility = Visibility.Visible;
                errorText.Content += ex.Message;
            }

        }
    }
}
