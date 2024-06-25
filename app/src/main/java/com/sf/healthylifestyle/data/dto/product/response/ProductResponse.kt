package com.sf.healthylifestyle.data.dto.product.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("subtitle") val subtitle: String,
    @SerialName("image_extra") val image_extra: String,
    @SerialName("price") val price: Int,
    @SerialName("weight") val weight: Int,
    @SerialName("calories") val calories: Float,
)

