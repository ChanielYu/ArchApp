package com.auxy.archapp.main.ui.weather.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherModelDao {
    @Insert
    fun insertWeather(vararg weatherData: WeatherData)

    @Delete
    fun deleteWeather(vararg weatherData: WeatherData)

    @Query("SELECT * FROM WeatherData WHERE timeSeconds < strftime('%s','now')")
    fun getExpiredWeathers(): List<WeatherData>
}
