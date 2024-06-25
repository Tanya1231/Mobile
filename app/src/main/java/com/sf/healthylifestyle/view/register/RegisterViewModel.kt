package com.sf.healthylifestyle.view.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sf.healthylifestyle.data.dto.auth.request.CodeRequest
import com.sf.healthylifestyle.data.dto.auth.request.UserLoginRequest
import com.sf.healthylifestyle.domain.usecases.ConfirmUseCase
import com.sf.healthylifestyle.domain.usecases.LoginUseCase
import com.sf.healthylifestyle.domain.usecases.RegisterUseCase
import com.sf.healthylifestyle.view.auth.AuthState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val regUseCase : RegisterUseCase,
    private val confirmUseCase: ConfirmUseCase
) : ViewModel() {

    private var _isEntry = MutableSharedFlow<Boolean>()
    val isEntry: SharedFlow<Boolean>
        get() = _isEntry.asSharedFlow()

    private val _regState = MutableStateFlow<RegState>(RegState.Loading)
    val regState: StateFlow<RegState> = _regState

    init {
        viewModelScope.launch {
            _regState.emit(RegState.Reg(RegState.PHONE_OR_EMAIL))
        }
    }

    fun login(login: String) {
        viewModelScope.launch {
            /*** На время разработки пропускаем пустую строку*/
            if (login.isEmpty()) {
                _isEntry.emit(true)
            } else {
                _isEntry.emit(regUseCase("",login))
            }
        }
    }

    fun switch() {
        viewModelScope.launch {
            _regState.emit(RegState.Reg(RegState.PHONE_OR_EMAIL))
        }
    }

    fun reg(userLogin: UserLoginRequest) {
        println("MVVM reg: ${userLogin.username}; ${userLogin.login}")

        viewModelScope.launch {
            /*** На время разработки пропускаем пустую строку*/
            if (userLogin.login.isEmpty()) {
                _regState.emit(RegState.Confirm(null))
            } else {
                val result = regUseCase(userLogin.username, userLogin.login)
                if (result) _regState.emit(RegState.Confirm(true))
                else _regState.emit(RegState.Reg(false))
            }
        }
    }

    fun confirm(code: CodeRequest) {
        println("MVVM confirm: ${code.code}")

        viewModelScope.launch {
            if (code.code.isEmpty()) {
                _regState.emit(RegState.Done(null))
            } else {
                val result = confirmUseCase(code.code)
                if (result) _regState.emit(RegState.Done(true))
                else _regState.emit(RegState.Confirm(false))
            }
//            _authState.emit(AuthState.Done(code))
        }

//        viewModelScope.launch {
//            _regState.emit(RegState.Done(null))
//        }
    }

    class Factory(
        val regUseCase : RegisterUseCase,
        val confirmUseCase: ConfirmUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
                return RegisterViewModel(
                    regUseCase = regUseCase,
                    confirmUseCase = confirmUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}