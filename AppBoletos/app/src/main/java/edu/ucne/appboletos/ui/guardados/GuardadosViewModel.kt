package edu.ucne.appboletos.ui.guardados

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.empleosdoapp.data.local.entity.Empleos
import com.ucne.empleosdoapp.data.local.repository.EmpleosRepository
import com.ucne.empleosdoapp.data.remote.dto.EmpleoDto
import com.ucne.empleosdoapp.data.remote.repository.EmpleoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GuardadosUiState(
    val empleo: EmpleoDto = EventoDto(
        0,
        "",
        "",
        "",
        "",
    )
)

data class EmpleosLocalUiState(
    val empleos: List<Empleos> = emptyList()
)

@HiltViewModel
class GuardadosViewModel @Inject constructor(
    private val repository: EmpleoRepository,
    private val repositoryLocal: EmpleosRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(GuardadosUiState())
    val uiState: StateFlow<GuardadosUiState> = _uiState.asStateFlow()
    private val _uiStateLocal = MutableStateFlow(EmpleosLocalUiState())
    val uiStateLocal: StateFlow<EmpleosLocalUiState> = _uiStateLocal.asStateFlow()

    init {
        viewModelScope.launch {
            repositoryLocal.getAll().collect { list ->
                _uiStateLocal.update {
                    it.copy(empleos = list)
                }
            }
        }
    }

    fun getById(id: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(empleo = repository.getEmpleo(id))
            }
        }
    }

    fun Delete(empleos: Empleos) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryLocal.delete(empleos)
        }
    }
}