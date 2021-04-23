package com.auxy.archapp.main.ui.weather.di

import com.auxy.archapp.main.ui.weather.WeatherClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
class WeatherModule {
    @Provides
    @Singleton
    fun provideWeatherRetrofitClient(retrofit: Retrofit): WeatherClient = retrofit.create(WeatherClient::class.java)
}
