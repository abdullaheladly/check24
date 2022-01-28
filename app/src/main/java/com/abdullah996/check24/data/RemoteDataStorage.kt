package com.abdullah996.check24.data

import com.abdullah996.check24.data.model.BaseResponse
import com.abdullah996.check24.data.network.Apis
import javax.inject.Inject

class RemoteDataStorage @Inject constructor(
private val apis: Apis) {
    suspend fun getAllProducts():BaseResponse{
        return apis.getAllProducts()
    }

}