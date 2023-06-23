package edu.ucne.appboletos.ui.navigation

sealed class Screen(val route: String) {
    object ConexionScreen: Screen("Conexion")
    object InicioMain: Screen("Inicio")
}