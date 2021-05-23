package com.auxy.archapp.app.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.auxy.archapp.AndroidTestUtils
import com.auxy.archapp.main.ui.weather.database.WeatherData
import com.auxy.archapp.main.ui.weather.database.WeatherModelDao
import com.auxy.archapp.main.ui.weather.model.WeatherModel
import com.auxy.archapp.moshi.MoshiInstance
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.*

class AppDatabaseTest {
    private lateinit var dataBase: AppDatabase
    private lateinit var weatherModelDao: WeatherModelDao

    private val moshi = MoshiInstance.create()
    private var weatherMode: WeatherModel? = null

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dataBase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        weatherModelDao = dataBase.getWeatherModelDao()
        weatherMode = AndroidTestUtils.readJsonFile(context, "Sydney_weather.json")?.let {
            moshi.adapter(WeatherModel::class.java).fromJson(it)
        }
    }

    @After
    fun tearDown() {
        dataBase.close()
    }

    @Test
    fun weatherModelDaoTest() {
        weatherMode?.currently?.time?.let {
            weatherModelDao.insertWeather(
                WeatherData(
                    it.time / 1000,
                    weatherMode?.currently?.summary
                )
            )
        }
        val expiredWeathers = weatherModelDao.getExpiredWeathers()
        assertTrue(expiredWeathers[0].timeSeconds < Date().time / 1000)
    }
}
