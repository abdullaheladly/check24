package com.abdullah996.check24.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Price(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("value")
    val value: Double
):Parcelable