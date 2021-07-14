package com.auxy.archapp.main.ui.dashboard

import com.auxy.archapp.main.ui.weather.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherFlowClient {
    @GET("{latitude},{longitude}")
    suspend fun loadWeather(@Path("latitude") latitude: String, @Path("longitude") longitude: String): WeatherModel
}
