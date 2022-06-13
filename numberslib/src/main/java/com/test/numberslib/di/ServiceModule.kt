package com.test.numberslib.di

import android.app.Application
import androidx.room.Room
import com.test.numberslib.data.cache.NumbersDB
import com.test.numberslib.data.cache.NumbersDao
import com.test.numberslib.data.network.NumbersApi
import com.test.numberslib.data.network.NumbersApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt's module to get Retrofit's network and Dao interface object.
 * This module is required because 'NumbersApi' and Dao doesn't have default constructor.
 */
@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Provides
    @Singleton
    fun provideApi(): NumbersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NumbersApi::class.java)
    }

    @Provides
    @Singleton
    fun dao(app: Application): NumbersDao = Room.databaseBuilder(
        app, NumbersDB::class.java, "app-db"
    ).build().numbersDao()
}