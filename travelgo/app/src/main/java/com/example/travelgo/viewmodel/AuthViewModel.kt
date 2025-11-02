package com.example.travelgo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelgo.data.models.User
import com.example.travelgo.data.repository.TravelGoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: User, val token: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(
    private val repo: TravelGoRepository = TravelGoRepository()
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, password: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            val result = repo.login(email, password)
            result.onSuccess {
                _authState.value = AuthState.Success(it.user, it.authToken)
            }.onFailure {
                _authState.value = AuthState.Error(it.message ?: "Error desconocido")
            }
        }
    }
}
