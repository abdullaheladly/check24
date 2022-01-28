package com.abdullah996.check24.data.database

import androidx.room.TypeConverter
import com.abdullah996.check24.data.model.Price
import com.abdullah996.check24.data.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ProductsTypeConverter {
val gson=Gson()

    @TypeConverter
    fun productsToString(products: Products):String{
        return gson.toJson(products)
    }
    @TypeConverter
    fun stringToProducts(string: String):Products{
        val listType= object : TypeToken<Products>() {}.type
        return gson.fromJson(string,listType)
    }

    @TypeConverter
    fun productToString(product: Product):String{
        return gson.toJson(product)
    }
    @TypeConverter
    fun stringToProduct(string: String):Product{
        val listType= object : TypeToken<Product>() {}.type
        return gson.fromJson(string,listType)
    }
    @TypeConverter
    fun priceToString(product: Price):String{
        return gson.toJson(product)
    }
    @TypeConverter
    fun stringToprice(string: String):Price{
        val listType= object : TypeToken<Price>() {}.type
        return gson.fromJson(string,listType)
    }
}