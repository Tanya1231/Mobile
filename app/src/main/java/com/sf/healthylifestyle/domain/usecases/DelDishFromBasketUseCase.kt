package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.repository.BasketRepository

class DelDishFromBasketUseCase(
    private val repository: BasketRepository
) {

    suspend operator fun invoke(id: Int) {
        repository.removeDish(id)
    }
}