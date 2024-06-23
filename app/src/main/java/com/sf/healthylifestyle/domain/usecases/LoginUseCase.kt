package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val repository: AuthRepository
) {
//    suspend fun execute(login: String): Boolean {
//        repository.login(login)
//        return true
//    }

    suspend operator fun invoke(login: String): Boolean {
        val result = repository.login(login)
        return result.isSuccess
    }
}