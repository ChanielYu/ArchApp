package com.auxy.archapp.main.ui.weather

import android.location.Location
import android.location.LocationManager
import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.auxy.archapp.main.ui.weather.model.WeatherModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
        private val state: SavedStateHandle,
        private val weatherRepository: WeatherRepository
) : ViewModel() {
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

    private var job: Job? = null

    init {
        refreshWeather()
        state.contains("ABC")
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    @MainThread
    fun refreshWeather() {
        job?.cancel()
        job = viewModelScope.launch {

        }
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
