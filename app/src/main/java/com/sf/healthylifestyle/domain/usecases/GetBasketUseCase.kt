package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.repository.BasketRepository

class GetBasketUseCase(
    private val repository: BasketRepository
) {

    suspend operator fun invoke(code: String): Boolean {
        val result = repository.getAll()
        return true
    }
}