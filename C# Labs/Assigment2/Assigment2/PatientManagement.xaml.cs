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
    /// Logique d'interaction pour PatientManagement.xaml
    /// </summary>
    public partial class PatientManagement : Window
    {
        PatientDataContext dbPatient = new PatientDataContext();

        public PatientManagement()
        {
            InitializeComponent();
        }
        private void resetErrorMessage()
        {
            errorText.Visibility = Visibility.Hidden;
            errorText.Content = "Error Happened ! ";
        }

        private void NewButton(object sender, RoutedEventArgs e)
        {
            //new button reset all textbox with empty string
            CodeTextBox.Text = String.Empty;
            NameTextBox.Text = String.Empty;
            AddressTextBox.Text = String.Empty;
            BirthDateDateTime.Text = DateTime.Today.ToString();
            FeminineRadioButton.IsChecked = false;
            MasculineRadioButton.IsChecked = false;
            resetErrorMessage();

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

        private void SearchButton(object sender, RoutedEventArgs e)
        {
            resetErrorMessage();
            try
            {
                //récupérer le code et le transformer en int
                int patientIdSearched = Int16.Parse(CodeTextBox.Text);
                // requete sql du Patient recherché
                var queryPatient = from patient in dbPatient.Patient where patient.PatientId == patientIdSearched select patient;
                if (queryPatient.Count() == 0)
                    throw new NullReferenceException("Patient dont exist.");

                foreach (var patient in queryPatient)
                {
                    //remplir les champs 
                    NameTextBox.Text = patient.PatientName;
                    AddressTextBox.Text = patient.PatientAdress;
                    var parsedDate = DateTime.Parse(patient.BirthDate);
                    BirthDateDateTime.Text = parsedDate.ToString();
                    if(patient.PatientGender.Equals("H"))
                        MasculineRadioButton.IsChecked=true;
                    else
                        FeminineRadioButton.IsChecked=true;
                }
            }
            catch (Exception ex)
            {
                //en cas d'erreur affichage du message d'erreur
                errorText.Visibility = Visibility.Visible;
                errorText.Content += ex.Message;
            }
        }

        private void AddButton(object sender, RoutedEventArgs e)
        {
            resetErrorMessage();
            try
            {
                Patient pat = new Patient();

                //premiere verification : le docteur n'existe pas déja
                int PatientIdSearched = Int16.Parse(CodeTextBox.Text);
                var queryPatient = from patient in dbPatient.Patient where patient.PatientId == PatientIdSearched select patient;
                foreach (var Patient in queryPatient)
                {
                    if (PatientIdSearched == Patient.PatientId)
                        throw new NullReferenceException("Patient ID already exist.");
                }

                //deuxieme verification : les champs sont remplis
                if (String.IsNullOrEmpty(NameTextBox.Text) ||
                    String.IsNullOrEmpty(AddressTextBox.Text) ||
                    String.IsNullOrEmpty(CodeTextBox.Text) ||
                    (FeminineRadioButton.IsChecked!=true && MasculineRadioButton.IsChecked!=true) ||
                    String.IsNullOrEmpty(BirthDateDateTime.Text)
                    )
                    throw new NullReferenceException("Fill all element.");

                //inscirption dans la db
                pat.PatientId = PatientIdSearched;
                pat.PatientName = NameTextBox.Text;
                pat.PatientAdress = AddressTextBox.Text;
                if (MasculineRadioButton.IsChecked == true)
                    pat.PatientGender = "H";
                else
                    pat.PatientGender = "F";
                pat.BirthDate = BirthDateDateTime.Text;
                dbPatient.Patient.InsertOnSubmit(pat);
                dbPatient.SubmitChanges();

            }
            catch (Exception ex)
            {
                errorText.Visibility = Visibility.Visible;
                errorText.Content += ex.Message;
            }

        }

        private void DeleteButton(object sender, RoutedEventArgs e)
        {
            resetErrorMessage();
            try
            {
                //premiere verification : le patient existe bien
                int patientIdSearched = Int16.Parse(CodeTextBox.Text);
                var queryPatient = from patient in dbPatient.Patient where patient.PatientId == patientIdSearched select patient;
                if (queryPatient.Count() == 0)
                    throw new NullReferenceException("Patient dont exist.");


                //deuxieme verification : message de vérification
                MessageBoxResult result = MessageBox.Show("Do you really want to do that?",
                "Warning", MessageBoxButton.YesNo, MessageBoxImage.Question);
                if (result == MessageBoxResult.Yes)
                {
                    foreach (Patient pat in queryPatient)
                    {
                        dbPatient.Patient.DeleteOnSubmit(pat);
                        dbPatient.SubmitChanges();
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
                errorText.Visibility = Visibility.Visible;
                errorText.Content += ex.Message;
            }

        }

        private void EditButton(object sender, RoutedEventArgs e)
        {

            resetErrorMessage();
            try
            {
                //premiere verification : les champs sont remplis 
                if (String.IsNullOrEmpty(NameTextBox.Text) ||
                    String.IsNullOrEmpty(AddressTextBox.Text) ||
                    String.IsNullOrEmpty(CodeTextBox.Text) ||
                    (FeminineRadioButton.IsChecked != true && MasculineRadioButton.IsChecked != true) ||
                    String.IsNullOrEmpty(BirthDateDateTime.Text)
                    )
                    throw new NullReferenceException("Load a patient before.");

                //deuxieme verification : le patient existe
                int patientIdSearched = Int16.Parse(CodeTextBox.Text);
                var queryPatient = from patient in dbPatient.Patient where patient.PatientId == patientIdSearched select patient;
                if (queryPatient.Count() == 0)
                    throw new NullReferenceException("Doctor dont exist.");

                //troisieme verification : message de vérification
                MessageBoxResult result = MessageBox.Show("Do you really want to do that?",
                "Warning", MessageBoxButton.YesNo, MessageBoxImage.Question);
                if (result == MessageBoxResult.Yes)
                {
                    //update de la db
                    foreach (Patient pat in queryPatient)
                    {
  
                        pat.PatientId = patientIdSearched;
                        pat.PatientName = NameTextBox.Text;
                        pat.PatientAdress = AddressTextBox.Text;
                        if (MasculineRadioButton.IsChecked == true)
                            pat.PatientGender = "H";
                        else
                            pat.PatientGender = "F";
                        pat.BirthDate = BirthDateDateTime.Text;                        
                    }
                    dbPatient.SubmitChanges();
                }
            }
            catch (Exception ex)
            {
                errorText.Visibility = Visibility.Visible;
                errorText.Content += ex.Message;
            }

        }
    }
}
