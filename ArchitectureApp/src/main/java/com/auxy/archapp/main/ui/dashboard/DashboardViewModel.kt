package com.auxy.archapp.main.ui.dashboard

import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val weatherFlowClient: WeatherFlowClient) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private var job: Job? = null

    init {
        refreshWeather()
    }

    fun refreshWeather() {
        val (lat, lng) = Location(LocationManager.NETWORK_PROVIDER)
                .apply {
                    latitude = -33.87190570512753
                    longitude = 151.209578141570
                }.let {
                    Pair(it.latitude.toString(), it.longitude.toString())
                }
        job?.cancel()
        job = viewModelScope.launch {
            flow {
                delay(5000)
                emit(weatherFlowClient.loadWeather(lat, lng))
            }.onStart {
                Log.v("viewModelScope", "Start on ${Thread.currentThread().name}")
            }.onCompletion {
                Log.v("viewModelScope", "Complete on ${Thread.currentThread().name}")
            }.catch {

            }.flowOn(Dispatchers.IO).collect {
                Log.v("viewModelScope", Thread.currentThread().name)
            }
        }
    }
}
