package com.sf.healthylifestyle.data.mappers.dish

import com.sf.healthylifestyle.data.dbo.entity.DishEntity
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import javax.inject.Inject

class DishMapper @Inject constructor() : IDishMapper {

    override fun toDishes(entity: List<DishEntity>): List<ProductResponse> =
        entity.map {
            it.toDish()
        }

    private fun DishEntity.toDish(): ProductResponse {
        return ProductResponse(
            id = this.id,
            title = this.title,
            subtitle = this.subtitle,
            image_extra = this.image_extra,
            price = this.price,
            weight = this.weight,
            calories = this.calories,
        )
    }
}