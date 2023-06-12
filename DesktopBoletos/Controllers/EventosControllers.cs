using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Text;
using System.Text.Json;
using System.Net.Http;
using DesktopBoletos.Models;

namespace DesktopBoletos.Controllers
{
    public class EventosControllers
    {
        public string? url = "https://64839cb5f2e76ae1b95cb373.mockapi.io/Evento";

        JsonSerializerOptions options = new JsonSerializerOptions() { PropertyNameCaseInsensitive = true };

        HttpClient httpClient = new HttpClient();
        List<Eventos> eventos = new List<Eventos>();

        private async Task ConectionEvento()
        {
            var response = await httpClient.GetAsync(url);
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStreamAsync();
                eventos = JsonSerializer.Deserialize<List<Eventos>>(content, options);
            }
            else
                Console.WriteLine("Error");
        }

        public List<Eventos> GETEventos()
        {
            return eventos;
        }
    }
}