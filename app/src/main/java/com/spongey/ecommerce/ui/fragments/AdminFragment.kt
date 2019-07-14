package com.spongey.ecommerce.ui.fragments

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.spongey.ecommerce.R
import com.spongey.ecommerce.database.AppDatabase
import com.spongey.ecommerce.database.CachedProduct
import kotlinx.android.synthetic.main.fragment_admin.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdminFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addProduct.setOnClickListener {
            val title = inputTitle.text
            d("Clicked","You tried to add a $title")

            doAsync {
                //creating an instance of the database
                val db = Room.databaseBuilder(
                    activity!!.applicationContext, AppDatabase::class.java, "database-name"
                ).build()

                //caching the products into the database
                db.productDao().insertAll(CachedProduct(null, title.toString(), 25.00))

                val products = db.productDao().getAll()
                uiThread {
                    d("Adding ...", "$title added to the database")
                }
            }
        }
    }


}