package com.sf.healthylifestyle.view.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sf.healthylifestyle.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

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
                _isEntry.emit(loginUseCase(login))
            }
        }
    }

    fun switch() {
        viewModelScope.launch {
            _regState.emit(RegState.Reg(RegState.PHONE_OR_EMAIL))
        }
    }

    fun reg() {
        viewModelScope.launch {
            _regState.emit(RegState.Confirm(null))
        }
    }

    fun confirm() {
        viewModelScope.launch {
            _regState.emit(RegState.Done(null))
        }
    }

//    suspend fun getTokenByPhone() {
//
//        if (getTokenByPhone.execute()) _token.emit("token")
//        else _token.emit("null token")
//
//    }
//
//    suspend fun getTokenByEmail() {
//
//        if (getTokenByEmail.execute()) _token.emit("token")
//        else _token.emit("null token")
//
//    }

    class Factory(
        val loginUseCase: LoginUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
                return RegisterViewModel(
                    loginUseCase = loginUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    /*    @Suppress("UNCHECKED_CAST")
        class Factory(
            val registerUser: RegisterUser,
        ) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(
                        RegisterViewModel::
                        class.java
                    )
                ) {
                    return RegisterViewModel(
                        registerUser = registerUser,
                    ) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }*/
}