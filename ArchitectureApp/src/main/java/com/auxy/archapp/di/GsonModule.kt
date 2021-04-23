package com.auxy.archapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class GsonModule {
    @Singleton
    @Provides
    internal fun provideGson(): Gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, typeOfT, _ ->
                if (typeOfT == Date::class.java && json.isJsonPrimitive) {
                    Date(json.asLong * 1000)
                } else {
                    null
                }
            })
            .create()
}
