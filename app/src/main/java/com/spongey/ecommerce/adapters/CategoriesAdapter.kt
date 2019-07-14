package com.spongey.ecommerce.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spongey.ecommerce.R

class CategoriesAdapter(val categories: List<String>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.category.text = categories[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = categories.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val category: TextView = itemView.findViewById(R.id.categoryName)
    }
}