
package com.sandeep.foodrunner.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.sandeep.foodrunner.*
import com.google.android.material.navigation.NavigationView
import com.sandeep.foodrunner.fragment.*

class DashboardActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var toolbar: Toolbar
    private lateinit var frameLayout: FrameLayout
    private lateinit var navigationView: NavigationView
    private var previousMenuItem:MenuItem?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)
        setUpToolbar()
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout,
            R.string.Open_drawer,
            R.string.Close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        openHome()

        navigationView.setNavigationItemSelectedListener {
            if(previousMenuItem!=null) {
                previousMenuItem?.isChecked = false
            }
            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it
            when (it.itemId) {
                R.id.home -> {
                    openHome()
                    drawerLayout.closeDrawers()

                }
                R.id.favouriteRestaurants ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        FavouritesFragment()
                    ).addToBackStack("Favourites").commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title="Favourites"
                }
                R.id.userProfile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        UserProfileFragment()
                    ).addToBackStack("User Profile").commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title="User Profile"
                }
                R.id.orderHistory ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        OrderHistoryFragment()
                    ).addToBackStack("Order History").commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title="Order History"
                }
                R.id.faqs ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        FAQsFragment()
                    ).addToBackStack("FAQs").commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title="FAQs"

                }
                R.id.logOut ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        LogOutFragment()
                    ).addToBackStack("Logout").commit()
                            drawerLayout.closeDrawers()
                    supportActionBar?.title="LogOut"
                }


            }
            return@setNavigationItemSelectedListener true
        }
    }
     private fun setUpToolbar(){
         setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title="Food Runner"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id= item.itemId
        if (id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    fun openHome()
    {
        val fragment= HomeFragment()
        val transaction=supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout,fragment)
                        transaction.commit()
        supportActionBar?.title="All Restaurants"
        navigationView.setCheckedItem(R.id.home)
    }

    override fun onBackPressed() {
        when(supportFragmentManager.findFragmentById(R.id.frameLayout)){
            !is HomeFragment -> openHome()
            else -> super.onBackPressed()
        }
    }

}
