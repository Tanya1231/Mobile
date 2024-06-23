package com.sf.healthylifestyle.domain.irepository

import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import kotlinx.coroutines.flow.Flow

interface IBasketRepository {
    suspend fun getAll(): Flow<ProductResponse>
    suspend fun addDish(dish: ProductResponse): Boolean
    suspend fun removeDish(): Boolean
    suspend fun changeQuantity(): Boolean

}