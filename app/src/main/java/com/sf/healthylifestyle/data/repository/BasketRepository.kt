package com.sf.healthylifestyle.data.repository

import com.sf.healthylifestyle.data.dbo.dao.BasketDao
import com.sf.healthylifestyle.data.dbo.entity.DishEntity
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.data.mappers.dish.DishMapper
import com.sf.healthylifestyle.domain.irepository.IBasketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext

class BasketRepository(
    private val basketDao: BasketDao,
    private val dishMapper: DishMapper,
) : IBasketRepository {

    override suspend fun getAll(): Flow<ProductResponse> {
        val listDishes = withContext(Dispatchers.IO) {
            dishMapper.toDishes(basketDao.getAll())
        }
        return listDishes.asFlow()
    }

    override suspend fun addDish(dish: ProductResponse): Boolean {
        withContext(Dispatchers.IO) {
            basketDao.add(
                DishEntity(
                    id = dish.id,
                    title = dish.title,
                    subtitle = dish.subtitle,
                    image_extra = dish.image_extra,
                    price = dish.price,
                    weight = dish.weight,
                    calories = dish.calories,
                    quantity = 1
                )
            )
        }
        return true
    }

    override suspend fun removeDish(): Boolean {
        return true
    }

    override suspend fun changeQuantity(): Boolean {
        return true
    }
}