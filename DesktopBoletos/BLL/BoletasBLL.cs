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
    public class BoletasBLL
    {
        public bool Guardar(Boletas boletas)
        {
            if(!Existe(boletas.Id))
                return Insertar(boletas);
            else
                return Modificar(boletas);
        }

        private bool Modificar(Boletas boletas)
        {
            Contexto contexto = new Contexto();
            bool confirmar = false;

            try {
                contexto.Entry(boletas).State = EntityState.Modified;
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

        private bool Insertar(Boletas boletas)
        {
            Contexto contexto = new Contexto();
            bool confirmar = false;

            try {
                contexto.Boletas!.Add(boletas);
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
                var boletas = contexto.Boletas!.Find(id);
                if(boletas != null)
                {
                    contexto.Boletas!.Remove(boletas);
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

        public Boletas Buscar(int id)
        {
            Contexto contexto = new Contexto();
            Boletas? boletas;

            try {
                boletas = contexto.Boletas!
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

            return boletas!;
        }

        private bool Existe(int id)
        {
            Contexto contexto = new Contexto();
            bool confirmar = false;

            try {
                confirmar = contexto.Boletas!.Any(e => e.Id == id);
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
                confirmar = contexto.Boletas!.Any(e => e.Nombre!.ToLower() == nombre!.ToLower());
            }
            catch (Exception) {
                throw;
            }
            finally {
                contexto.Dispose();
            }

            return confirmar;
        }

        public List<Boletas> Getboletas()
        {
            List<Boletas> lista = new List<Boletas>();
            Contexto contexto = new Contexto();

            try {
                lista = contexto.Boletas!
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

        public List<Boletas> GetList(Expression<Func<Boletas, bool>> criterio)
        {
            List<Boletas> lista = new List<Boletas>();
            Contexto contexto = new Contexto();

            try {
                lista = contexto.Boletas!
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