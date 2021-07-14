package com.auxy.archapp.main.ui.weather.di

import com.auxy.archapp.main.ui.weather.WeatherClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class WeatherModule {
    @Provides
    fun provideWeatherRetrofitClient(retrofit: Retrofit): WeatherClient = retrofit.create(WeatherClient::class.java)
}
