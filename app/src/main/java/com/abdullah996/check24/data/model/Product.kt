package com.abdullah996.check24.data.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("available")
    val available: Boolean,
    @SerializedName("color")
    val color: String,
    @SerializedName("colorCode")
    val colorCode: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageURL")
    val imageURL: String,
    @SerializedName("longDescription")
    val longDescription: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Price,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("releaseDate")
    val releaseDate: Int,
    @SerializedName("type")
    val type: String
)