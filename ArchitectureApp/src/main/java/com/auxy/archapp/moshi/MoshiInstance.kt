package com.auxy.archapp.moshi

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiInstance {
    fun create(): Moshi = Moshi.Builder().apply {
        add(Rfc3339DateJsonAdapter().nullSafe())
        add(MoshiDateAdapter())
        add(MoshiLatLngAdapter())
        add(KotlinJsonAdapterFactory())
    }.build()
}
