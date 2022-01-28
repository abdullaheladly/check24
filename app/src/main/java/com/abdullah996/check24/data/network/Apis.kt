package com.abdullah996.check24.data.network

import com.abdullah996.check24.data.model.BaseResponse
import retrofit2.http.GET

interface Apis {
    @GET("products-test.json")
    suspend fun getAllProducts():BaseResponse
}