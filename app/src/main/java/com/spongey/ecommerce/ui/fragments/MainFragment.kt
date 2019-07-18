package com.spongey.ecommerce.ui.fragments


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

import com.spongey.ecommerce.R
import com.spongey.ecommerce.adapters.CategoriesAdapter
import com.spongey.ecommerce.adapters.ProductsAdapter
import com.spongey.ecommerce.models.Product

import com.spongey.ecommerce.repos.ProductsRepository
import com.spongey.ecommerce.ui.ProductDetails
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*

import kotlinx.android.synthetic.main.fragment_main.view.categoriesRecyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick


class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)


        val fruits = listOf("Citrus", "Berries", "Apples & Pears", "Tropical & Exotic", "Melons")

        root.categoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(fruits)
        }

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val productsRepository = ProductsRepository().getAllProducts()
        loadRecyclerView(productsRepository)

        searchButton.onClick {
            loadRecyclerView(ProductsRepository().searchForProducts(searchProduct.text.toString()))
        }

    }

    private fun loadRecyclerView(productsRepository: Single<List<Product>>) {
       val single= productsRepository
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

                }
                progressBar.visibility = View.GONE
            }, {
                d("Christine", "error:( ${it.message}")
            })
    }
}