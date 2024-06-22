package com.sf.healthylifestyle.domain.usecases

import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.data.repository.AuthRepository
import com.sf.healthylifestyle.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetRightHalfUseCase(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(): List<ProductResponse>? {
        return repository.get("H2")
    }
}