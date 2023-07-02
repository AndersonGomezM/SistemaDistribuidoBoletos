package edu.ucne.appboletos.data.remote.dto

import java.time.LocalDateTime

data class EventoDto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val fecha: String,
    val direccion: String,
)