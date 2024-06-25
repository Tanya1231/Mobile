package com.sf.healthylifestyle.domain.irepository

import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.domain.models.Dish
import kotlinx.coroutines.flow.Flow

interface IBasketRepository {
    suspend fun getAll(): List<Dish>
    suspend fun delAll()
    suspend fun addDish(dish: ProductResponse): Boolean
    suspend fun removeDish(id: Int)
    suspend fun changeQuantity(): Boolean

}