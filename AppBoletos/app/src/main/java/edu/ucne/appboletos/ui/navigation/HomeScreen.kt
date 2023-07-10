package edu.ucne.appboletos.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.ucne.appboletos.R
import edu.ucne.appboletos.ui.eventos.EventoScreen
import edu.ucne.appboletos.ui.eventos_selected.EventoSelectedScreen
import edu.ucne.appboletos.ui.guardados.GuardadosListScreen
import edu.ucne.appboletos.ui.theme.AppBoletosTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    AppBoletosTheme {
        navController.popBackStack()
        val navControllerMenu = rememberNavController()

        val items = listOf(
            Screen.EventoScreen,
            Screen.GuardadosListScreen
        )

        Scaffold (
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                BarraNavegacion(items, navControllerMenu)
            }
        ) {
            Menu(navController = navControllerMenu)
        }
    }
}

@Composable
private fun Menu(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.EventoScreen.route
    ) {
        composable(Screen.EventoScreen.route) {
            EventoScreen(
                errorNavigation = navController
            ) {
                navController.navigate(Screen.EventoSelectedScreen.route + "/$it")
            }
        }

        composable(
            Screen.EventoSelectedScreen.route + "/{id}",
            arguments = listOf(navArgument("id") {type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            EventoSelectedScreen(
                id = id,
                onNavigateBack = { navController.navigateUp() }
            )
        }

        composable(Screen.GuardadosListScreen.route) {
            GuardadosListScreen(
                onClickSelected = { navController.navigate(Screen.EventoSelectedScreen.route) }
            )
        }

        composable(Screen.ErrorScreen.route) {
            ErrorScreen()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun BarraNavegacion(
    items: List<Screen>,
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 2f else 1f)

    val nombres = listOf(
        "Home",
        "Guardados"
    )

    val icons = listOf(
        R.drawable.home,
        R.drawable.guardado
    )

    NavigationBar(
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                modifier = Modifier.scale(scale.value),
                icon = {
                    Icon(
                        painter = painterResource(icons[index]),
                        contentDescription = nombres[index],
                    )
                },
                label = { Text(nombres[index]) },
                selected = currentDestination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route)
                }
            )
        }
    }
}