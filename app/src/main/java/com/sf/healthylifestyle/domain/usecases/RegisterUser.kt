package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.repository.AuthRepository


class RegisterUser(
    private val repository: AuthRepository
) {
    suspend fun execute(): Boolean = true
}