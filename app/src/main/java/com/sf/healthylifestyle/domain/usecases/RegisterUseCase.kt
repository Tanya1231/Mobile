package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.repository.AuthRepository


class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(userName: String, login: String): Boolean {
        val result = repository.reg(userName, login)
        return result.isSuccess
    }
}