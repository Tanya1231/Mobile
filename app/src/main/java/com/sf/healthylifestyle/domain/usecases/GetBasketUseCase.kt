package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.repository.BasketRepository
import com.sf.healthylifestyle.domain.models.Dish
import kotlinx.coroutines.flow.Flow

class GetBasketUseCase(
    private val repository: BasketRepository
) {

    suspend operator fun invoke(): List<Dish> {
        val result = repository.getAll()
        return result
    }
}