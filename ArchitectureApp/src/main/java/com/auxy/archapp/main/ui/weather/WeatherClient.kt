package com.auxy.archapp.main.ui.weather

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherClient {
    @GET("{latitude},{longitude}")
    fun loadWeather(@Path("latitude") latitude: String, @Path("longitude") longitude: String): Single<WeatherModel>
}