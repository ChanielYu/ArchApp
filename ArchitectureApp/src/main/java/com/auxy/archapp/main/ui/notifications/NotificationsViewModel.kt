package com.auxy.archapp.main.ui.notifications

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auxy.archapp.main.ui.notifications.NotificationContract.ViewState
import com.auxy.archapp.main.ui.notifications.NotificationContract.ViewState.Shimmer
import com.auxy.archapp.main.ui.notifications.NotificationContract.ViewState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val weatherRepository: ForecastWeatherRepository
) : ViewModel() {
    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(Shimmer)
    val viewState: StateFlow<ViewState> = _viewState

    init {
        flow {
            emit(weatherRepository.loadSourceA())
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

    companion object {
        private const val TAG = "NotificationsViewModel"
    }
}
