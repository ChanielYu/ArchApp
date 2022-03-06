package com.auxy.archapp.main.ui.notifications

class NotificationContract {
    sealed class ViewState {
        object Shimmer : ViewState()
        data class Success(val text: String) : ViewState()
    }
}
