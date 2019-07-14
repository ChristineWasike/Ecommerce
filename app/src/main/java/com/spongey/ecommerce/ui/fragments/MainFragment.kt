package com.spongey.ecommerce.ui.fragments

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.gson.Gson
import com.spongey.ecommerce.R
import com.spongey.ecommerce.adapters.CategoriesAdapter
import com.spongey.ecommerce.adapters.ProductsAdapter
import com.spongey.ecommerce.database.AppDatabase
import com.spongey.ecommerce.database.CachedProduct
import com.spongey.ecommerce.models.Product
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_main.view.categoriesRecyclerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.InputStream
import java.util.ArrayList


class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        var fruits: List<Product>


        doAsync {
            //creating an instance of the database
            val db = Room.databaseBuilder(
                activity!!.applicationContext, AppDatabase::class.java, "database-name"
            ).build()

            //caching the products into the database

            //retrieve products from database
            val cachedProducts = db.productDao().getAll()

            val products = cachedProducts.map {
                Product(
                    it.name,
                    "https://pngimg.com/uploads/guava/guava_PNG15.png",
                    it.price,
                    true
                )
            }
            uiThread {
                root.recyclerView.apply {
                    layoutManager = GridLayoutManager(activity, 2)
                    adapter = ProductsAdapter(products)
                    root.progressBar.visibility = View.GONE
                    fruits = products
                }
            }

        }

//        val inputStream: InputStream = activity!!.assets.open("generated.json")
//        val inputString = inputStream.bufferedReader().use { it.readText() }
//
//
//        val products = Gson().fromJson(inputString, Array<Product>::class.java).toList()
//        root.recyclerView.apply {
//            layoutManager = GridLayoutManager(activity, 2)
//            adapter = ProductsAdapter(products)
//            root.progressBar.visibility = View.GONE
//            fruits = products
//        }
//
//        val categories: ArrayList<String> = ArrayList()
//        for (fruit in fruits)
//            categories.add(fruit.name)
//
//
//        root.categoriesRecyclerView.apply {
//            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
//            adapter = CategoriesAdapter(categories)
//        }

        return root
    }
}