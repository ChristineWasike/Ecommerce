package com.spongey.ecommerce.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spongey.ecommerce.R
import com.spongey.ecommerce.adapters.ProductsAdapter.*
import com.spongey.ecommerce.models.Product
import com.spongey.ecommerce.ui.ProductDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row.view.*

class ProductsAdapter(
    private val products: List<Product>,
    private val onClickProduct: (name: String, image: String, price: Double, photoView: View) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        Picasso.get().load(product.image).into(holder.image)
        holder.title.text = product.name
        val newPrice = product.price.toString()
        holder.price.text = "Ksh. $newPrice"
        if (product.isOnSale) {
            holder.saleImage.visibility = View.VISIBLE
        } else {
            holder.saleImage.visibility = View.GONE
        }

        //when user clicks

        holder.image.setOnClickListener {
            onClickProduct.invoke(product.name, product.image, product.price, holder.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = products.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.appleImage
        val title: TextView = itemView.productTitle
        val price: TextView = itemView.priceTag
        val saleImage: ImageView = itemView.saleImage
    }
}