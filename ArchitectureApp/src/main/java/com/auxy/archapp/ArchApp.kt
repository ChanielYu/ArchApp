package com.auxy.archapp

import android.content.Context
import androidx.multidex.MultiDex
import com.auxy.archapp.di.DaggerApplicationComponent
import dagger.android.support.DaggerApplication

class ArchApp : DaggerApplication() {
    override fun applicationInjector() = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}