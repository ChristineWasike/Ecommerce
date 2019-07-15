package com.spongey.ecommerce.repos

import com.google.gson.Gson
import com.spongey.ecommerce.models.Product
import io.reactivex.Single
import io.reactivex.Single.create
import java.net.URL

class ProductsRepository {

    fun getAllProducts(): Single<List<Product>> {

        return create<List<Product>> {
            val json = URL("https://api.myjson.com/bins/1gf1kz").readText()
            val products = Gson().fromJson(json, Array<Product>::class.java).toList()
            it.onSuccess(products)
        }
    }

}