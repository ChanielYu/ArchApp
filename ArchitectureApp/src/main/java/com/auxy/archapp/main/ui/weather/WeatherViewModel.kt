package com.auxy.archapp.main.ui.weather

import android.location.Location
import android.location.LocationManager
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is weather Fragment"
    }
    val text: LiveData<String> = _text
    // Weather
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _weather = MutableLiveData<WeatherModel>()
    val weather: LiveData<WeatherModel> = _weather

    private var disposable: Disposable? = null

    init {
        refreshWeather()
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    @MainThread
    fun refreshWeather() {
        disposable?.dispose()
        disposable = weatherRepository.loadWeather(Location(LocationManager.NETWORK_PROVIDER)
                .apply {
                    latitude = -33.87190570512753
                    longitude = 151.209578141570
                })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    _isLoading.postValue(true)
                }
                .doFinally {
                    _isLoading.postValue(false)
                }
                .subscribe({
                    _weather.postValue(it)
                }, {
                })
    }
}