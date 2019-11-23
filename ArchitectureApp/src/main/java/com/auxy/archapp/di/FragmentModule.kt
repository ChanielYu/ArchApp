package com.auxy.archapp.di

import com.auxy.archapp.main.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment
}