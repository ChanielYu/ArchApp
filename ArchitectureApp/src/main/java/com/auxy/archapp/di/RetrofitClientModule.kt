package com.auxy.archapp.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitClientModule {
    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
    }

    /*@Provides
    @Singleton
    internal fun provideWeatherRetrofitClient(retrofit: Retrofit): WeatherClient {
        return retrofit.create<WeatherClient>(WeatherClient::class.java)
    }*/

    companion object {
        private const val baseUrl = "https://api.forecast.io/forecast/a52faed7963ddaddf5139ab91f066c6d/"
        private const val CONNECT_TIMEOUT = 15L
        private const val READ_TIMEOUT = 10L
    }
}