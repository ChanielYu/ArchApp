package com.auxy.archapp.main

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.auxy.archapp.R
import com.auxy.archapp.databinding.ActivityMainBinding
import com.auxy.archapp.globalcomponent.ExitEventManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var exitEventManager: ExitEventManager
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
    private val compositeDisposable = CompositeDisposable()

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
        compositeDisposable.add(exitEventManager.exitTaskEvent.subscribe {
            Log.v("ArchApp", "swipe app from task")
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Completable.timer(10, TimeUnit.MILLISECONDS, Schedulers.single()).subscribe {
            compositeDisposable.dispose()
            Log.v("ArchApp", "Post destroy performed")
        }
    }
}
