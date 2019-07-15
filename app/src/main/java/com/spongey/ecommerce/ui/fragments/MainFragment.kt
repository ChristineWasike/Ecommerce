package com.spongey.ecommerce.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
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
import com.spongey.ecommerce.repos.ProductsRepository
import com.spongey.ecommerce.ui.ProductDetails
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_main.view.categoriesRecyclerView
import kotlinx.android.synthetic.main.product_row.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.InputStream
import java.util.ArrayList


class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)


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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var fruits: List<Product>

        val productsRepository = ProductsRepository().getAllProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                d("Christine", "success:) ")
                recyclerView.apply {
                    layoutManager = GridLayoutManager(activity, 2)

                    adapter = ProductsAdapter(it) { extraName, extraPhoto, extraPrice, extraHolderImage ->

                        val intent = Intent(activity, ProductDetails::class.java)
                        intent.putExtra("title", extraName)
                        intent.putExtra("image", extraPhoto)
                        intent.putExtra("price", extraPrice.toString())
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            activity as AppCompatActivity,
                            extraHolderImage,
                            "photoToAnimate"
                        )
                        startActivity(intent, options.toBundle())
                    }


                    fruits = it
                }
                progressBar.visibility = View.GONE
            }, {
                d("Christine", "error:( ${it.message}")
            })


    }
}