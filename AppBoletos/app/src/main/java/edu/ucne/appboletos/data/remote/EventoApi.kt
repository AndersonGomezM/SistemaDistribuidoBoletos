package edu.ucne.appboletos.data.remote

import edu.ucne.appboletos.data.remote.dto.EventoDto
import retrofit2.Response
import retrofit2.http.*

interface EventoApi {
    @GET("eventos")
    suspend fun GetList(): List<EventoDto>

    @GET("eventos/{Id}")
    suspend fun GetEvento(@Path("Id") Id: Int): EventoDto

    @PUT("/api/Adelantos/{id}")
    suspend fun putEvento(@Path("Id") id: Int, @Body adelantosDto: EventoDto): Response<Unit>
}