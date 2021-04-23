package com.auxy.archapp.moshi

import com.google.android.gms.maps.model.LatLng
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonReader.Token.BEGIN_OBJECT
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

class MoshiLatLngAdapter {
    @ToJson
    fun toJson(writer: JsonWriter, latLng: LatLng) {
        with(writer) {
            beginObject()
            name(LATITUDE).value(latLng.latitude)
            name(LONGITUDE).value(latLng.longitude)
            endObject()
        }
    }

    @FromJson
    fun fromJson(reader: JsonReader): LatLng? = with(reader) {
        when (peek()) {
            BEGIN_OBJECT -> {
                var lat = 0.0
                var lng = 0.0
                beginObject()
                while (hasNext()) {
                    when (nextName()) {
                        LATITUDE -> lat = nextDouble()
                        LONGITUDE -> lng = nextDouble()
                        else -> skipValue()
                    }
                }
                endObject()
                LatLng(lat, lng)
            }
            else -> {
                skipValue()
                null
            }
        }
    }

    companion object {
        private const val LATITUDE = "lat"
        private const val LONGITUDE = "lng"
    }
}
