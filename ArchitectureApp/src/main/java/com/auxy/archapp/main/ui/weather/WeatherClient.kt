package com.auxy.archapp.main.ui.weather

import com.auxy.archapp.main.ui.weather.model.WeatherModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherClient {
    @GET("{latitude},{longitude}")
    fun loadWeather(@Path("latitude") latitude: String, @Path("longitude") longitude: String): Single<WeatherModel>
}
