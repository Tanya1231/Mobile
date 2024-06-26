package com.sf.healthylifestyle.data.mappers.dish

import com.sf.healthylifestyle.data.dbo.entity.DishEntity
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.domain.models.Dish
import kotlinx.serialization.SerialName
import javax.inject.Inject

class DishMapper @Inject constructor() : IDishMapper {

    override fun toDishes(entity: List<DishEntity>): List<Dish> =
        entity.map {
            it.toDish()
        }

    override fun ProductResponsetoDishes(response: ProductResponse): Dish =
        response.toDish()

    private fun ProductResponse.toDish(): Dish {
        return Dish(
            id = this.id,
            rating = this.rating,
            likes = this.likes,
            title = this.title,
            subtitle = this.subtitle,
            image = this.image,
            image_extra = this.image_extra,
            calories = this.calories,
            proteins = this.proteins,
            fats =this.fats,
            carbs = this.carbs,
            price = this.price,
            description = this.description,
            cooking_method = this.cooking_method,
            weight = this.weight,
            ingredients = this.ingredients,
            is_prepared = this.is_prepared,
            category = this.category,
            tag = this.tag,
        )
    }

    private fun DishEntity.toDish(): Dish {
        return Dish(
            id = this.id,
            title = this.title,
            subtitle = this.subtitle,
            image_extra = this.image_extra,
            price = this.price,
            weight = this.weight,
            calories = this.calories,
            category = null,
            tag = null,
            quantity = this.quantity
        )
    }
}