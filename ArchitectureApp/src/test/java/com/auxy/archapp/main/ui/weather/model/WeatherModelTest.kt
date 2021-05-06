package com.auxy.archapp.main.ui.weather.model

import com.auxy.archapp.TestUtils.moshi
import com.auxy.archapp.TestUtils.readJsonFile
import kotlinx.coroutines.*
import kotlinx.coroutines.debug.junit4.CoroutinesTimeout
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

@ExperimentalCoroutinesApi
class WeatherModelTest {
    /*@get:Rule
    val timeout = CoroutinesTimeout.seconds(10)*/
    private val testDispatcher = TestCoroutineDispatcher()
    private var realVar = 0
    private val latch = CountDownLatch(1)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        //GlobalScope.cancel()
    }

    @Test
    fun `VERIFY Moshi weather model`() {
        val availableIDs = TimeZone.getAvailableIDs()
        val weather = moshi.adapter(WeatherModel::class.java).fromJson(readJsonFile("Sydney_weather.json"))
        // Verify
        assertEquals(Date(1619161048000), weather?.currently?.time)
        assertEquals(TimeZone.getTimeZone("Australia/Sydney"), weather?.timezone)
        //Location.distanceBetween()
        measureTimeMillis {
            CoroutineScope(Job() + Dispatchers.Main).launch {
                blockFun()
                val mid = realVar
                latch.countDown()
            }
        }
        latch.await(5, TimeUnit.SECONDS)
    }

    private suspend fun blockFun() {
        val async = GlobalScope.async {
            delay(1000)
            realVar++
        }
        async.await()
    }
}
