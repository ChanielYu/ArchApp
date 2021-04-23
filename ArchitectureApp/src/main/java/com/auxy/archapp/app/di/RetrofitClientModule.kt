package com.auxy.archapp.app.di

import com.auxy.archapp.moshi.MoshiInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitClientModule {
    @Singleton
    @Provides
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(MoshiInstance.create()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(httpClient)
                .build()
    }

    companion object {
        private const val baseUrl = "https://api.forecast.io/forecast/a52faed7963ddaddf5139ab91f066c6d/"
        private const val CONNECT_TIMEOUT = 15L
        private const val READ_TIMEOUT = 15L
    }
}
