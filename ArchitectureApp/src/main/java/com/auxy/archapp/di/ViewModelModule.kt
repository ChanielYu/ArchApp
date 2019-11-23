package com.auxy.archapp.di

import androidx.lifecycle.ViewModel
import com.auxy.archapp.main.ui.home.HomeViewModel
import com.auxy.archapp.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel
    // Add more ViewModels here.
}