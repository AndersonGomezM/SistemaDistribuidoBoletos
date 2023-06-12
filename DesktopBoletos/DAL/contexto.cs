using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using DesktopBoletos.Models;

namespace DesktopBoletos.DAL
{
    public class Contexto : DbContext
    {
        public DbSet<User>? User { get; set; }

        public DbSet<Eventos>? Eventos { get; set; }

        public DbSet<Boletas>? Boletas { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlite(@"Data Source = Data\DataBase.db");
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            /*modelBuilder.Entity<Categoria>().HasData(
                new Categoria { CategoriaId = 1, NombreCategoria = "Percianas aluminicas" },
            );*/
        }
    }
}