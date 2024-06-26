package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.repository.ProductRepository
import com.sf.healthylifestyle.domain.models.Dish

class GetDishByIdUseCase(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(id: Int): Dish? {
        val result = repository.getDish(id)
        return result
    }
}