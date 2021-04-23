package com.auxy.archapp.moshi

import com.auxy.archapp.utils.DateFormat.dateFormat
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.*

class MoshiDateAdapter {
    private val format = dateFormat

    @ToJson
    fun toJson(date: Date): String? = format.format(date)

    @FromJson
    fun fromJson(date: String): Date? = try {
        format.parse(date)
    } catch (ex: Exception) {
        null
    }
}
