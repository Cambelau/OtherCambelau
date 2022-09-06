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
    /// Logique d'interaction pour DoctorManagement.xaml
    /// </summary>
    public partial class DoctorManagement : Window
    {
        DoctorsDataContext dbDoctors = new DoctorsDataContext();

        public DoctorManagement()
        {
            InitializeComponent();
        }

        private void NewButton(object sender, RoutedEventArgs e)
        {
            //new button reset all textbox with empty string
            CodeTextBox.Text = String.Empty;
            NameTextBox.Text = String.Empty;
            TelephoneTextBox.Text = String.Empty;
            HiringDateDateTime.Text = DateTime.Today.ToString();
            SpecialityComboBox.Text = String.Empty;
            resetErrorMessage();
        }

        private void SearchButton(object sender, RoutedEventArgs e)
        {
            resetErrorMessage();
            try
            {

                //récupérer le code et le transformer en int
                int doctorIdSearched = Int16.Parse(CodeTextBox.Text);
                // requete sql du doctor recherché
                var queryDoctor = from doct in dbDoctors.Doctors where doct.DoctorId == doctorIdSearched select doct;
                if (queryDoctor.Count() == 0)
                    throw new NullReferenceException("Doctor dont exist.");

                foreach (var doctor in queryDoctor)
                {
                    //remplir les champs 
                    NameTextBox.Text = doctor.DoctorName;
                    TelephoneTextBox.Text = doctor.DoctorTel;
                    var parsedDate = DateTime.Parse(doctor.HiringDate);
                    HiringDateDateTime.Text = parsedDate.ToString();
                    SpecialityComboBox.Text = doctor.DoctorSpecialism;
                }
            }
            catch (Exception ex)
            {
                //en cas d'erreur affichage du message d'erreur
                errorText.Visibility = Visibility.Visible;
                errorText.Content+=ex.Message;
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

        private void AddButton(object sender, RoutedEventArgs e)
        {
            resetErrorMessage();
            try
            {
                Doctors doc = new Doctors();

                //premiere verification : le docteur n'existe pas déja
                int doctorIdSearched = Int16.Parse(CodeTextBox.Text);
                var queryDoctor = from doct in dbDoctors.Doctors where doct.DoctorId == doctorIdSearched select doct;
                foreach (var doctor in queryDoctor)
                {
                    if (doctorIdSearched == doctor.DoctorId)
                        throw new NullReferenceException("Doctor ID already exist.");
                }

                //deuxieme verification : les champs sont remplis
                if (String.IsNullOrEmpty(NameTextBox.Text) ||
                    String.IsNullOrEmpty(TelephoneTextBox.Text) ||
                    String.IsNullOrEmpty(CodeTextBox.Text) ||
                    String.IsNullOrEmpty(SpecialityComboBox.Text) ||
                    String.IsNullOrEmpty(HiringDateDateTime.Text) 
                    )
                    throw new NullReferenceException("Fill all element.");

                //inscirption dans la db
                doc.DoctorId = doctorIdSearched;
                doc.DoctorName = NameTextBox.Text;
                doc.DoctorTel = TelephoneTextBox.Text;
                doc.DoctorSpecialism = SpecialityComboBox.Text; 
                doc.HiringDate= HiringDateDateTime.Text;
                dbDoctors.Doctors.InsertOnSubmit(doc);
                dbDoctors.SubmitChanges();
                
            }
            catch (Exception ex)
            {
                errorText.Visibility = Visibility.Visible;
                errorText.Content+=ex.Message;
            }

        }

        private void resetErrorMessage()
        {
            errorText.Visibility = Visibility.Hidden;
            errorText.Content = "Error Happened ! ";
        }

        private void EditButton(object sender, RoutedEventArgs e)
        {
            resetErrorMessage();
            try
            {
                //premiere verification : les champs sont remplis 
                if (String.IsNullOrEmpty(NameTextBox.Text) ||
                    String.IsNullOrEmpty(TelephoneTextBox.Text) ||
                    String.IsNullOrEmpty(CodeTextBox.Text) ||
                    String.IsNullOrEmpty(SpecialityComboBox.Text) ||
                    String.IsNullOrEmpty(HiringDateDateTime.Text)
                    )
                    throw new NullReferenceException("Load a doctor before.");
                
                //deuxieme verification : le docteur existe
                int doctorIdSearched = Int16.Parse(CodeTextBox.Text);
                var queryDoctor = from doct in dbDoctors.Doctors where doct.DoctorId == doctorIdSearched select doct;
                if (queryDoctor.Count() == 0)
                    throw new NullReferenceException("Doctor dont exist.");

                //troisieme verification : message de vérification
                MessageBoxResult result = MessageBox.Show("Do you really want to do that?",
                "Warning", MessageBoxButton.YesNo, MessageBoxImage.Question);
                if (result == MessageBoxResult.Yes)
                {
                    //update de la db
                    foreach (Doctors doc in queryDoctor)
                    {
                        doc.DoctorId = doctorIdSearched;
                        doc.DoctorName = NameTextBox.Text;
                        doc.DoctorTel = TelephoneTextBox.Text;
                        doc.DoctorSpecialism = SpecialityComboBox.Text;
                        doc.HiringDate = HiringDateDateTime.Text;
                    }
                    dbDoctors.SubmitChanges();
                }
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
                //premiere verification : le docteur existe bien
                int doctorIdSearched = Int16.Parse(CodeTextBox.Text);
                var queryDoctor = from doct in dbDoctors.Doctors where doct.DoctorId == doctorIdSearched select doct;
                if (queryDoctor.Count() == 0)
                    throw new NullReferenceException("Doctor dont exist.");


                //deuxieme verification : message de vérification
                MessageBoxResult result = MessageBox.Show("Do you really want to do that?",
                "Warning", MessageBoxButton.YesNo, MessageBoxImage.Question);
                if (result == MessageBoxResult.Yes)
                {
                    foreach (Doctors doc in queryDoctor)
                    {
                        dbDoctors.Doctors.DeleteOnSubmit(doc);
                        dbDoctors.SubmitChanges();
                    }
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
