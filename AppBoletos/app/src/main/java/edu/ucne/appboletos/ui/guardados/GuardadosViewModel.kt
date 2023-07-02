package edu.ucne.appboletos.ui.guardados

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appboletos.data.local.entity.Evento
import edu.ucne.appboletos.data.local.repository.EventosRepository
import edu.ucne.appboletos.data.remote.dto.EventoDto
import edu.ucne.appboletos.data.remote.repository.EventoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GuardadosUiState(
    val evento: EventoDto = EventoDto(
        0,
        "",
        "",
        "",
        "",
    )
)

data class EventosLocalUiState(
    val eventos: List<Evento> = emptyList()
)

@HiltViewModel
class GuardadosViewModel @Inject constructor(
    private val repository: EventoRepository,
    private val repositoryLocal: EventosRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(GuardadosUiState())
    val uiState: StateFlow<GuardadosUiState> = _uiState.asStateFlow()

    private val _uiStateLocal = MutableStateFlow(EventosLocalUiState())
    val uiStateLocal: StateFlow<EventosLocalUiState> = _uiStateLocal.asStateFlow()

    init {
        viewModelScope.launch {
            repositoryLocal.getAll().collect { list ->
                _uiStateLocal.update {
                    it.copy(eventos = list)
                }
            }
        }
    }

    fun getById(id: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(evento = repository.getEvento(id))
            }
        }
    }

    fun Delete(evento: Evento) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryLocal.delete(evento)
        }
    }
}