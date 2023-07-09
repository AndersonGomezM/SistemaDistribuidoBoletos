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
using DesktopBoletos.Models;
using DesktopBoletos.BLL;
using System.Text.Json;
using System.Net.Http;
using Newtonsoft.Json;

namespace DesktopBoletos.UI.Consultas
{
    /// <summary>
    /// Interaction logic for RegistroEvento.xaml
    /// </summary>
    public partial class RegistroEvento : Window
    {
        private Eventos eventos;

        public string url = "http://localhost:8000/ApiBoletos/";

        JsonSerializerOptions options = new JsonSerializerOptions() { PropertyNameCaseInsensitive = true };

        HttpClient httpClient = new HttpClient();

        public RegistroEvento()
        {
            InitializeComponent();

            eventos = new Eventos();
            this.DataContext = null;
            this.DataContext = eventos;
        }

        public RegistroEvento(Eventos evento)
        {
            InitializeComponent();
            this.WindowStartupLocation = WindowStartupLocation.CenterOwner;
            
            Back.Visibility = Visibility.Collapsed;
            EliminarButton.Visibility = Visibility.Visible;
            eventos = evento;
            this.DataContext = null;
            this.DataContext = eventos;
        }

        public async void POSTEvento(Eventos eventos)
        {
            ChangeProgressBar.Visibility = Visibility.Visible;

            var content = new StringContent(JsonConvert.SerializeObject(eventos), Encoding.UTF8, "application/json");

            var response = await httpClient.PostAsync(url + "eventos/", content);

            if (response.IsSuccessStatusCode) {
                MessageBox.Show("Se envio correctamente", "Exito", MessageBoxButton.OK, MessageBoxImage.Information);
                Limpiar();
            } else
                MessageBox.Show("Hubo un error de comunicación", "Error", MessageBoxButton.OK, MessageBoxImage.Error);

            ChangeProgressBar.Visibility = Visibility.Collapsed;
        }

        public async void PUTEvento(Eventos eventos)
        {
            ChangeProgressBar.Visibility = Visibility.Visible;

            var content = new StringContent(JsonConvert.SerializeObject(eventos), Encoding.UTF8, "application/json");

            var response = await httpClient.PutAsync(url + "eventos/" + eventos.id + "/", content);

            if (response.IsSuccessStatusCode) {
                MessageBox.Show("Se modifico correctamente", "Exito", MessageBoxButton.OK, MessageBoxImage.Information);
                Limpiar();
            } else
                MessageBox.Show("Hubo un error de comunicación 2", "Error", MessageBoxButton.OK, MessageBoxImage.Error);

            ChangeProgressBar.Visibility = Visibility.Collapsed;
        }

        public async void DELETEEvento(Eventos eventos)
        {
            ChangeProgressBar.Visibility = Visibility.Visible;

           var response = await httpClient.DeleteAsync(url + "eventos/" + eventos.id + "/");

            if (response.IsSuccessStatusCode) {
                MessageBox.Show("Se ha eliminado el evento con exito", "Exito", MessageBoxButton.OK, MessageBoxImage.Information);
                Limpiar();
            }
            else
                MessageBox.Show("Hubo un error de comunicación", "Error", MessageBoxButton.OK, MessageBoxImage.Error);

            ChangeProgressBar.Visibility = Visibility.Collapsed;
        }
        
        private void Back_Click(object sender, RoutedEventArgs e)
        {
            UIMenu menu = new UIMenu();
            menu.Show();
            this.Close();
        }

        private void Limpiar()
        {
            this.eventos = new Eventos();
            this.DataContext = eventos;
        }

        private void NuevoButton_Click(object sender, RoutedEventArgs e)
        {
            Limpiar();
        }

        private void GuardarButton_Click(object sender, RoutedEventArgs e)
        {
            if(eventos.id > 0)
                PUTEvento(eventos);
            else
                POSTEvento(eventos);
        }

        private void EliminarButton_Click(object sender, RoutedEventArgs e)
        {
            DELETEEvento(eventos);
        }
    }
}