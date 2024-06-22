package com.sf.healthylifestyle.domain.irepository

import com.sf.healthylifestyle.data.dto.product.response.ProductResponse

interface IProductRepository {
    suspend fun get(type: String): List<ProductResponse>?
}