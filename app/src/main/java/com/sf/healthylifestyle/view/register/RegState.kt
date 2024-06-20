package com.sf.healthylifestyle.view.register

sealed class RegState {
    data class Reg<out T>(val data: T) : RegState()
    data class Auth<out T>(val data: T) : RegState()
    data class Confirm<out T>(val data: T) : RegState()
    data class Done<out T>(val data: T) : RegState()
    data class Error(val error: Throwable) : RegState()
    data object Loading : RegState()

    companion object {

        var AGREE: Boolean = false
        var PHONE_OR_EMAIL = "phone"
    }
}
