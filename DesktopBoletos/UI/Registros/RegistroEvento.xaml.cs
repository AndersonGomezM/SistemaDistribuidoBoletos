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
    public partial class RegistroEvento : Window
    {
        private Eventos eventos = new Eventos();

        public string? url = "http://localhost:8000/ApiBoletos/v1/eventos/";

        HttpClient httpClient = new HttpClient();

        JsonSerializerOptions options = new JsonSerializerOptions() { PropertyNameCaseInsensitive = true };

        private EventosBLL eventosBLL = new EventosBLL();

        public RegistroEvento()
        {
            InitializeComponent();

            this.DataContext = null;
            this.DataContext = eventos;
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

        public async void POSTEvento(Eventos eventos)
        {
            ChangeButton.Visibility = Visibility.Visible;
            var content = new StringContent(JsonConvert.SerializeObject(eventos));
            var response = await httpClient.PostAsync(url, content);
            if (response.IsSuccessStatusCode)
                Console.WriteLine("Funciono correctamente");
            else
                Console.WriteLine("Error");

            ChangeButton.Visibility = Visibility.Collapsed;
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
            POSTEvento(eventos);
        }

        private void EliminarButton_Click(object sender, RoutedEventArgs e)
        {
            
        }
    }
}