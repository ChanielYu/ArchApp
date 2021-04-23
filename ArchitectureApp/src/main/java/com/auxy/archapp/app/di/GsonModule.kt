package com.auxy.archapp.app.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
