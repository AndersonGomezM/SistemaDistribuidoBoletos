package edu.ucne.appboletos.ui.eventos_selected

import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import edu.ucne.appboletos.data.remote.repository.EventoRepository
import edu.ucne.appboletos.data.remote.dto.EventoDto
import edu.ucne.appboletos.data.remote.dto.BoletosDto
import edu.ucne.appboletos.utils.Resources
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

data class EventoUiState(
    val evento: EventoDto = EventoDto(
        0,
        "",
        "",
        "",
        "",
    )
)

data class BoletoListUiState(
    val isLoading : Boolean = false,
    val boletos: List<BoletosDto> = emptyList(),
    val error : String = ""
)

@HiltViewModel
class EventoSelectedViewModel @Inject constructor(
    val repository: EventoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EventoUiState())
    val uiState: StateFlow<EventoUiState> = _uiState.asStateFlow()

    private val _uiState2 = MutableStateFlow(BoletoListUiState())
    val uiState2: StateFlow<BoletoListUiState> = _uiState2.asStateFlow()

    var id by mutableStateOf(0)

    fun getById(id: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(evento = repository.getEvento(id))
            }
        }
    }

    fun getBoletos() {
         repository.getListBoletos().onEach {   result ->
             when (result) {
                 is Resources.Loading -> {
                     _uiState2.value = BoletoListUiState(isLoading = true)
                 }
                 is Resources.Success -> {
                     _uiState2.value = BoletoListUiState(boletos = result.data ?: emptyList())
                 }
                 is Resources.Error -> {
                     _uiState2.value = BoletoListUiState(error = result.message ?: "Error desconocido")
                 }
             }
         }.launchIn(viewModelScope)
    }

    fun save(boletosDto: BoletosDto) {
        viewModelScope.launch {
            repository.putBoleto(id, boletosDto = boletosDto)
        }
    }
}