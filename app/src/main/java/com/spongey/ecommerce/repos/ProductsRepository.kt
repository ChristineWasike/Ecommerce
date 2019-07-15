package com.spongey.ecommerce.repos

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.spongey.ecommerce.models.Product
import com.spongey.ecommerce.ui.fragments.MainFragment
import io.reactivex.Single
import io.reactivex.Single.create
import java.io.InputStream
import java.net.URL

class ProductsRepository {
    private val context: Context? = null

    fun getAllProducts(): Single<List<Product>> {

        return create<List<Product>> {
            val json = URL("https://api.myjson.com/bins/1gf1kz").readText()
            val products = Gson().fromJson(json, Array<Product>::class.java).toList()
            it.onSuccess(products)
        }
    }

}