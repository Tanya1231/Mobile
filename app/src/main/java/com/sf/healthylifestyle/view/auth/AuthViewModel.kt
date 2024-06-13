package com.sf.healthylifestyle.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sf.healthylifestyle.domain.usecases.GetTokenByEmail
import com.sf.healthylifestyle.domain.usecases.GetTokenByPhone
import com.sf.healthylifestyle.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private var _isEntry = MutableSharedFlow<Boolean>()
    val isEntry: SharedFlow<Boolean>
        get() = _isEntry.asSharedFlow()

    init {
//        login()
//        viewModelScope.launch {
//            getTokenByPhone()
//        }
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

    /*    suspend fun getTokenByPhone() {

            if (getTokenByPhone.execute()) _token.emit("token")
            else _token.emit("null token")

        }

        suspend fun getTokenByEmail() {

            if (getTokenByEmail.execute()) _token.emit("token")
            else _token.emit("null token")

        }*/

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