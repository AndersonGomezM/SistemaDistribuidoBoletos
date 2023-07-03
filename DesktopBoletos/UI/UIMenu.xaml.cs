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
using DesktopBoletos.UI.Registros;
using DesktopBoletos.UI.Consultas;

namespace DesktopBoletos.UI
{
    /// <summary>
    /// Interaction logic for UIMenu.xaml
    /// </summary>
    public partial class UIMenu : Window
    {
        public UIMenu()
        {
            InitializeComponent();
        }
        
        private void RegistroEventos_Click(object sender, RoutedEventArgs e)
        {
            RegistroEvento registroEvento = new RegistroEvento();
            registroEvento.Show();
            this.Close();
        }
        private void RegistroBoletos_Click(object sender, RoutedEventArgs e)
        {
            RegistroBoletos registroBoleto = new RegistroBoletos();
            registroBoleto.Show();
            this.Close();
        }

        private void ConsultaEventos_Click(object sender, RoutedEventArgs e)
        {
            ConsultaEvento consultaEvento = new ConsultaEvento();
            consultaEvento.Show();
            this.Close();
        }

        private void ConsultaBoletos_Click(object sender, RoutedEventArgs e)
        {
            ConsultaBoletos consultaBoletos = new ConsultaBoletos();
            consultaBoletos.Show();
            this.Close();
        }
    }
}