package com.spongey.ecommerce.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.spongey.ecommerce.R
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.app_bar_main.*

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setSupportActionBar(toolbar)
        val productTitle = intent.getStringExtra("title")

        productDetailsTitle.text = productTitle

        availabilityButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Hey $productTitle is in stock")
                .setPositiveButton("OK") { p0, p1 ->

                }
                .create()
                .show()
        }
    }
}
