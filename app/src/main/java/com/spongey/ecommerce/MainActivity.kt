package com.spongey.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.spongey.ecommerce.model.Product
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val products = arrayListOf<Product>()

        for (i in 1..100) {
            products.add(Product("Organic Apple  #$i", "http://www.pngmart.com/files/5/Red-Apple-PNG-Photos.png", 200.00))
        }
            recyclerView.apply {
                layoutManager = GridLayoutManager(this@MainActivity, 2)
                adapter = ProductsAdapter(products)
            }
    }
}
