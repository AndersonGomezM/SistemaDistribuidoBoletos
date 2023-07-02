package edu.ucne.appboletos.data.remote.repository

import edu.ucne.appboletos.data.remote.EventoApi
import edu.ucne.appboletos.data.remote.dto.EventoDto
import edu.ucne.appboletos.utils.Resources
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class EventoRepository @Inject constructor(
    private val evento : EventoApi
) {
    fun getListEventos(): Flow<Resources<List<EventoDto>>> = flow {
        try {
            emit(Resources.Loading())
            emit(Resources.Success(evento.GetList()))
        } catch (e: IOException) {
            emit(Resources.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun getEvento(id: Int): EventoDto {
        try {
            return evento.GetEvento(id)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun putEvento(id: Int, eventodto: EventoDto) {
        evento.putEvento(id, eventodto)
    }
}