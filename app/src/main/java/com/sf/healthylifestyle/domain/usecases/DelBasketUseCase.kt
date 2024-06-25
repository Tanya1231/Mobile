package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.repository.BasketRepository

class DelBasketUseCase(
    private val repository: BasketRepository
) {

    suspend operator fun invoke() {
        repository.delAll()
    }
}