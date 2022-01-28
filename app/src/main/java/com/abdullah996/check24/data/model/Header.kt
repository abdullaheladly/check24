package com.abdullah996.check24.data.model


import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("headerDescription")
    val headerDescription: String,
    @SerializedName("headerTitle")
    val headerTitle: String
)