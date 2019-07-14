package com.spongey.ecommerce.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AlertDialog
import com.spongey.ecommerce.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.app_bar_main.*

class ProductDetails : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        val productTitle = intent.getStringExtra("title")
        val productImage = intent.getStringExtra("image")
        val productPrice = intent.getStringExtra("price")

        productDetailsTitle.text = productTitle
        Picasso.get().load(productImage).into(productDetailsImage)
        productDetailsPrice.text = "Ksh. $productPrice"


        availabilityButton.setOnClickListener {

            if (productTitle == "Mango") {
                AlertDialog.Builder(this)
                    .setMessage("Hey, ${productTitle}es are in stock.")
                    .setPositiveButton("OK") { p0, p1 ->

                    }
                    .create()
                    .show()
            } else if (productTitle == "Grapes")
                AlertDialog.Builder(this)
                    .setMessage("Hey, $productTitle are in stock.")
                    .setPositiveButton("OK") { p0, p1 ->

                    }
                    .create()
                    .show()
            else
                AlertDialog.Builder(this)
                    .setMessage("Hey, ${productTitle}s are in stock.")
                    .setPositiveButton("OK") { p0, p1 ->

                    }
                    .create()
                    .show()
        }
    }
}
