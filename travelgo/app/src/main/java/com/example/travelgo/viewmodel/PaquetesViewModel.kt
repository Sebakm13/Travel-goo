package com.example.travelgo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelgo.data.models.PaqueteTuristico
import com.example.travelgo.data.repository.TravelGoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class PaquetesUiState {
    object Loading : PaquetesUiState()
    data class Success(val data: List<PaqueteTuristico>) : PaquetesUiState()
    data class Error(val message: String) : PaquetesUiState()
}

class PaquetesViewModel(
    private val repo: TravelGoRepository = TravelGoRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<PaquetesUiState>(PaquetesUiState.Loading)
    val uiState: StateFlow<PaquetesUiState> = _uiState

    init { cargarPaquetes() }

    fun cargarPaquetes() {
        _uiState.value = PaquetesUiState.Loading
        viewModelScope.launch {
            val result = repo.getPaquetes()
            result.onSuccess {
                _uiState.value = PaquetesUiState.Success(it)
            }.onFailure {
                _uiState.value = PaquetesUiState.Error(it.message ?: "Error al cargar paquetes")
            }
        }
    }
}
