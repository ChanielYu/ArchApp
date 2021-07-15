package com.auxy.archapp.main.ui.notifications

import javax.inject.Inject

class ForecastWeatherRepository @Inject constructor(private val forecastWeather: ForecastWeather) {
    suspend fun weatherForecast(cityName: String, days: Int = 3) =
        forecastWeather.weatherForecast("https://weatherapi-com.p.rapidapi.com/forecast.json?q=$cityName&days=$days")
}
