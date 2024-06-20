package com.sf.healthylifestyle.data.repository

import com.sf.healthylifestyle.data.api.AuthApi
import com.sf.healthylifestyle.data.dto.auth.request.CodeRequest
import com.sf.healthylifestyle.data.dto.auth.request.LoginRequest
import com.sf.healthylifestyle.data.dto.auth.request.UserLoginRequest
import com.sf.healthylifestyle.data.dto.auth.response.DetailResponse
import com.sf.healthylifestyle.domain.irepository.IAuthRepository

class AuthRepository(private val authApi: AuthApi) : IAuthRepository {

    override suspend fun reg(userName: String, login: String): Result<DetailResponse> {
        val x = authApi.reg(UserLoginRequest(userName, login))
        println("Result: $x")
        return x
    }

    override suspend fun login(login: String): Result<DetailResponse> {
        val x = authApi.login(LoginRequest(login))
        println("Result: $x")
        return x
    }

    override suspend fun verify(code: String): Result<DetailResponse> {
        val x = authApi.verify(CodeRequest(code))
        println("Result: $x")
        return x
    }
}