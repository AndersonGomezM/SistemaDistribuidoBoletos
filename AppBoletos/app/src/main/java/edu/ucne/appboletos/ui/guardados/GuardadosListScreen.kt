package edu.ucne.appboletos.ui.guardados

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import edu.ucne.appboletos.data.local.entity.Evento
import edu.ucne.appboletos.data.remote.dto.EventoDto
import edu.ucne.appboletos.ui.theme.AppBoletosTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuardadosListScreen(
    viewModel: GuardadosViewModel = hiltViewModel(),
    onClickSelected: (Int) -> Unit
) {
    AppBoletosTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Guardados", fontSize = 16.sp)
                        }
                    }
                )
            },
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val uiStateLocal by viewModel.uiStateLocal.collectAsState()

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(2.dp))

                    if (uiStateLocal.eventos.isNotEmpty()) {
                        GuardadosList(
                            eventos = uiStateLocal.eventos,
                            viewModel = viewModel,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            onClickSelected(it)
                        }
                    } else {
                        Spacer(modifier = Modifier.padding(0.dp, 60.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp)
                                .padding(30.dp, 0.dp),
                            shape = RoundedCornerShape(20),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp, 0.dp),
                                text = "⚠\n\n!Al parecer no tienes niguna trabajo guardado¡\n\nBusca y guarda los trabajos que más te interesen en la plataforma",
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun GuardadosList(
    eventos: List<Evento>,
    viewModel: GuardadosViewModel,
    modifier: Modifier = Modifier,
    onClickSelected: (Int) -> Unit
) {
    LazyColumn(modifier = modifier) {

        items(eventos) { evento ->

            remember {
                viewModel.getById(evento.referencia)
                0
            }

            val uiState by viewModel.uiState.collectAsState()

            IconButton(
                onClick = { onClickSelected(evento.referencia) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(5.dp, 5.dp)
            ) {
                SelectedCard(uiState.evento, evento, viewModel)
            }
        }
    }
}

@Composable
private fun SelectedCard(
    evento: EventoDto,
    eventoLocal: Evento,
    viewModel: GuardadosViewModel
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
                colors = CardDefaults.cardColors(Color.LightGray)
            ) {
            }

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
                    Text(text = "Fecha: " + evento.fecha, fontSize = 10.sp)

                    IconButton(
                        onClick = {
                            viewModel.Delete(eventoLocal)
                            Toast.makeText(
                                context,
                                "Se ha eliminado con exito 👌",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.Delete,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}