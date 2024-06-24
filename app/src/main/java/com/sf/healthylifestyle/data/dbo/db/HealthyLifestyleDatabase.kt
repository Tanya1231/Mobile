package com.sf.healthylifestyle.data.dbo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sf.healthylifestyle.data.dbo.dao.BasketDao
import com.sf.healthylifestyle.data.dbo.entity.DishEntity

@Database(entities = [DishEntity::class], version = 1, exportSchema = false)
abstract class HealthyLifestyleDatabase : RoomDatabase() {
    abstract fun basketDao(): BasketDao
}