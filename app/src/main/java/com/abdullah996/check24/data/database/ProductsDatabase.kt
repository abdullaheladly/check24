package com.abdullah996.check24.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters


@Database(
    entities = [ProductsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ProductsTypeConverter::class)
abstract  class ProductsDatabase:RoomDatabase() {
    abstract fun productsDao():ProductsDao
}