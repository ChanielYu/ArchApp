package com.auxy.archapp.main.ui.weather.model

import com.auxy.archapp.TestUtils.moshi
import com.auxy.archapp.TestUtils.readJsonFile
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class WeatherModelTest {
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `VERIFY Moshi weather model`() {
        val availableIDs = TimeZone.getAvailableIDs()
        val weather = moshi.adapter(WeatherModel::class.java).fromJson(readJsonFile("Sydney_weather.json"))
        // Verify
        assertEquals(Date(1619161048000), weather?.currently?.time)
        assertEquals(TimeZone.getTimeZone("Australia/Sydney"), weather?.timezone)
    }
}
