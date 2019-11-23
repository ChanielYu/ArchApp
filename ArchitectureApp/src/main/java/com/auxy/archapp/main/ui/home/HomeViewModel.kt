package com.auxy.archapp.main.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.auxy.archapp.R
import javax.inject.Inject

class HomeViewModel @Inject constructor(context: Context) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment ${context.getString(R.string.app_name)}"
    }
    val text: LiveData<String> = _text
}