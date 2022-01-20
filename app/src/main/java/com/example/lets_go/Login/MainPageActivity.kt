package com.example.lets_go.Login

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lets_go.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainPageActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        Log.d("SHOW_LOG", "onCreate")
        supportActionBar?.hide()


        val controller = findNavController(R.id.nav_host_fragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val appBarConfig = AppBarConfiguration(setOf(
            R.id.mainFragment,
            R.id.profileFragment

        ))

        setupActionBarWithNavController(controller, appBarConfig)
        bottomNavigationView.setupWithNavController(controller)

    }


}