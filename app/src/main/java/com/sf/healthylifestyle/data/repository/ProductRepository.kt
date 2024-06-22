package com.sf.healthylifestyle.data.repository

import com.sf.healthylifestyle.data.api.ProductApi
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.domain.irepository.IProductRepository

class ProductRepository(private val productApi: ProductApi) : IProductRepository {

    override suspend fun get(type: String): List<ProductResponse>? {
        val x = productApi.getProduct(type)
        println("Result: $x")
        if (x.isSuccess) {
            return x.getOrNull()
        }
        else  return null
    }
}