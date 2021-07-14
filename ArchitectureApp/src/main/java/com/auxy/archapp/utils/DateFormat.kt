package com.auxy.archapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormat {
    val dateFormat: SimpleDateFormat
        get() = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
}