package com.abdullah996.check24.data.model


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("value")
    val value: Double
)