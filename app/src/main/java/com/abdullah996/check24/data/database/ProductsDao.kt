package com.abdullah996.check24.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdullah996.check24.data.model.Product
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductsDao {
   /* @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM products_table ORDER BY id ASC")
     fun getAllProducts(): Flow<List<Product>>*/
}