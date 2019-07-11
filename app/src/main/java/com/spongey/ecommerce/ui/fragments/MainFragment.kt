package com.spongey.ecommerce.ui.fragments

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.spongey.ecommerce.R
import com.spongey.ecommerce.adapters.ProductsAdapter
import com.spongey.ecommerce.models.Product
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.io.InputStream


class MainFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        val inputStream: InputStream = activity!!.assets.open("generated.json")
        val inputString  =inputStream.bufferedReader().use { it.readText()}

        val products = Gson().fromJson(inputString, Array<Product>::class.java).toList()

        d("Data",  inputString)
        root.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = ProductsAdapter(products)
        }
        return root
    }
}