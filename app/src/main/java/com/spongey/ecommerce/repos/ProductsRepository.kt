package com.spongey.ecommerce.repos

import com.google.gson.Gson
import com.spongey.ecommerce.models.Product
import io.reactivex.Single
import io.reactivex.Single.create
import java.net.URL

class ProductsRepository {

    fun getAllProducts(): Single<List<Product>> {

        return create {
            it.onSuccess(fetchProducts())
        }
    }


    fun searchForProducts(searchTerm: String): Single<List<Product>> {
        return create {
            val filterProducts = fetchProducts().filter {
                it.name.contains(searchTerm, true)
            }
            it.onSuccess(filterProducts)
        }
    }

    fun getProductByName(name: String): Single<Product>{
        return create {
            val product = fetchProducts().first {
                it.name == name
            }
            it.onSuccess(product)
        }
    }

    private fun fetchProducts(): List<Product> {
        val json =
            URL("https://gist.githubusercontent.com/ChristineWasike/409619a12abd3d616d6426281487b839/raw/d6b5e4489e18a16c8333cb19c20dfdec534521f3/fruit_product.json").readText()
        return Gson().fromJson(json, Array<Product>::class.java).toList()
    }

}