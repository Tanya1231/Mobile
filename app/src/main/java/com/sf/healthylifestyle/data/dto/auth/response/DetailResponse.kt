package com.sf.healthylifestyle.data.dto.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailResponse(
    @SerialName("detail") val detail: String,
 )
