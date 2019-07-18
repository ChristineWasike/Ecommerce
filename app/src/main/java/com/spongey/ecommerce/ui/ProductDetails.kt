package com.spongey.ecommerce.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.spongey.ecommerce.R
import com.spongey.ecommerce.cart.CartActivity
import com.spongey.ecommerce.repos.ProductsRepository
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.app_bar_main.*

class ProductDetails : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        //UpButtonEnabled
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val productTitle = intent.getStringExtra("title")
        val productImage = intent.getStringExtra("image")
        val productPrice = intent.getStringExtra("price")

        val product = ProductsRepository().getProductByName(productTitle)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                productDetailsTitle.text = it.name

                productDetailsPrice.text = "Ksh. ${it.price}"
            }, {})



        Picasso.get().load(productImage).into(productDetailsImage)

        availabilityButton.setOnClickListener {

            when (productTitle) {
                "Mango" -> AlertDialog.Builder(this)
                    .setMessage("Hey, ${productTitle}es are in stock.")
                    .setPositiveButton("OK") { p0, p1 ->

                    }
                    .create()
                    .show()
                "Grapes" -> AlertDialog.Builder(this)
                    .setMessage("Hey, $productTitle are in stock.")
                    .setPositiveButton("OK") { p0, p1 ->

                    }
                    .create()
                    .show()
                else -> AlertDialog.Builder(this)
                    .setMessage("Hey, ${productTitle}s are in stock.")
                    .setPositiveButton("OK") { p0, p1 ->

                    }
                    .create()
                    .show()
            }
        }

    }

    
}
