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

namespace DesktopBoletos.UI.Registros
{
    public partial class ConsultaEvento : Window
    {
        public string? url = "http://localhost:8000/ApiBoletos/eventos/";

        JsonSerializerOptions options = new JsonSerializerOptions() { PropertyNameCaseInsensitive = true };

        EventosBLL eventosBLL = new EventosBLL();

        public ConsultaEvento()
        {
            InitializeComponent();
            ConectionEvento();
        }

        private async void ConectionEvento()
        {
            
            using(var httpClient = new HttpClient())
            {
                var response = await httpClient.GetAsync(url);
                if (response.IsSuccessStatusCode)
                {
                    var content = await response.Content.ReadAsStreamAsync();
                    var list = JsonSerializer.Deserialize<List<Eventos>>(content, options);

                    EventosData.ItemsSource = null;
                    EventosData.ItemsSource = list;
                    ChangeButton.Visibility = Visibility.Collapsed;
                }
                else
                    Console.WriteLine("Error");
            }
        }

        private void Back_Click(object sender, RoutedEventArgs e)
        {
            UIMenu menu = new UIMenu();
            menu.Show();
            this.Close();
        }

        private void Ver_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}