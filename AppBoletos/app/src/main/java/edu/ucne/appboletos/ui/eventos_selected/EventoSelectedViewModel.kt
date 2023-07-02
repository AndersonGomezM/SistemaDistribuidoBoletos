package edu.ucne.appboletos.ui.eventos_selected

import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appboletos.data.local.entity.Evento
import edu.ucne.appboletos.data.local.repository.EventosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import edu.ucne.appboletos.data.remote.repository.EventoRepository
import edu.ucne.appboletos.data.remote.dto.EventoDto
import java.time.Clock
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

data class EventoUiState(
    val evento: EventoDto = EventoDto(
        0,
        "",
        "",
        "",
        "",
    )
)

@HiltViewModel
class EventoSelectedViewModel @Inject constructor(
    val repository: EventoRepository,
    val repositoryLocal: EventosRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EventoUiState())
    val uiState: StateFlow<EventoUiState> = _uiState.asStateFlow()

    var id by mutableStateOf(0)

    fun getById(id: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(evento = repository.getEvento(id))
            }
        }
    }

    fun save() {
        viewModelScope.launch {
            repositoryLocal.insert(
                Evento(referencia = id)
            )
        }
    }
}