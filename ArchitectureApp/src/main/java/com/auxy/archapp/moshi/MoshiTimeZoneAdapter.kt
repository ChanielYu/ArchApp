package com.auxy.archapp.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.*

class MoshiTimeZoneAdapter {
    @ToJson
    fun toJson(timeZone: TimeZone): String? = timeZone.id

    @FromJson
    fun fromJson(timeZone: String): TimeZone? = try {
        TimeZone.getTimeZone(timeZone)
    } catch (ex: Exception) {
        null
    }
}