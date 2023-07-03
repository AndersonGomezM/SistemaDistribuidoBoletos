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
        private Boletas boletos = new Boletas();

        public string url = "http://localhost:8000/ApiBoletos/";

        JsonSerializerOptions options = new JsonSerializerOptions() { PropertyNameCaseInsensitive = true };

        HttpClient httpClient = new HttpClient();

        public RegistroBoletos()
        {
            InitializeComponent();

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

            if (response.IsSuccessStatusCode)
                MessageBox.Show("Se ha enviado con exito", "Exito", MessageBoxButton.OK, MessageBoxImage.Information);
            else
                MessageBox.Show("Hubo un error de comunicación " + EventoComboBox.SelectedIndex, "Error", MessageBoxButton.OK, MessageBoxImage.Error);

            ChangeProgressBar.Visibility = Visibility.Collapsed;
        }

        private async void GetEvento()
        {
            var response = await httpClient.GetAsync(url + "eventos/");

            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStreamAsync();
                var list = System.Text.Json.JsonSerializer.Deserialize<List<Eventos>>(content, options);

                foreach(var item in list)
                    EventoComboBox.Items.Add(item.nombre + " (" + item.id + ")");
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

        private void ConsultarButton_Click(object sender, RoutedEventArgs e)
        {

        }

        private void NuevoButton_Click(object sender, RoutedEventArgs e)
        {
            Limpiar();
        }

        private void GuardarButton_Click(object sender, RoutedEventArgs e)
        {
            POSTBoletos(boletos);
        }
    }
}