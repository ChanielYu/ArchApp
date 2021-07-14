package com.auxy.archapp.app

import android.content.Intent
import androidx.multidex.MultiDexApplication
import com.auxy.archapp.service.ExitService
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins

@HiltAndroidApp
class ArchApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { throwable ->
            when (throwable) {
                is UndeliverableException -> Unit
                else -> throw throwable
            }
        }
        startService(Intent(this, ExitService::class.java))
    }
}
