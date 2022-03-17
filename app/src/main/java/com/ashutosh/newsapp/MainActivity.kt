package com.ashutosh.newsapp

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ashutosh.newsapp.api.RetrofitBuilder
import com.ashutosh.newsapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.lang.Error

class MainActivity : AppCompatActivity(){


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var nav_view: BottomNavigationView
    val TAG = "MainActivity"

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view = findViewById(R.id.nav_view)

        navController = findNavController(R.id.nav_host_fragment_activity_main)


        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_news,
            R.id.navigation_bookmark)
            .build()


        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)


    }



}