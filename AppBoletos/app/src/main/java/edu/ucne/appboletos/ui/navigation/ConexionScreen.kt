package edu.ucne.appboletos.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.appboletos.R
import edu.ucne.appboletos.ui.theme.AppBoletosTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConexionScreen(
    onClick: () -> Unit
) {
    AppBoletosTheme {
        Inicio(onClick)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Inicio(
    onClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.waring),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )

            Text(text = "¡¡Ooops!!", fontSize = 30.sp, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "No hay internet, comprueba tu conexión.")
            Spacer(modifier = Modifier.padding(15.dp))

            Button(
                onClick = onClick,
                shape = RoundedCornerShape(20)
            ) {
                Text(text = "Intentar nuevamente", color = Color.White)
            }
        }
    }
}