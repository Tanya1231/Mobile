package com.sf.healthylifestyle.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sf.healthylifestyle.domain.usecases.GetTokenByPhone
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val getTokenByPhone: GetTokenByPhone
    ) : ViewModel() {

    private var _token = MutableSharedFlow<String>()
    val token: SharedFlow<String>
        get() = _token.asSharedFlow()

    init {
        viewModelScope.launch {
            getToken()
        }
    }

    suspend fun getToken() {

        if (getTokenByPhone.execute()) _token.emit("token")
        else _token.emit("null token")

    }

    class Factory(
        val getTokenByPhone: GetTokenByPhone
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                return AuthViewModel(
                    getTokenByPhone = getTokenByPhone
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}