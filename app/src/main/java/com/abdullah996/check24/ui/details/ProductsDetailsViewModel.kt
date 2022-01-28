package com.abdullah996.check24.ui.details

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.abdullah996.check24.data.database.Products
import com.abdullah996.check24.data.database.ProductsEntity
import com.abdullah996.check24.data.model.Product
import com.abdullah996.check24.data.repository.ProductRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsDetailsViewModel @ViewModelInject constructor(
private val productRepoImpl: ProductRepoImpl,
application: Application
):AndroidViewModel(application) {

   // val getAllProduct:LiveData<List<Product>> = productRepoImpl.getAllFavorites().asLiveData()

     fun addToFavourite(product: Product)=viewModelScope.launch(Dispatchers.IO){
        productRepoImpl.addFavourite(product)
    }

}