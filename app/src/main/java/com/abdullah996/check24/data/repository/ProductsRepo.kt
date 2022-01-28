package com.abdullah996.check24.data.repository

import com.abdullah996.check24.data.model.BaseResponse

interface ProductsRepo {
    suspend fun getAllProducts(
    ):BaseResponse
}