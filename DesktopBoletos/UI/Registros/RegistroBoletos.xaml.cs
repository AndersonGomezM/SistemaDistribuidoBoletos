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

namespace DesktopBoletos.UI.Registros
{
    /// <summary>
    /// Interaction logic for RegistroBoletos.xaml
    /// </summary>
    public partial class RegistroBoletos : Window
    {
        private Boletas boletos;

        public string url = "http://localhost:8000/ApiBoletos/";

        JsonSerializerOptions options = new JsonSerializerOptions() { PropertyNameCaseInsensitive = true };

        HttpClient httpClient = new HttpClient();

        public RegistroBoletos()
        {
            InitializeComponent();

            boletos = new Boletas();
            this.DataContext = null;
            this.DataContext = boletos;
            GetEvento();
        }

        public RegistroBoletos(Boletas boleto)
        {
            InitializeComponent();
            this.WindowStartupLocation = WindowStartupLocation.CenterOwner;

            Back.Visibility = Visibility.Collapsed;
            EliminarButton.Visibility = Visibility.Visible;
            boletos = boleto;
            boletos.eventos -= 1;
            this.DataContext = null;
            this.DataContext = boletos;
            GetEvento();
        }


        public async void POSTBoletos(Boletas boletos)
        {
            ChangeProgressBar.Visibility = Visibility.Visible;

            boletos.eventos += 1;
            var content = new StringContent(JsonConvert.SerializeObject(boletos), Encoding.UTF8, "application/json");

            var response = await httpClient.PostAsync(url + "boletos/", content);

            if (response.IsSuccessStatusCode) {
                MessageBox.Show("Se ha enviado con exito", "Exito", MessageBoxButton.OK, MessageBoxImage.Information);
                Limpiar();
            } else
                MessageBox.Show("Hubo un error de comunicación " + EventoComboBox.SelectedIndex, "Error", MessageBoxButton.OK, MessageBoxImage.Error);

            ChangeProgressBar.Visibility = Visibility.Collapsed;
        }

        public async void PUTBoletos(Boletas boletos)
        {
            ChangeProgressBar.Visibility = Visibility.Visible;

            boletos.eventos += 1;
            var content = new StringContent(JsonConvert.SerializeObject(boletos), Encoding.UTF8, "application/json");

            var response = await httpClient.PostAsync(url + "boletos/" + boletos.id + "/", content);

            if (response.IsSuccessStatusCode) {
                MessageBox.Show("Se modifico correctamente", "Exito", MessageBoxButton.OK, MessageBoxImage.Information);
                Limpiar();
            } else
                MessageBox.Show("Hubo un error de comunicación " + EventoComboBox.SelectedIndex, "Error", MessageBoxButton.OK, MessageBoxImage.Error);

            ChangeProgressBar.Visibility = Visibility.Collapsed;
        }

        public async void DELETEBoletos(Boletas boletos)
        {
            ChangeProgressBar.Visibility = Visibility.Visible;

            var response = await httpClient.DeleteAsync(url + "boletos/" + boletos.id + "/");

            if (response.IsSuccessStatusCode) {
                MessageBox.Show("Se ha eliminado el boleto con exito", "Exito", MessageBoxButton.OK, MessageBoxImage.Information);
                Limpiar();
            }
            else
                MessageBox.Show("Hubo un error de comunicación", "Error", MessageBoxButton.OK, MessageBoxImage.Error);

            ChangeProgressBar.Visibility = Visibility.Collapsed;
        }

        private async void GetEvento()
        {
            var response = await httpClient.GetAsync(url + "eventos/");

            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStreamAsync();
                var list = System.Text.Json.JsonSerializer.Deserialize<List<Eventos>>(content, options);

                if(list != null)
                    foreach(var item in list)
                        EventoComboBox.Items.Add(item.nombre + " (" + item.id + ")");
                else
                    EventoComboBox.Items.Add("Lista vacia");
            }
        }
        
        private void Back_Click(object sender, RoutedEventArgs e)
        {
            UIMenu menu = new UIMenu();
            menu.Show();
            this.Close();
        }

        private void Limpiar()
        {
            this.boletos = new Boletas();
            this.DataContext = boletos;
        }

        private void NuevoButton_Click(object sender, RoutedEventArgs e)
        {
            Limpiar();
        }

        private void GuardarButton_Click(object sender, RoutedEventArgs e)
        {
            if(boletos.id > 0)
                PUTBoletos(boletos);
            else
                POSTBoletos(boletos);
        }

        private void EliminarButton_Click(object sender, RoutedEventArgs e)
        {
            DELETEBoletos(boletos);
        }
    }
}