using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Text;
using System.Text.Json;
using System.Net.Http;
using Newtonsoft.Json;
using DesktopBoletos.Models;

namespace DesktopBoletos.Controllers
{
    public class EventosControllers
    {
        public string? url = "http://localhost:8000/ApiBoletos/v1/eventos";

        HttpClient httpClient = new HttpClient();

        JsonSerializerOptions options = new JsonSerializerOptions() { PropertyNameCaseInsensitive = true };

        List<Eventos> eventos = new List<Eventos>();

        private async void ConectionEvento()
        {
            var response = await httpClient.GetAsync(url);
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStreamAsync();
                var list = System.Text.Json.JsonSerializer.Deserialize<List<Eventos>>(content, options);
                eventos = list!;
            }
            else
                Console.WriteLine("Error");
        }

        public async void POSTEvento(Eventos eventos)
        {
            var content = new StringContent(JsonConvert.SerializeObject(eventos));
            var response = await httpClient.PostAsync(url, content);
            if (response.IsSuccessStatusCode)
                Console.WriteLine("Funciono correctamente");
            else
                Console.WriteLine("Error");
        }
    }
}