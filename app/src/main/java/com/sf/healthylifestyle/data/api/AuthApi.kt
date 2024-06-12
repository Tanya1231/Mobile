package com.sf.healthylifestyle.data.api

import com.sf.healthylifestyle.data.dto.auth.request.LoginRequest
import com.sf.healthylifestyle.data.dto.auth.response.DetailResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface AuthApi {
    @POST("/login/")
    fun login(@Body loginRequest: LoginRequest): Result<DetailResponse>

    @POST("/verify/")
    fun verify(@Body loginRequest: LoginRequest): Result<DetailResponse>
}