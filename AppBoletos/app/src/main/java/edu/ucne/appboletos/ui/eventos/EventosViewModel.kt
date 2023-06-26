package edu.ucne.appboletos.ui.eventos

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import edu.ucne.appboletos.data.remote.dto.EventoDto
import edu.ucne.appboletos.data.remote.repository.EventoRepository

data class EventosListUiState(
    val eventos: List<EventoDto> = emptyList()
)

@HiltViewModel
class EventosViewModel @Inject constructor(
    val repository: EventoRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(EventosListUiState())
    val uiState: StateFlow<EventosListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(eventos = repository.getListEventos())
            }
        }
    }
}