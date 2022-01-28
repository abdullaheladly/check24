package com.abdullah996.check24.ui.overview

import androidx.hilt.lifecycle.ViewModelInject
import com.abdullah996.check24.base.BaseViewModel
import com.abdullah996.check24.data.model.BaseResponse
import com.abdullah996.check24.data.repository.ProductRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductOverviewViewModel @ViewModelInject constructor(
    private val productRepoImpl: ProductRepoImpl
) :BaseViewModel() {
    fun getAllProducts() =handleRequestLiveData<BaseResponse> {
        val result= withContext(Dispatchers.IO){
            productRepoImpl.getAllProducts()
        }
        result.let {

        }
        emit(result)
    }
}