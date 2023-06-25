using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DesktopBoletos.Models
{
    public class Eventos
    {
        public int id { get; set; }

        public string? nombre { get; set; }

        public string? descripcion { get; set; }

        public DateTime fecha { get; set; } = DateTime.Now;

        public string? direccion { get; set; }

        /*[ForeignKey("Id")]
        public virtual List<Boletas> Productos { get; set; } = new List<Boletas>();*/
    }
}