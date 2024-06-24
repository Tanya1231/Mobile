package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.data.repository.BasketRepository

class AddDishUseCase(
    private val repository: BasketRepository
) {

    suspend operator fun invoke(dish: ProductResponse): Boolean {
        println("AddDishUseCase -> dish: $dish")
        val result = repository.addDish(dish)
        return true
    }
}