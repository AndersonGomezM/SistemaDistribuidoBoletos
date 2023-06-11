using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using Microsoft.EntityFrameworkCore;
using DesktopBoletos.Models;
using DesktopBoletos.DAL;

namespace DesktopBoletos.BLL
{
    public class UserBLL
    {
        public bool Guardar(User user)
        {
            if(Buscar(user))
                return Modificar(user);
            else
                return false;
        }

        private bool Modificar(User user)
        {
            Contexto contexto = new Contexto();
            bool confirmar = false;

            try {
                contexto.Entry(user).State = EntityState.Modified;
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

        public bool Buscar(User user)
        {
            Contexto contexto = new Contexto();
            bool confirmar = false;

            try {
                confirmar = contexto.User!.Any(e => e.Username == user.Username && e.Password == user.Password);
            }
            catch (Exception) {
                throw;
            }
            finally {
                contexto.Dispose();
            }

            return confirmar;
        }
    }
}