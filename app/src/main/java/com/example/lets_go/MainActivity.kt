package com.example.lets_go

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        Log.d("SHOW_LOG", "onCreate - Main Activity")
//        val controller = findNavController(R.id.nav_host_fragment)
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//
//        val appBarConfig = AppBarConfiguration(setOf(
//            R.id.mainFragment,
//            R.id.notificationFragment,
//            R.id.profileFragment
//
//        ))
//
//        setupActionBarWithNavController(controller, appBarConfig)
//        bottomNavigationView.setupWithNavController(controller)



    }


}