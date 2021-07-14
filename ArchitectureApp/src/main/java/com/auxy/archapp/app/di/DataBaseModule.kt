package com.auxy.archapp.app.di

import android.content.Context
import androidx.room.Room
import com.auxy.archapp.app.database.AppDatabase
import com.auxy.archapp.utils.Utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .build()

    @Provides
    fun provideWeatherModelDao(dataBase: AppDatabase) = dataBase.getWeatherModelDao()
}
