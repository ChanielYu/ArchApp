package com.auxy.archapp.main.ui.weather

import android.location.Location
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherClient: WeatherClient) {
    fun loadWeather(location: Location) =
            weatherClient.loadWeather(location.latitude.toString(), location.longitude.toString())
}