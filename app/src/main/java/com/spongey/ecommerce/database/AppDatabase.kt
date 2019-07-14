package com.spongey.ecommerce.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CachedProduct::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract  fun productDao(): ProductDao
}
