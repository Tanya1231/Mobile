package com.sf.healthylifestyle.domain.irepository

interface IAuthRepository {
    suspend fun login(login: String): Boolean
}