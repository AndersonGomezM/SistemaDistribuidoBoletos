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

namespace DesktopBoletos.UI.Consultas
{
    public partial class RegistroEvento : Window
    {
        public RegistroEvento()
        {
            InitializeComponent();
        }

        private void Back_Click(object sender, RoutedEventArgs e)
        {
            UIMenu menu = new UIMenu();
            menu.Show();
            this.Close();
        }

        private void ConsultarButton_Click(object sender, RoutedEventArgs e)
        {

        }

        private void NuevoButton_Click(object sender, RoutedEventArgs e)
        {
            
        }

        private void ConsultaEventos_Click(object sender, RoutedEventArgs e)
        {
            
        }

        private void GuardarButton_Click(object sender, RoutedEventArgs e)
        {
            
        }

        private void EliminarButton_Click(object sender, RoutedEventArgs e)
        {
            
        }
    }
}