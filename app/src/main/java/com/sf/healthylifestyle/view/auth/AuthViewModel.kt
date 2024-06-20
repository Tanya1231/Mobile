package com.sf.healthylifestyle.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sf.healthylifestyle.data.dto.auth.request.CodeRequest
import com.sf.healthylifestyle.data.dto.auth.request.LoginRequest
import com.sf.healthylifestyle.domain.usecases.ConfirmUseCase
import com.sf.healthylifestyle.domain.usecases.LoginUseCase
import com.sf.healthylifestyle.view.register.RegState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val confirmUseCase: ConfirmUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState

    init {
        viewModelScope.launch {
            _authState.emit(AuthState.Auth(null))
        }
    }

    fun login(login: LoginRequest) {
        println("MVVM login: ${login.login}")

        viewModelScope.launch {
            /*** На время разработки пропускаем пустую строку*/
            if (login.login.isEmpty()) {
                _authState.emit(AuthState.Confirm(null))
            } else {
                val result = loginUseCase(login.login)
                if (result) _authState.emit(AuthState.Confirm(true))
                else _authState.emit(AuthState.Auth(false))
            }
        }
    }

    fun confirm(code: CodeRequest) {
        println("MVVM confirm: ${code.code}")

        viewModelScope.launch {
            if (code.code.isEmpty()) {
                _authState.emit(AuthState.Done(null))
            } else {
                val result = confirmUseCase(code.code)
                if (result) _authState.emit(AuthState.Done(true))
                else _authState.emit(AuthState.Confirm(false))
            }
//            _authState.emit(AuthState.Done(code))
        }
    }

    class Factory(
        val loginUseCase: LoginUseCase,
        val confirmUseCase: ConfirmUseCase

    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                return AuthViewModel(
                    loginUseCase = loginUseCase,
                    confirmUseCase = confirmUseCase

                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}