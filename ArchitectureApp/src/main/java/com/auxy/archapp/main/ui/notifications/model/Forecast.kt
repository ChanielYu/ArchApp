package com.auxy.archapp.main.ui.notifications.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Forecast(
    @Json(name = "forecastday")
    val forecastday: List<Forecastday>? = null
)