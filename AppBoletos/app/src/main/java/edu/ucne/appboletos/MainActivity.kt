package edu.ucne.appboletos

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import edu.ucne.appboletos.ui.navigation.HomeScreen
import edu.ucne.appboletos.ui.navigation.Screen
import edu.ucne.appboletos.ui.theme.AppBoletosTheme
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.appboletos.ui.navigation.ConexionScreen
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppBoletosTheme {
                var refreshing by remember { mutableStateOf(false) }

                LaunchedEffect(refreshing) {
                    delay(1000)
                    refreshing = false
                }

                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = refreshing),
                    onRefresh = { refreshing = true },
                    indicator = { state, refreshTrigger ->
                        SwipeRefreshIndicator(
                            state = state,
                            refreshTriggerDistance = refreshTrigger,
                            scale = true
                        )
                    }
                ) {
                    InicioMain()
                }
            }
        }
    }
}

@Composable
private fun InicioMain() {
    val context = LocalContext.current
    val navController = rememberNavController()

    if (compruebaConexion(context)) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(navController)
        }
    } else {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavConexion(navController)
        }
    }
}

@Composable
private fun NavConexion(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.ConexionScreen.route
    ) {

        composable(Screen.ConexionScreen.route) {
            ConexionScreen(
                onClick = { navController.navigate(Screen.InicioMain.route) }
            )
        }

        composable(Screen.InicioMain.route) {
            InicioMain()
        }
    }
}

private fun compruebaConexion(context: Context): Boolean {
    var connected = false
    val connec = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // Recupera todas las redes (tanto móviles como wifi)
    val redes = connec.allNetworkInfo
    for (i in redes.indices) {
        // Si alguna red tiene conexión, se devuelve true
        if (redes[i].state == NetworkInfo.State.CONNECTED) {
            connected = true
        }
    }
    return connected
}