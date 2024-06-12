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

    private var _token = MutableSharedFlow<String>()
    val token: SharedFlow<String>
        get() = _token.asSharedFlow()

    init {
        login()
        viewModelScope.launch {
//            getTokenByPhone()
        }
    }

    private fun login() {
        viewModelScope.launch {
            loginUseCase("maevski.ed@gmail.com")
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