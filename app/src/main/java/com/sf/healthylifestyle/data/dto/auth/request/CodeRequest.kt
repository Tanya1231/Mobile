package com.sf.healthylifestyle.data.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CodeRequest(
    @SerialName("code") val code: String,
)
