package com.auxy.archapp.main.ui.weather.di

import com.auxy.archapp.main.ui.weather.WeatherClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class WeatherModule {
    @Provides
    @Singleton
    fun provideWeatherRetrofitClient(retrofit: Retrofit): WeatherClient {
        return retrofit.create<WeatherClient>(WeatherClient::class.java)
    }
}