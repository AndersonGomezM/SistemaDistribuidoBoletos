package edu.ucne.appboletos.data.remote.repository

import edu.ucne.appboletos.data.remote.EventoApi
import edu.ucne.appboletos.data.remote.dto.EventoDto
import javax.inject.Inject

class EventoRepository @Inject constructor(
    private val evento : EventoApi
) {
    suspend fun getListEventos(): List<EventoDto> {
        try {
            return evento.GetList()
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getEvento(id: Int): EventoDto {
        try {
            return evento.GetEvento(id)
        } catch (e: Exception) {
            throw e
        }
    }
}