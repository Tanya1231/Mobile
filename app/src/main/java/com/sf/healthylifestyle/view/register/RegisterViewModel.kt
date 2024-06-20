package com.sf.healthylifestyle.view.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sf.healthylifestyle.domain.usecases.ConfirmUseCase
import com.sf.healthylifestyle.domain.usecases.LoginUseCase
import com.sf.healthylifestyle.domain.usecases.RegisterUseCase
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