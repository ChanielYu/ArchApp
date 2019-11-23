package com.auxy.archapp.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ApplicationModule {
    @Binds
    abstract fun bindContext(application: Application): Context
}