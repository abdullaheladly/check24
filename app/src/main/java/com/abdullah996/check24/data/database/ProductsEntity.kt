package com.abdullah996.check24.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abdullah996.check24.data.model.Product


@Entity(tableName = "products_table")
class ProductsEntity(
var product: Product
) {
@PrimaryKey(autoGenerate = true)
var id:Int=0
}