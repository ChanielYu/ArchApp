package com.auxy.archapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.auxy.archapp.R
import com.auxy.archapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment).navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        setupActionBarWithNavController(navController,
                AppBarConfiguration(setOf(R.id.navigation_home,
                        R.id.navigation_weather,
                        R.id.navigation_dashboard,
                        R.id.navigation_notifications)))
        binding.navView.setupWithNavController(navController)
    }
}
