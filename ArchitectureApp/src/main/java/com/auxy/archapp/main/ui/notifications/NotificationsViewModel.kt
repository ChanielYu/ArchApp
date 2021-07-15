package com.auxy.archapp.main.ui.notifications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val weatherRepository: ForecastWeatherRepository
) : ViewModel() {
    private val _isBusy = MutableLiveData<Boolean>()
    val isBusy: LiveData<Boolean> = _isBusy

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    private var job: Job? = null
    private var disposable: Disposable? = null

    init {
        /*runBlocking {
            launch {
                delay(3000)
                Log.v(TAG, "3")
                Log.v(TAG, Thread.currentThread().name)
            }
            launch {
                delay(2000)
                Log.v(TAG, "2")
                Log.v(TAG, Thread.currentThread().name)
            }
            launch {
                delay(1000)
                Log.v(TAG, "1")
                Log.v(TAG, Thread.currentThread().name)
            }
        }*/
        /*viewModelScope.launch(Dispatchers.Default) {
            flow {
                for(i in 0 until 3) {
                    delay(1000)
                    emit(i)
                }
            }.collect {
                Log.v(TAG, "item = $it @ ${Thread.currentThread().name}")
            }
        }
        disposable?.dispose()
        disposable = Single.fromCallable {
            runBlocking {
                Log.v(TAG, this.toString())
                //viewModelScope.launch(Dispatchers.IO) {
                    Log.v(TAG, "Before delay: ${Thread.currentThread().name}")
                    delay(3000)
                    Log.v(TAG, "After delay: ${Thread.currentThread().name}")
                //}
                Log.v(TAG, Thread.currentThread().name)
                99
            }
        }.subscribeOn(Schedulers.single()).subscribe({
            Log.v(TAG, it.toString())
        }, {
            Log.v(TAG, it.toString())
        })*/
        /*with(viewModelScope) {
            launch {
                withContext(Dispatchers.Main.immediate) {
                    delay(3000)
                    Log.v(TAG, "3")
                    Log.v(TAG, Thread.currentThread().name)
                }
            }
            launch {
                withContext(Dispatchers.IO) {
                    delay(2000)
                    Log.v(TAG, "2")
                    Log.v(TAG, Thread.currentThread().name)
                }
            }
            launch {
                withContext(Dispatchers.Unconfined) {
                    delay(1000)
                    Log.v(TAG, "1")
                    Log.v(TAG, Thread.currentThread().name)
                }
            }
        }*/
        refresh()
        _text.value = "Waiting for notifications"
        Log.v(TAG, "Initialized")
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
        Log.v(TAG, "onCleared")
    }

    fun refresh() {
        Log.v(TAG, "Before launch ${job.toString()}")
        job = viewModelScope.launch {
            Log.v(TAG, "Before cancel ${job.toString()}")
            job?.cancelAndJoin()
            Log.v(TAG, "After cancel ${job.toString()}")
            Log.v(TAG, "Before weatherForecast ${job.toString()}")
            flow {
                emit(weatherRepository.weatherForecast("Sydney"))
            }.flowOn(
                Dispatchers.IO
            ).onStart {
                _isBusy.value = true
                Log.v(TAG, "onStart @ ${Thread.currentThread().name}")
            }.onCompletion {
                _isBusy.value = false
                Log.v(TAG, "onCompletion @ ${Thread.currentThread().name}")
            }.catch {
                Log.v(TAG, it.toString())
            }.collect {
                Log.v(TAG, it.toString())
            }
            Log.v(TAG, "After weatherForecast ${job.toString()}")
            Log.v(TAG, "Before flow ${job.toString()}")
            flow {
                for (i in 0 until 9) {
                    delay(1000)
                    if (i > 6) {
                        error("Too big")
                    } else {
                        emit(i)
                    }
                    //Log.v(TAG, "emit @ ${Thread.currentThread().name}")
                }
            }.flowOn(
                Dispatchers.Default
            ).onStart {
                _isBusy.value = true
                Log.v(TAG, "onStart @ ${Thread.currentThread().name}")
            }.onCompletion {
                _isBusy.value = false
                Log.v(TAG, "onCompletion @ ${Thread.currentThread().name}")
            }.catch {
                Log.v(TAG, it.toString())
            }.collect {
                Log.v(TAG, "item = $it @ ${Thread.currentThread().name}")
            }
            Log.v(TAG, "After flow ${job.toString()}")
        }
        Log.v(TAG, "After launch ${job.toString()}")
        /*viewModelScope.launch {
            job?.cancelAndJoin()
            job = flow {
                for (i in 0 until 9) {
                    delay(1000)
                    if (i > 6) {
                        error("Too big")
                    } else {
                        emit(i)
                    }
                    Log.v(TAG, "emit @ ${Thread.currentThread().name}")
                }
            }.flowOn(
                Dispatchers.Default
            ).onStart {
                _isBusy.value = true
                Log.v(TAG, "onStart @ ${Thread.currentThread().name}")
            }.onCompletion {
                _isBusy.value = false
                Log.v(TAG, "onCompletion @ ${Thread.currentThread().name}")
            }.onEach {
                Log.v(TAG, "item = $it @ ${Thread.currentThread().name}")
            }.catch {
                Log.v(TAG, it.toString())
            }.launchIn(viewModelScope)
        }*/
    }

    companion object {
        private const val TAG = "NotificationsViewModel"
    }
}
