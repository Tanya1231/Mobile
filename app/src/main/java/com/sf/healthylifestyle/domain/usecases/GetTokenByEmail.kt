package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.repository.AuthRepository


class GetTokenByEmail(
    private val repository: AuthRepository
) {
    suspend fun execute(): Boolean = true
}