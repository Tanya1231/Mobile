package com.sf.healthylifestyle.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sf.healthylifestyle.domain.usecases.GetTokenByEmail
import com.sf.healthylifestyle.domain.usecases.GetTokenByPhone
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val getTokenByPhone: GetTokenByPhone,
    private val getTokenByEmail: GetTokenByEmail,
    ) : ViewModel() {

    private var _token = MutableSharedFlow<String>()
    val token: SharedFlow<String>
        get() = _token.asSharedFlow()

    init {
        viewModelScope.launch {
            getTokenByPhone()
        }
    }

    suspend fun getTokenByPhone() {

        if (getTokenByPhone.execute()) _token.emit("token")
        else _token.emit("null token")

    }

    suspend fun getTokenByEmail() {

        if (getTokenByEmail.execute()) _token.emit("token")
        else _token.emit("null token")

    }

    class Factory(
        val getTokenByPhone: GetTokenByPhone,
        val getTokenByEmail: GetTokenByEmail,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                return AuthViewModel(
                    getTokenByPhone = getTokenByPhone,
                    getTokenByEmail = getTokenByEmail
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}