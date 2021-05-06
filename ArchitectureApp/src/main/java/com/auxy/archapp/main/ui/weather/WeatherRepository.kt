package com.auxy.archapp.main.ui.weather

import android.location.Location
import com.auxy.archapp.main.ui.weather.database.WeatherModelDao
import javax.inject.Inject

class WeatherRepository @Inject constructor(
        private val weatherClient: WeatherClient,
        private val weatherModelDao: WeatherModelDao) {
    fun loadWeather(location: Location) =
            weatherClient.loadWeather(location.latitude.toString(), location.longitude.toString())
}
