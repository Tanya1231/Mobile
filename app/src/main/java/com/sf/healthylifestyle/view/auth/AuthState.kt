package com.sf.healthylifestyle.view.auth

sealed class AuthState {
    data class Auth<out T>(val data: T) : AuthState()
    data class Confirm<out T>(val data: T) : AuthState()
    data class Done<out T>(val data: T) : AuthState()
    data class Error(val error: Throwable) : AuthState()
    data object Loading : AuthState()
}
