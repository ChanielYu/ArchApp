package com.auxy.archapp.main.ui.weather.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherData(
        @PrimaryKey
        val timeSeconds: Long,
        @ColumnInfo(name = "weather_model")
        val weatherModel: String?//WeatherModel
)
