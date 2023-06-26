package edu.ucne.appboletos.ui.eventos_selected

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import edu.ucne.appboletos.R
import edu.ucne.appboletos.data.remote.dto.EventoDto
import edu.ucne.appboletos.ui.eventos_selected.EventoSelectedViewModel
import edu.ucne.appboletos.ui.theme.AppBoletosTheme
import java.lang.Double.toString

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventoSelectedScreen(
    id: Int = 0,
    viewModel: EventoSelectedViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    remember {
        viewModel.getById(id)
        0
    }

    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
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
                            Text(text = "Seleccionado", fontSize = 16.sp)
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
                        contentDescription = null,
                    )
                }
            }
        ) {
            Column {
                Spacer(modifier = Modifier.height(5.dp))
                Banner(uiState.evento)
                Spacer(modifier = Modifier.height(10.dp))
                VacanteTitulo(uiState.evento)
                Spacer(modifier = Modifier.height(10.dp))
                ExtraInfo(uiState.evento)
                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    DescripcionRequisito(uiState.evento)
                    Botones(uiState.evento)
                }
            }
        }
    }
}

@Composable
private fun Banner(evento: EventoDto) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(4.dp, 0.dp),
                shape = RoundedCornerShape(20.dp)
            ) {}
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.size(90.dp),
                border = BorderStroke(3.dp, Color.White),
                shape = RoundedCornerShape(35.dp)
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
        }
    }
}

@Composable
private fun VacanteTitulo(evento: EventoDto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = evento.nombreVacante,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
    }
}

@Composable
private fun InfoCard(idIcon: Int, text: String) {
    Card(
        modifier = Modifier
            .width(110.dp)
            .height(70.dp)
            .padding(5.dp),
        elevation = 2.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(idIcon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(3.dp))
            Text(text = text, fontWeight = FontWeight.Bold, fontSize = 10.sp, textAlign = TextAlign.Center)
        }
    }
}

@Composable
private fun DescripcionRequisito(evento: EventoDto) {
    Column(
        modifier = Modifier.padding(4.dp, 0.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Descripción del puesto:", fontWeight = FontWeight.Black, fontSize = 14.sp)
        Text(text = "   " + evento.informacionVacante + "\n", fontSize = 12.sp)
        Text(text = "Requisitos:", fontWeight = FontWeight.Black, fontSize = 14.sp)
        Text(text = "   " + evento.requisitoVacante + "\n", fontSize = 12.sp)
    }
}

@Composable
private fun Botones(
    evento: EventoDto
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(2.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(195.dp)
                .padding(2.dp, 0.dp),
            border = BorderStroke(2.dp, ColorPri),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
        ) {
            Text(text = "Guardar", color = ColorPri)
        }
        Button(
            onClick = {
                val uri = Uri.parse(empleo.paginaWeb)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            },
            modifier = Modifier
                .width(195.dp)
                .padding(2.dp, 0.dp)
        ) {
            Text(text = "Solicitar")
        }
    }
}