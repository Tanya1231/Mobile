package com.sf.healthylifestyle.data.dbo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DISHES")
data class DishEntity(
    @field: ColumnInfo(name = "id") @field:PrimaryKey var id:Int,
    @field: ColumnInfo(name = "title") val title: String,
    @field: ColumnInfo(name = "subtitle") val subtitle: String,
    @field: ColumnInfo(name = "image_extra") val image_extra: String,
    @field: ColumnInfo(name = "price") val price: Int,
    @field: ColumnInfo(name = "weight") val weight: Int,
    @field: ColumnInfo(name = "calories") val calories: Float,
    @field: ColumnInfo(name = "quantity") val quantity: Int,
)
