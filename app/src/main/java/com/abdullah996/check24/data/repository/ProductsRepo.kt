package com.abdullah996.check24.data.repository

import com.abdullah996.check24.data.database.Products
import com.abdullah996.check24.data.model.BaseResponse
import com.abdullah996.check24.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepo {
    suspend fun getAllProducts(
    ):BaseResponse

    /* fun getAllFavorites():Flow<List<Product>>*/

    suspend fun addFavourite(product: Product)
}