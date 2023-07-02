package edu.ucne.appboletos.data.local.repository

import javax.inject.Inject
import edu.ucne.appboletos.data.local.AppDataBase
import edu.ucne.appboletos.data.local.entity.Evento

class EventosRepository @Inject constructor(
    val db: AppDataBase) {

    suspend fun insert(evento: Evento) {
        db.eventosDao.insertEvento(evento)
    }

    suspend fun update(evento: Evento) {
        db.eventosDao.updateEvento(evento)
    }

    suspend fun delete(evento: Evento) {
        db.eventosDao.deleteEvento(evento)
    }

    fun getEvento(id: Int) = db.eventosDao.getEvento(id)

    fun getAll() = db.eventosDao.getAll()
}