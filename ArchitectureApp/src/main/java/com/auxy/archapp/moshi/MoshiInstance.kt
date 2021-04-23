package com.auxy.archapp.moshi

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiInstance {
    fun create(): Moshi = Moshi.Builder().apply {
        add(KotlinJsonAdapterFactory())
        add(MoshiLatLngAdapter())
        add(MoshiMilliDateAdapter())
        add(MoshiDateAdapter())
        add(MoshiTimeZoneAdapter())
        //add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
    }.build()
}
