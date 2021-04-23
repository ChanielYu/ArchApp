package com.auxy.archapp.di

import androidx.lifecycle.ViewModel
import com.auxy.archapp.main.ui.home.HomeViewModel
import com.auxy.archapp.main.ui.weather.WeatherViewModel
import com.auxy.archapp.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

//@Suppress("unused")
@InstallIn(ApplicationComponent::class)
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    internal abstract fun weatherViewModel(viewModel: WeatherViewModel): ViewModel
    // Add more ViewModels here.
}
