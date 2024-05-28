package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.repository.AuthRepository


class GetTokenByPhone(
    private val repository: AuthRepository
) {
    suspend fun execute(): Boolean = true
}