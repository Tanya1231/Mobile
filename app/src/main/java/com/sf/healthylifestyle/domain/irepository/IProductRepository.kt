package com.sf.healthylifestyle.domain.irepository

import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.domain.models.Dish

interface IProductRepository {
    suspend fun get(type: String): List<ProductResponse>?

    suspend fun getDish(id: Int): Dish?
}