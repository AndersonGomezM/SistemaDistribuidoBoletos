package edu.ucne.appboletos.data.remote

import edu.ucne.appboletos.data.remote.dto.BoletosDto
import edu.ucne.appboletos.data.remote.dto.EventoDto
import retrofit2.Response
import retrofit2.http.*

interface EventoApi {
    @GET("eventos/")
    suspend fun GetListE(): List<EventoDto>

    @GET("eventos/{id}/")
    suspend fun GetEvento(@Path("id") id: Int): EventoDto

    @GET("boletos/")
    suspend fun GetListB(): List<BoletosDto>

    @PUT("boletos/{id}/")
    suspend fun PutBoleto(@Path("id") id: Int, @Body boletosDto: BoletosDto): Response<Unit>
}