package com.auxy.archapp.main.ui.notifications

import kotlinx.coroutines.delay
import javax.inject.Inject

class ForecastWeatherRepository @Inject constructor(private val forecastWeather: ForecastWeather) {
    private var countA = 0
    private var countB = 0

    suspend fun weatherForecast(cityName: String = "Sydney", days: Int = 3) =
        forecastWeather.weatherForecast("https://weatherapi-com.p.rapidapi.com/forecast.json?q=$cityName&days=$days")

    suspend fun loadSourceA() = run {
        delay(1000)
        "A-${countA++}"
    }

    suspend fun loadSourceB() = run {
        delay(1000)
        "B-${countB++}"
    }
}
