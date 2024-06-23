package com.sf.healthylifestyle.data.dbo.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sf.healthylifestyle.data.dbo.entity.DishEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(dishEntity: DishEntity)

    @Delete
    fun delete(dishEntity: DishEntity)

    @Query("SELECT * FROM DISHES")
    fun getAll(): List<DishEntity>

    @Query("SELECT * FROM DISHES WHERE id = :id")
    fun get(id: Int): Flow<DishEntity>

    @Query("UPDATE dishes SET quantity = :quantity WHERE id = :id")
    fun updateQuantity(
        id: Int,
        quantity: Int
    )

}