package com.spongey.ecommerce.models


data class Product(
    val name: String,
    val image: String,
    val price: Double,
    val isOnSale: Boolean
)