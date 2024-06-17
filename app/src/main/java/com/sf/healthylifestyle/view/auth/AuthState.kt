package com.sf.healthylifestyle.view.auth

sealed class AuthState {
    data class Reg<out T>(val data: T) : AuthState() {

    }

    data class Auth<out T>(val data: T) : AuthState() {
        companion object {
            fun agree(agree: Boolean) {
                AGREE = agree
            }

            fun phoneOrEmail(option: String) {
                PHONE_OR_EMAIL = option
            }
        }
    }

    data class Confirm<out T>(val data: T) : AuthState() {
        companion object {
            fun agree(agree: Boolean) {
                AGREE = agree
            }

            fun phoneOrEmail(option: String) {
                PHONE_OR_EMAIL = option
            }
        }
    }

    data class Done<out T>(val data: T) : AuthState()
    data class Error(val error: Throwable) : AuthState()
    data object Loading : AuthState()

    companion object {
        var AGREE: Boolean = false

        var PHONE_OR_EMAIL = "phone"

//        fun agree(agree: Boolean) {
//            AGREE = agree
//        }
//
//        fun phoneOrEmail(option: String) {
//            PHONE_OR_EMAIL = option
//        }

    }
}
