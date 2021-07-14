package com.auxy.archapp.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.*

class MoshiMilliDateAdapter {
    @ToJson
    fun toJson(date: Date): Long = date.time / 1000

    @FromJson
    fun fromJson(date: Long): Date = Date(date * 1000)
}
