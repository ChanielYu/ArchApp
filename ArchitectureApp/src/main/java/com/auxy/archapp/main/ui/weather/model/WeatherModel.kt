package com.auxy.archapp.main.ui.weather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class WeatherModel(
        @Json(name = "currently")
        val currently: Currently? = null,
        @Json(name = "daily")
        val daily: Daily? = null,
        @Json(name = "flags")
        val flags: Flags? = null,
        @Json(name = "hourly")
        val hourly: Hourly? = null,
        @Json(name = "latitude")
        val latitude: Double? = null,
        @Json(name = "longitude")
        val longitude: Double? = null,
        @Json(name = "offset")
        val offset: Int? = null,
        @Json(name = "timezone")
        val timezone: String? = null
) {
    data class Currently(
            @Json(name = "apparentTemperature")
            val apparentTemperature: Double? = null,
            @Json(name = "cloudCover")
            val cloudCover: Double? = null,
            @Json(name = "dewPoint")
            val dewPoint: Double? = null,
            @Json(name = "humidity")
            val humidity: Double? = null,
            @Json(name = "icon")
            val icon: String? = null,
            @Json(name = "ozone")
            val ozone: Double? = null,
            @Json(name = "precipIntensity")
            val precipIntensity: Double? = null,
            @Json(name = "precipProbability")
            val precipProbability: Double? = null,
            @Json(name = "pressure")
            val pressure: Double? = null,
            @Json(name = "summary")
            val summary: String? = null,
            @Json(name = "temperature")
            val temperature: Double? = null,
            @Json(name = "time")
            val time: Date? = null,
            @Json(name = "uvIndex")
            val uvIndex: Int? = null,
            @Json(name = "visibility")
            val visibility: Double? = null,
            @Json(name = "windBearing")
            val windBearing: Int? = null,
            @Json(name = "windGust")
            val windGust: Double? = null,
            @Json(name = "windSpeed")
            val windSpeed: Double? = null
    )

    data class Daily(
            @Json(name = "data")
            val `data`: List<Data?>? = null,
            @Json(name = "icon")
            val icon: String? = null,
            @Json(name = "summary")
            val summary: String? = null
    )

    data class Data(
            @Json(name = "apparentTemperatureHigh")
            val apparentTemperatureHigh: Double? = null,
            @Json(name = "apparentTemperatureHighTime")
            val apparentTemperatureHighTime: Int? = null,
            @Json(name = "apparentTemperatureLow")
            val apparentTemperatureLow: Double? = null,
            @Json(name = "apparentTemperatureLowTime")
            val apparentTemperatureLowTime: Int? = null,
            @Json(name = "apparentTemperatureMax")
            val apparentTemperatureMax: Double? = null,
            @Json(name = "apparentTemperatureMaxTime")
            val apparentTemperatureMaxTime: Int? = null,
            @Json(name = "apparentTemperatureMin")
            val apparentTemperatureMin: Double? = null,
            @Json(name = "apparentTemperatureMinTime")
            val apparentTemperatureMinTime: Int? = null,
            @Json(name = "cloudCover")
            val cloudCover: Double? = null,
            @Json(name = "dewPoint")
            val dewPoint: Double? = null,
            @Json(name = "humidity")
            val humidity: Double? = null,
            @Json(name = "icon")
            val icon: String? = null,
            @Json(name = "moonPhase")
            val moonPhase: Double? = null,
            @Json(name = "ozone")
            val ozone: Double? = null,
            @Json(name = "precipIntensity")
            val precipIntensity: Double? = null,
            @Json(name = "precipIntensityMax")
            val precipIntensityMax: Double? = null,
            @Json(name = "precipIntensityMaxTime")
            val precipIntensityMaxTime: Int? = null,
            @Json(name = "precipProbability")
            val precipProbability: Double? = null,
            @Json(name = "precipType")
            val precipType: String? = null,
            @Json(name = "pressure")
            val pressure: Double? = null,
            @Json(name = "summary")
            val summary: String? = null,
            @Json(name = "sunriseTime")
            val sunriseTime: Int? = null,
            @Json(name = "sunsetTime")
            val sunsetTime: Int? = null,
            @Json(name = "temperatureHigh")
            val temperatureHigh: Double? = null,
            @Json(name = "temperatureHighTime")
            val temperatureHighTime: Int? = null,
            @Json(name = "temperatureLow")
            val temperatureLow: Double? = null,
            @Json(name = "temperatureLowTime")
            val temperatureLowTime: Int? = null,
            @Json(name = "temperatureMax")
            val temperatureMax: Double? = null,
            @Json(name = "temperatureMaxTime")
            val temperatureMaxTime: Int? = null,
            @Json(name = "temperatureMin")
            val temperatureMin: Double? = null,
            @Json(name = "temperatureMinTime")
            val temperatureMinTime: Int? = null,
            @Json(name = "time")
            val time: Date? = null,
            @Json(name = "uvIndex")
            val uvIndex: Int? = null,
            @Json(name = "uvIndexTime")
            val uvIndexTime: Int? = null,
            @Json(name = "visibility")
            val visibility: Double? = null,
            @Json(name = "windBearing")
            val windBearing: Int? = null,
            @Json(name = "windGust")
            val windGust: Double? = null,
            @Json(name = "windGustTime")
            val windGustTime: Int? = null,
            @Json(name = "windSpeed")
            val windSpeed: Double? = null
    )

    data class Flags(
            @Json(name = "nearest-station")
            val nearestStation: Double?,
            @Json(name = "sources")
            val sources: List<String?>?,
            @Json(name = "units")
            val units: String?)

    data class Hourly(
            @Json(name = "data")
            val `data`: List<DataX?>?,
            @Json(name = "icon")
            val icon: String?,
            @Json(name = "summary")
            val summary: String?)

    data class DataX(
            @Json(name = "apparentTemperature")
            val apparentTemperature: Double?,
            @Json(name = "cloudCover")
            val cloudCover: Double?,
            @Json(name = "dewPoint")
            val dewPoint: Double?,
            @Json(name = "humidity")
            val humidity: Double?,
            @Json(name = "icon")
            val icon: String?,
            @Json(name = "ozone")
            val ozone: Double?,
            @Json(name = "precipIntensity")
            val precipIntensity: Double?,
            @Json(name = "precipProbability")
            val precipProbability: Double?,
            @Json(name = "pressure")
            val pressure: Double?,
            @Json(name = "summary")
            val summary: String?,
            @Json(name = "temperature")
            val temperature: Double?,
            @Json(name = "time")
            val time: Date?,
            @Json(name = "uvIndex")
            val uvIndex: Int?,
            @Json(name = "visibility")
            val visibility: Double?,
            @Json(name = "windBearing")
            val windBearing: Int?,
            @Json(name = "windGust")
            val windGust: Double?,
            @Json(name = "windSpeed")
            val windSpeed: Double?)
}