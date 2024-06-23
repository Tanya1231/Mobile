package com.sf.healthylifestyle.domain.irepository

import com.sf.healthylifestyle.data.dto.auth.response.DetailResponse

interface IAuthRepository {
    suspend fun reg(userName: String, login: String): Result<DetailResponse>
    suspend fun login(login: String): Result<DetailResponse>
    suspend fun verify(code: String): Result<DetailResponse>
}