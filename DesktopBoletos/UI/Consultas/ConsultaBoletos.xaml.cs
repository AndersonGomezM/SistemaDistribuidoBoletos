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
using DesktopBoletos.UI.Registros;
using System.Text.Json;
using System.Net.Http;

namespace DesktopBoletos.UI.Consultas
{
    public partial class ConsultaBoletos : Window
    {
        public string? url = "http://localhost:8000/ApiBoletos/";

        JsonSerializerOptions options = new JsonSerializerOptions() { PropertyNameCaseInsensitive = true };
    
        HttpClient httpClient = new HttpClient();

        public ConsultaBoletos()
        {
            InitializeComponent();
            GetBoletos();
        }

        private async void GetBoletos()
        {
            var response = await httpClient.GetAsync(url + "boletos/");

            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStreamAsync();
                var list = JsonSerializer.Deserialize<List<Boletas>>(content, options);

                BoletasData.ItemsSource = null;
                BoletasData.ItemsSource = list;
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

        private void Ver_Click(object sender, RoutedEventArgs e)
        {
            Boletas boleto = (Boletas)BoletasData.SelectedItem;            
            RegistroBoletos registroBoletos = new RegistroBoletos(boleto);
            registroBoletos.Show();
        }

        private void EventoFocus(object sender, RoutedEventArgs e)
        {
            
        }
    }
}