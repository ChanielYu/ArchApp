package com.auxy.archapp.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.auxy.archapp.main.ui.weather.database.WeatherData
import com.auxy.archapp.main.ui.weather.database.WeatherModelDao

@Database(entities = [WeatherData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getWeatherModelDao(): WeatherModelDao
}
