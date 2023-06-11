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
        public bool Guardar(Eventos eventos)
        {
            if(!Existe(eventos.Id))
                return Insertar(eventos);
            else
                return Modificar(eventos);
        }

        private bool Modificar(Eventos eventos)
        {
            Contexto contexto = new Contexto();
            bool confirmar = false;

            try {
                contexto.Entry(eventos).State = EntityState.Modified;
                confirmar = contexto.SaveChanges() > 0;
            }
            catch (Exception) {
                throw;
            }
            finally {
                contexto.Dispose();
            }

            return confirmar;
        }

        private bool Insertar(Eventos eventos)
        {
            Contexto contexto = new Contexto();
            bool confirmar = false;

            try {
                contexto.Eventos!.Add(eventos);
                confirmar = contexto.SaveChanges() > 0;
            }
            catch (Exception) {
                throw;
            }
            finally {
                contexto.Dispose();
            }

            return confirmar;
        }

        public bool Eliminar(int id)
        {
            Contexto contexto = new Contexto();
            bool confirmar = false;

            try {
                var eventos = contexto.Eventos!.Find(id);
                
                if(eventos != null)
                {
                    contexto.Eventos!.Remove(eventos);
                    confirmar = contexto.SaveChanges() > 0;
                }
            }
            catch (Exception) {
                throw;
            }
            finally {
                contexto.Dispose();
            }

            return confirmar;
        }

        public Eventos Buscar(int id)
        {
            Contexto contexto = new Contexto();
            Eventos? eventos;

            try {
                eventos = contexto.Eventos!
                    .Where(e => e.Id == id)
                    .AsNoTracking()
                    .SingleOrDefault();
            }
            catch (Exception) {
                throw;
            }
            finally {
                contexto.Dispose();
            }

            return eventos!;
        }

        private bool Existe(int id)
        {
            Contexto contexto = new Contexto();
            bool confirmar = false;

            try {
                confirmar = contexto.Eventos!.Any(e => e.Id == id);
            }
            catch (Exception) {
                throw;
            }
            finally {
                contexto.Dispose();
            }

            return confirmar;
        }

        public bool Validar(string? nombre)
        {
            Contexto contexto = new Contexto();
            bool confirmar = false;

            try {
                confirmar = contexto.Eventos!.Any(e => e.Nombre!.ToLower() == nombre!.ToLower());
            }
            catch (Exception) {
                throw;
            }
            finally {
                contexto.Dispose();
            }

            return confirmar;
        }

        public List<Eventos> Geteventos()
        {
            List<Eventos> lista = new List<Eventos>();
            Contexto contexto = new Contexto();

            try {
                lista = contexto.Eventos!
                    .AsNoTracking()
                    .ToList();
            }
            catch (Exception) {
                throw;
            }
            finally {
                contexto.Dispose();
            }

            return lista;
        }

        public List<Eventos> GetList(Expression<Func<Eventos, bool>> criterio)
        {
            List<Eventos> lista = new List<Eventos>();
            Contexto contexto = new Contexto();

            try {
                lista = contexto.Eventos!
                    .Where(criterio)
                    .AsNoTracking()
                    .ToList();
            }
            catch (Exception) {
                throw;
            }
            finally {
                contexto.Dispose();
            }

            return lista;
        }
    }
}