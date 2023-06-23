package edu.ucne.appboletos.data.remote

import edu.ucne.appboletos.data.remote.dto.EventoDto
import retrofit2.http.*

interface EventoApi {
    @GET("eventos")
    suspend fun GetList(): List<EventoDto>

    @GET("eventos/{Id}")
    suspend fun GetEvento(@Path("Id") Id: Int): EventoDto
}