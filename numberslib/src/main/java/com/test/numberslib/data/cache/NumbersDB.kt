package com.test.numberslib.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Number::class], version = 1)
abstract class NumbersDB : RoomDatabase() {
    abstract fun employeesDao(): NumbersDao
}