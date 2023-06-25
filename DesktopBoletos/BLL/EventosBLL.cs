using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using Microsoft.EntityFrameworkCore;
using DesktopBoletos.Models;
using DesktopBoletos.DAL;
using DesktopBoletos.Controllers;

namespace DesktopBoletos.BLL
{
    public class EventosBLL
    {
        EventosControllers controllers = new EventosControllers();

        public bool Guardar(Eventos eventos)
        {
            //controllers.POSTEvento(eventos);
            return true;
        }

        private bool Modificar(Eventos eventos)
        {
            bool confirmar = false;

            try {
                /*
                contexto.Entry(eventos).State = EntityState.Modified;
                confirmar = contexto.SaveChanges() > 0;
                */
            }
            catch (Exception) {
                throw;
            }

            return confirmar;
        }

        private bool Insertar(Eventos eventos)
        {
            bool confirmar = false;

            try {
                /*
                contexto.Eventos!.Add(eventos);
                confirmar = contexto.SaveChanges() > 0;
                */
            }
            catch (Exception) {
                throw;
            }

            return confirmar;
        }

        public bool Eliminar(int id)
        {
            bool confirmar = false;

            try {
                /*
                var eventos = contexto.Eventos!.Find(id);
                
                if(eventos != null)
                {
                    contexto.Eventos!.Remove(eventos);
                    confirmar = contexto.SaveChanges() > 0;
                }
                */
            }
            catch (Exception) {
                throw;
            }

            return confirmar;
        }

        public Eventos Buscar(int id)
        {
            Eventos? eventos = new Eventos();

            try {
                /*
                eventos = contexto.Eventos!
                    .Where(e => e.Id == id)
                    .AsNoTracking()
                    .SingleOrDefault();
                */
            }
            catch (Exception) {
                throw;
            }

            return eventos;
        }

        private bool Existe(int id)
        {
            bool confirmar = false;

            try {
                //confirmar = contexto.Eventos!.Any(e => e.Id == id);
            }
            catch (Exception) {
                throw;
            }

            return confirmar;
        }

        public bool Validar(string? nombre)
        {
            bool confirmar = false;

            try {
                //confirmar = contexto.Eventos!.Any(e => e.Nombre!.ToLower() == nombre!.ToLower());
            }
            catch (Exception) {
                throw;
            }

            return confirmar;
        }

        public List<Eventos> Geteventos()
        {
            List<Eventos> lista = new List<Eventos>();

            try {

            }
            catch (Exception) {
                throw;
            }

            return lista;
        }

        public List<Eventos> GetList()
        {
            List<Eventos> lista = new List<Eventos>();

            try {
                //lista = controllers.GETEventos();
            }
            catch (Exception) {
                throw;
            }

            return lista;
        }
    }
}