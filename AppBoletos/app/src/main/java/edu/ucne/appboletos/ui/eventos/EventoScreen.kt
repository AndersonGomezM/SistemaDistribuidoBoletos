package edu.ucne.appboletos.ui.eventos

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appboletos.R
import edu.ucne.appboletos.data.remote.dto.EventoDto
import edu.ucne.appboletos.ui.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventoScreen(
    viewModel: EventosViewModel = hiltViewModel(),
    errorNavigation: NavController,
    onClickSelected: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Eventos", fontSize = 16.sp)
                    }
                },
                modifier = Modifier.border(width = 1.dp, color = Color.White)
            )
        }
    ) {
        val uiState by viewModel.uiState.collectAsState()

        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(80.dp))
            if(uiState.error == "Error") {
                errorNavigation.navigate(Screen.ErrorScreen.route)
            } else {
                if(uiState.isLoading)
                    CircularProgressIndicator(modifier = Modifier
                        .size(80.dp)
                        .padding(0.dp, 50.dp), strokeWidth = 8.dp)

                CategoriaList(
                    eventos = uiState.eventos,
                    viewModel = viewModel,
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    onClickSelected(it)
                }
            }
        }
    }
}

@Composable
private fun CategoriaList(
    eventos: List<EventoDto>,
    viewModel: EventosViewModel,
    modifier: Modifier = Modifier,
    onClickSelected: (Int) -> Unit
) {
    LazyColumn(modifier = modifier) {

        items(eventos) { evento ->
            IconButton(
                onClick = { onClickSelected(evento.id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(5.dp, 5.dp)
            ) {
                SelectedCard(evento = evento, viewModel)
            }
        }
    }
}

@Composable
private fun SelectedCard(
    evento: EventoDto,
    viewModel: EventosViewModel
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxSize()
            .height(130.dp),
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(130.dp),
                colors = CardDefaults.cardColors(Color.Cyan)
            ) {}

            Spacer(modifier = Modifier.padding(3.dp, 0.dp))

            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = evento.nombre,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Left,
                    fontSize = 14.sp
                )

                Text(
                    text = evento.descripcion,
                    textAlign = TextAlign.Left,
                    fontSize = 12.sp
                )

                Text(
                    text = evento.direccion,
                    textAlign = TextAlign.Left,
                    fontSize = 12.sp
                )

                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Text(text = evento.fecha.format("dd/MM/yyyy"), fontSize = 10.sp)
                    }

                    IconButton(
                        onClick = {
                            viewModel.id = evento.id
                            viewModel.save()
                            Toast.makeText(
                                context,
                                "Se ha guardado la propuesta de empleo con exito 👍",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(R.drawable.guardado),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}