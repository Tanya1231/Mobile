package com.sf.healthylifestyle.data.dto.product.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    @SerialName("id") val id: Int,
    @SerialName("rating") val rating: Int,
    @SerialName("likes") val likes: Int,
    @SerialName("title") val title: String,
    @SerialName("subtitle") val subtitle: String,
    @SerialName("image") val image: String,
    @SerialName("image_extra") val image_extra: String,
    @SerialName("calories") val calories: Float,
    @SerialName("proteins") val proteins: Float,
    @SerialName("fats") val fats: Float,
    @SerialName("carbs") val carbs: Float,
    @SerialName("price") val price: Int,
    @SerialName("description") val description: String,
    @SerialName("cooking_method") val cooking_method: String,
    @SerialName("weight") val weight: Int,
    @SerialName("ingredients") val ingredients: String,
    @SerialName("is_prepared") val is_prepared: String,
    @SerialName("category") val category: ArrayList<Int>,
    @SerialName("tag") val tag: ArrayList<Int>,
)

