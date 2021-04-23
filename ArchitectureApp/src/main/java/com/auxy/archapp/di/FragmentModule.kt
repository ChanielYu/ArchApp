package com.auxy.archapp.di

import com.auxy.archapp.main.ui.home.HomeFragment
import com.auxy.archapp.main.ui.weather.WeatherFragment
import com.auxy.archapp.main.ui.weather.di.WeatherModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module(includes = [WeatherModule::class])
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun weatherFragment(): WeatherFragment
}
