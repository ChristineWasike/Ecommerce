package com.spongey.ecommerce.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.google.android.material.navigation.NavigationView
import com.spongey.ecommerce.ui.fragments.ApplesFragment
import com.spongey.ecommerce.adapters.ProductsAdapter
import com.spongey.ecommerce.R
import com.spongey.ecommerce.cart.CartActivity
import com.spongey.ecommerce.database.AppDatabase
import com.spongey.ecommerce.database.CachedProduct
import com.spongey.ecommerce.database.ProductDao
import com.spongey.ecommerce.models.Product
import com.spongey.ecommerce.ui.fragments.AdminFragment
import com.spongey.ecommerce.ui.fragments.MainFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.navigation_drawer.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.InputStream

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_drawer)
        setSupportActionBar(toolbar)


        doAsync {
            //creating an instance of the database
            val db = Room.databaseBuilder(
                applicationContext, AppDatabase::class.java, "database-name"
            ).build()

            //caching the products into the database
            db.productDao().insertAll(CachedProduct(null, "Guava Fruit", 25.00))

            val products = db.productDao().getAll()
            uiThread {
                d("Database", "Number of products: ${products.size} ${products[0].name}")
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, MainFragment()).commit()

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)


    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.actionHome -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, MainFragment()).commit()
            }

            R.id.actionApples -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, ApplesFragment()).commit()
            }

            R.id.actionAdmin -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, AdminFragment()).commit()
            }

        }


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        d("Cart", "Going to cart")
        startActivity(Intent(this, CartActivity::class.java))
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }
}
