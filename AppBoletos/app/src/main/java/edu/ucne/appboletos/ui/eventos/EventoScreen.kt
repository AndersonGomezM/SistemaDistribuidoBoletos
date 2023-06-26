package edu.ucne.appboletos.ui.eventos

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import edu.ucne.appboletos.R
import edu.ucne.appboletos.data.remote.dto.EventoDto
import edu.ucne.appboletos.ui.theme.AppBoletosTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventoScreen(
    onNavigateBack: () -> Unit,
    viewModel: EventosViewModel = hiltViewModel(),
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
                            Text(text = "Eventos", fontSize = 16.sp)
                        }
                    }
                )

                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.back),
                        contentDescription = null
                    )
                }
            }
        ) {
            val uiState by viewModel.uiState.collectAsState()

            Column(modifier = Modifier.fillMaxSize()) {

                Spacer(modifier = Modifier.height(2.dp))
                CategoriaList(
                    empleos = uiState.eventos,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    onClickSelected(it)
                }
            }
        }
    }
}

@Composable
private fun CategoriaList(
    eventos: List<EventoDto>,
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
                SelectedCard(evento = evento)
            }
        }
    }
}

@Composable
private fun SelectedCard(
    evento: EventoDto
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .height(130.dp),
        elevation = 2.dp
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(130.dp),
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(evento.logoEmpresa)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.padding(3.dp, 0.dp))

            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = empleo.nombreVacante,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Left,
                    fontSize = 14.sp,
                    color = ColorPri
                )
                Text(
                    text = empleo.nombreEmpresa,
                    textAlign = TextAlign.Left,
                    fontSize = 12.sp
                )
                Text(
                    text = empleo.tipo,
                    textAlign = TextAlign.Left,
                    fontSize = 12.sp
                )
                Text(
                    text = empleo.modalida,
                    fontSize = 12.sp
                )
                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = empleo.ubicacion + " - " + empleo.fechaPublicacion, fontSize = 10.sp)

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(R.drawable.guardado),
                            contentDescription = null,
                            tint = ColorPri
                        )
                    }
                }
            }
        }
    }
}