package edu.ucne.appboletos.ui.eventos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appboletos.data.local.entity.Evento
import edu.ucne.appboletos.data.local.repository.EventosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import edu.ucne.appboletos.data.remote.dto.EventoDto
import edu.ucne.appboletos.data.remote.repository.EventoRepository
import edu.ucne.appboletos.utils.Resources
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

data class EventosListUiState(
    val isLoading : Boolean = false,
    val eventos: List<EventoDto> = emptyList(),
    val error : String = ""
)

@HiltViewModel
class EventosViewModel @Inject constructor(
    repository: EventoRepository,
    private val repositoryLocal: EventosRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(EventosListUiState())
    val uiState: StateFlow<EventosListUiState> = _uiState.asStateFlow()

    var id by mutableStateOf(0)

    init {
        repository.getListEventos().onEach { result ->
            when(result) {
                is Resources.Loading -> {
                    _uiState.value = EventosListUiState(isLoading = true)
                }
                is Resources.Success -> {
                    _uiState.value = EventosListUiState(eventos = result.data ?: emptyList())
                }
                is Resources.Error -> {
                    _uiState.value = EventosListUiState(error = result.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun save() {
        viewModelScope.launch {
            repositoryLocal.insert(
                Evento(referencia = id)
            )
        }
    }
}