package com.sf.healthylifestyle.data.mappers.dish

import com.sf.healthylifestyle.data.dbo.entity.DishEntity
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse

interface IDishMapper {
    fun toDishes(entity: List<DishEntity>): List<ProductResponse>
}