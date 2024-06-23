package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.repository.AuthRepository

class ConfirmUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(code: String): Boolean {
        val result = repository.verify(code)
        return result.isSuccess
    }
}