using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;

namespace DesktopBoletos.Models
{
    public class Boletas
    {
        [Key]
        public int Id { get; set; }

        public string? Nombre { get; set; }

        public float Precio { get; set; }

        public int Cantidad { get; set; }
    }
}