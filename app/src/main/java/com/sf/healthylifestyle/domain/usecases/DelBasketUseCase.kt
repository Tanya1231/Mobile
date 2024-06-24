package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.repository.BasketRepository
import com.sf.healthylifestyle.domain.models.Dish

class DelBasketUseCase(
    private val repository: BasketRepository
) {

    suspend operator fun invoke() {
        repository.delAll()
    }
}