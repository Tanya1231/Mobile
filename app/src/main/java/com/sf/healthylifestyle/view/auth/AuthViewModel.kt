package com.sf.healthylifestyle.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sf.healthylifestyle.data.dto.auth.request.CodeRequest
import com.sf.healthylifestyle.data.dto.auth.request.LoginRequest
import com.sf.healthylifestyle.domain.usecases.LoginUseCase
import com.sf.healthylifestyle.view.register.RegState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState

    init {
        viewModelScope.launch {
            _authState.emit(AuthState.Auth(null))
        }
    }

    fun login(login: LoginRequest) {
        viewModelScope.launch {
            /*** На время разработки пропускаем пустую строку*/
            if (login.login.isEmpty()) {
                _authState.emit(AuthState.Confirm(true))
            } else {
                _authState.emit(AuthState.Confirm(loginUseCase(login.login)))
            }
        }
    }

    fun confirm(code: CodeRequest) {
        viewModelScope.launch {
            _authState.emit(AuthState.Done(code))
        }
    }

    class Factory(
        val loginUseCase: LoginUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                return AuthViewModel(
                    loginUseCase = loginUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}