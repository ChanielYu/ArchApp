package com.auxy.archapp.main.ui.dashboard

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class WeatherFlowModule {
    @Provides
    fun provideWeatherRetrofitClient(retrofit: Retrofit): WeatherFlowClient = retrofit.create(WeatherFlowClient::class.java)
}
