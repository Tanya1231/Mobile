package com.sf.healthylifestyle.data.api

import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("/product/")
    suspend fun getProduct(@Query("is_prepared") isPrepared: String): Result<List<ProductResponse>>

    @GET("/product/")
    suspend fun getDish(@Query("id") id: Int): Result<List<ProductResponse>>
//    suspend fun getDish(@Query("id") id: Int): Result<ProductListResponse>
}
