package com.auxy.archapp.main.ui.notifications.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Forecastday(
    @Json(name = "date")
    val date: String? = null,
    @Json(name = "date_epoch")
    val dateEpoch: Int? = null
)