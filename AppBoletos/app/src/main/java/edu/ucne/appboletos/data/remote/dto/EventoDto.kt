package edu.ucne.appboletos.data.remote.dto

data class EventoDto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val fecha: String,
    val direccion: String,
)