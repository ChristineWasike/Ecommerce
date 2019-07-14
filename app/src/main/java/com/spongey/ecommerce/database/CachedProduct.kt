package com.spongey.ecommerce.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CachedProduct(
    @PrimaryKey(autoGenerate = true) val pid: Int?,
    @ColumnInfo val name: String,
//    @ColumnInfo val image: String,
    @ColumnInfo val price: Double
//    @ColumnInfo val isOnSale: Boolean
)