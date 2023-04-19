package com.auxy.archapp.main.ui.notifications.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherForecast(
    /*@Json(name = "current")
    val current: Current? = null,
    @Json(name = "forecast")
    val forecast: Forecast? = null,*/
    @Json(name = "location")
    val location: Location? = null
)
