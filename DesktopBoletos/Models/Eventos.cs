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
        [Key]
        public int Id { get; set; }

        public string? Nombre { get; set; }

        public string? Descripcion { get; set; }

        public DateTime Fecha { get; set; } = DateTime.Now;

        public string? Direccion { get; set; }

        /*[ForeignKey("Id")]
        public virtual List<Boletas> Productos { get; set; } = new List<Boletas>();*/
    }
}