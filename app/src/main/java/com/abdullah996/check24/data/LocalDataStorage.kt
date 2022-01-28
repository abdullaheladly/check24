package com.abdullah996.check24.data

import com.abdullah996.check24.data.database.Products
import com.abdullah996.check24.data.database.ProductsDao
import com.abdullah996.check24.data.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataStorage @Inject constructor(
private val productsDao: ProductsDao
) {
   /* suspend fun addToFavourite(product: Product) {
        productsDao.insertProduct(product)
    }
    fun getAllProducts():Flow<List<Product>>{
        return productsDao.getAllProducts()
    }*/

}