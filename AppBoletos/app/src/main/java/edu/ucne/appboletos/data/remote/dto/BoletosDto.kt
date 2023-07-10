package edu.ucne.appboletos.data.remote.dto

data class BoletosDto(
    val id: Int,
    val nombre: String,
    val cantidad: Int,
    val evento: Int,
)