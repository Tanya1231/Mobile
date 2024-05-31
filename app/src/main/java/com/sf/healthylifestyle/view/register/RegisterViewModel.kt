package com.sf.healthylifestyle.view.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sf.healthylifestyle.domain.usecases.RegisterUser

class RegisterViewModel(private val registerUser: RegisterUser) : ViewModel() {

    @Suppress("UNCHECKED_CAST")
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
    }
}