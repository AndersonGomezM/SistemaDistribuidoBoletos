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
        public int id { get; set; }

        public string? nombre { get; set; }

        public int cantidad { get; set; }

        public int eventos { get; set; }
    }
}