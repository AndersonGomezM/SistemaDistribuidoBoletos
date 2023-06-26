package edu.ucne.appboletos.ui.guardados

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ucne.empleosdoapp.data.local.entity.Empleos
import com.ucne.empleosdoapp.data.remote.dto.EmpleoDto
import com.ucne.empleosdoapp.ui.theme.ColorPri
import com.ucne.empleosdoapp.ui.theme.EmpleosDoAppTheme

@Composable
fun GuardadosListScreen(
    viewModel: GuardadosViewModel = hiltViewModel(),
    onClickSelected: (Int) -> Unit
) {
    Scaffold (
        topBar =  {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Guardados", fontSize = 16.sp)
                    }
                },
                elevation = 2.dp,
                backgroundColor = Color.White
            )

        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background
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

                if(uiStateLocal.empleos.isNotEmpty()) {
                    GuardadosList(
                        empleos = uiStateLocal.empleos,
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
                        backgroundColor = ColorPri,
                        shape = RoundedCornerShape(20),
                        elevation = 10.dp
                    ) {
                        Text(
                            modifier = Modifier.fillMaxSize().padding(10.dp, 0.dp),
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

@Composable
private fun GuardadosList(
    empleos: List<Empleos>,
    viewModel: GuardadosViewModel,
    modifier: Modifier = Modifier,
    onClickSelected: (Int) -> Unit
) {
    LazyColumn(modifier = modifier) {

        items(empleos) { empleo ->

            remember {
                viewModel.getById(empleo.idRefenrence)
                0
            }

            val uiState by viewModel.uiState.collectAsState()

            IconButton(
                onClick = { onClickSelected(empleo.idRefenrence) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(5.dp, 5.dp)
            ) {
                SelectedCard(uiState.empleo, empleo, viewModel)
            }
        }
    }
}

@Composable
private fun SelectedCard(
    empleo: EmpleoDto,
    empleoLocal: Empleos,
    viewModel: GuardadosViewModel
) {
    val context = LocalContext.current
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
                        .data(empleo.logoEmpresa)
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

                    IconButton(
                        onClick = {
                            viewModel.Delete(empleoLocal)
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
                            contentDescription = null,
                            tint = ColorPri
                        )
                    }
                }
            }
        }
    }
}