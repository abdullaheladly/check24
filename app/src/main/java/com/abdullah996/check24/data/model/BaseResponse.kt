package com.abdullah996.check24.data.model



import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("filters")
    val filters: List<String>,
    @SerializedName("header")
    val header: Header,
    @SerializedName("products")
    val products: List<Product>
)