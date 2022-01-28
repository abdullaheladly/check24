package com.abdullah996.check24.data.repository

import com.abdullah996.check24.data.LocalDataStorage
import com.abdullah996.check24.data.RemoteDataStorage
import com.abdullah996.check24.data.model.BaseResponse
import com.abdullah996.check24.data.model.Product
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ActivityRetainedScoped
class ProductRepoImpl @Inject constructor(
    private val localDataStorage: LocalDataStorage,
    private val remoteDataStorage: RemoteDataStorage
) :ProductsRepo {
    override suspend fun getAllProducts(): BaseResponse {
        return remoteDataStorage.getAllProducts()
    }

    /*override  fun getAllFavorites(): Flow<List<Product>> {
       return localDataStorage.getAllProducts()
    }*/

    override suspend fun addFavourite(product: Product) {
        //localDataStorage.addToFavourite(product)
    }


}