package com.auxy.archapp.main.ui.notifications

import com.auxy.archapp.main.ui.notifications.model.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Url

interface ForecastWeather {
    @GET
    suspend fun weatherForecast(
        @Url url: String,
        @HeaderMap headers: Map<String, String> = mapOf(
            Pair("x-rapidapi-key", "8457a32a4fmsh1cf0e9e972a9121p1c7b7djsn499bc07108b5"),
            Pair("x-rapidapi-host", "weatherapi-com.p.rapidapi.com")
        )
    ): WeatherForecast
}
