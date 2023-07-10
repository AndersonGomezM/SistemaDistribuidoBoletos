package edu.ucne.appboletos.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.appboletos.R
import edu.ucne.appboletos.ui.theme.AppBoletosTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ErrorScreen(
) {
    AppBoletosTheme {
        Inicio()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Inicio(
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
            Text(text = "Tenemos un problema con el servidor")
            Spacer(modifier = Modifier.padding(15.dp))
            Text(text = "Vuelva a intentar en otro momento")
            Spacer(modifier = Modifier.padding(15.dp))
        }
    }
}