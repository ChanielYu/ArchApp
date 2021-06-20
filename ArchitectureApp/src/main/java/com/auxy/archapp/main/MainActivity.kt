package com.auxy.archapp.main

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
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
    private val requestPermissionLauncher =
        registerForActivityResult(RequestMultiplePermissions()) { permissionMap ->
            if (permissionMap.mapNotNull { entry ->
                    if (entry.key == Manifest.permission.CAMERA || entry.key == Manifest.permission.ACCESS_FINE_LOCATION) {
                        entry.value
                    } else {
                        null
                    }
                }.all { it }) {
                Toast.makeText(this, "All Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Oops! Need permissions", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment).navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(
                setOf(
                    R.id.navigation_home,
                    R.id.navigation_weather,
                    R.id.navigation_dashboard,
                    R.id.navigation_notifications
                )
            )
        )
        binding.navView.setupWithNavController(navController)
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }
}
