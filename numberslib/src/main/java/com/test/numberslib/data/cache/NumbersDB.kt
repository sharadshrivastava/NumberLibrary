package com.test.numberslib.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.numberslib.data.cache.entity.Data

@Database(entities = [Data::class], version = 1)
abstract class NumbersDB : RoomDatabase() {
    abstract fun numbersDao(): NumbersDao
}