package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.repository.AuthRepository


class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend fun invoke(): Boolean = true
}