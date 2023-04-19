package com.auxy.archapp.main.ui.notifications

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auxy.archapp.main.ui.notifications.NotificationContract.ViewState
import com.auxy.archapp.main.ui.notifications.NotificationContract.ViewState.Shimmer
import com.auxy.archapp.main.ui.notifications.NotificationContract.ViewState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@ObsoleteCoroutinesApi
@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val weatherRepository: ForecastWeatherRepository
) : ViewModel() {
    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(Shimmer)
    val viewState: StateFlow<ViewState> = _viewState

    private val byClick = AtomicBoolean(false)

    private val refreshA = Channel<AtomicBoolean>(Channel.CONFLATED).apply { trySend(byClick) }

    private val ticker = ticker(10000, 0)

    init {
        refreshA.receiveAsFlow().combine(ticker.receiveAsFlow()) { byClick, _ ->
            "${weatherRepository.loadSourceA()} by click = ${byClick.getAndSet(false)}"
        }.flowOn(
            Dispatchers.IO
        ).onEach { text ->
            _viewState.value = Success(text)
        }.launchIn(viewModelScope)
        Log.v(TAG, "Initialized")
    }

    override fun onCleared() {
        super.onCleared()
        Log.v(TAG, "onCleared")
    }

    fun refreshTextA() {
        viewModelScope.launch { refreshA.send(byClick.apply { set(true) }) }
    }

    companion object {
        private const val TAG = "NotificationsViewModel"
    }
}
