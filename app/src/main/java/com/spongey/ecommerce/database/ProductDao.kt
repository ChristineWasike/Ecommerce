package com.spongey.ecommerce.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM cachedProduct")
    fun getAll(): List<CachedProduct>

   @Insert
   fun insertAll(vararg products: CachedProduct)
}