package com.example.data.storage.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.storage.room.entities.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun buyProduct(product: Product)

    @Query("SELECT * FROM product_table ORDER BY id ASC")
    suspend fun getInventory() : List<Product>
}