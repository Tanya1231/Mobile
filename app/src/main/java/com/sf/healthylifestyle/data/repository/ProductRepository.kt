package com.sf.healthylifestyle.data.repository

import com.sf.healthylifestyle.data.api.ProductApi
import com.sf.healthylifestyle.data.dbo.dao.BasketDao
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.data.mappers.dish.DishMapper
import com.sf.healthylifestyle.domain.irepository.IProductRepository
import com.sf.healthylifestyle.domain.models.Dish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/***
 * Перенести сюда BasketRepository все, что не относится к корзине
 * */
class ProductRepository(
    private val productApi: ProductApi,
    private val dishMapper: DishMapper
) : IProductRepository {

    override suspend fun get(type: String): List<ProductResponse>? {
        val x = productApi.getProduct(type)
//        println("Result: $x")
        if (x.isSuccess) {
            return x.getOrNull()
        }
        else  return null
    }

    override suspend fun getDish(id: Int): Dish? {
        val x = productApi.getDish(id)
//        println("Result: $x")
        return if (x.isSuccess) {
            x.getOrNull()?.let{ dishMapper.ProductResponsetoDishes(it[0])}
        } else {
            null
        }
    }
}