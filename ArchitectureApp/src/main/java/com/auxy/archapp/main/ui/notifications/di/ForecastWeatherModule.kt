package com.auxy.archapp.main.ui.notifications.di

import com.auxy.archapp.main.ui.notifications.ForecastWeather
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ForecastWeatherModule {
    @Provides
    fun provideForecastWeather(retrofit: Retrofit): ForecastWeather =
        retrofit.create(ForecastWeather::class.java)
}
