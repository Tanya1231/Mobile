package com.sf.healthylifestyle.data.repository

import com.sf.healthylifestyle.data.api.AuthApi
import com.sf.healthylifestyle.data.dto.auth.request.LoginRequest
import com.sf.healthylifestyle.domain.irepository.IAuthRepository

class AuthRepository(private val authApi: AuthApi) : IAuthRepository {
    override suspend fun login(login: String): Boolean {
        authApi.login(LoginRequest(login))
        return true
    }
}