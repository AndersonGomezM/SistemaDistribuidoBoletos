package edu.ucne.appboletos.ui.navigation

sealed class Screen(val route: String) {
    object InicioMain: Screen("Inicio")
    object EventoScreen: Screen("Eventos")
    object EventoSelectedScreen: Screen("Seleccionado")
    object GuardadosListScreen: Screen("Guardados")
    object ConexionScreen: Screen("Conexion")
    object ErrorScreen: Screen("Error")
}