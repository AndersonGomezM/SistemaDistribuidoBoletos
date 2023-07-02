package edu.ucne.appboletos.data.remote

import edu.ucne.appboletos.data.remote.dto.EventoDto
import retrofit2.Response
import retrofit2.http.*

interface EventoApi {
    @GET("eventos/")
    suspend fun GetList(): List<EventoDto>

    @GET("eventos/{id}")
    suspend fun GetEvento(@Path("id") id: Int): EventoDto

    @PUT("eventos/{id}")
    suspend fun putEvento(@Path("id") id: Int, @Body eventoDto: EventoDto): Response<Unit>
}