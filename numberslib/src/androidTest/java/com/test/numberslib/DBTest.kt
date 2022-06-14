package com.test.numberslib

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.test.numberslib.data.cache.NumbersDB
import com.test.numberslib.data.cache.NumbersDao
import com.test.numberslib.data.cache.entity.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class DBTest {

    lateinit var numbersDao: NumbersDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        numbersDao = Room.inMemoryDatabaseBuilder(
            context, NumbersDB::class.java
        ).build().numbersDao()

        runBlocking {
            val list = listOf(Data(0, 10), Data(1, 20), Data(2, 30))
            numbersDao.insertNumbers(list)
        }
    }


    @Test
    fun testInsert() {
        runBlocking {
            val list = numbersDao.numbers().first()
            Assert.assertEquals(10, list.first().value)
        }
    }

    @Test
    fun testAverage() {
        runBlocking {
            val avg = numbersDao.average()
            val expected = 20.0
            Assert.assertEquals(expected, avg)
        }
    }

    @Test
    fun testDelete() {
        runBlocking {
            numbersDao.delete(20)
            val actualCount = numbersDao.numbers().first().size
            val expectedCount = 2
            Assert.assertEquals(expectedCount, actualCount)
        }
    }
}