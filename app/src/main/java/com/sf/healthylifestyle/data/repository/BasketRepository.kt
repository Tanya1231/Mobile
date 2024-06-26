package com.sf.healthylifestyle.data.repository

import com.sf.healthylifestyle.data.dbo.dao.BasketDao
import com.sf.healthylifestyle.data.dbo.entity.DishEntity
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.data.mappers.dish.DishMapper
import com.sf.healthylifestyle.domain.irepository.IBasketRepository
import com.sf.healthylifestyle.domain.models.Dish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext

/***
 * Перенести из BasketRepository все, что не относится к корзине
 * */
class BasketRepository(
    private val basketDao: BasketDao,
    private val dishMapper: DishMapper,
) : IBasketRepository {

    override suspend fun getAll(): List<Dish> {
        val listDishes = withContext(Dispatchers.IO) {
            dishMapper.toDishes(basketDao.getAll())
        }
        return listDishes
    }

    override suspend fun delAll() {
        withContext(Dispatchers.IO) {
            basketDao.trunc()
        }
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

    override suspend fun removeDish(id: Int) {
        withContext(Dispatchers.IO) {
            basketDao.delete(id)
        }
    }

    override suspend fun changeQuantity(): Boolean {
        return true
    }
}