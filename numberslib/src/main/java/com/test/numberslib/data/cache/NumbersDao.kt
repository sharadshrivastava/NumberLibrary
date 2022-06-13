package com.test.numberslib.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.numberslib.data.cache.entity.Data
import kotlinx.coroutines.flow.Flow

@Dao
interface NumbersDao {

    @Query("SELECT * FROM Data")
    fun numbers(): Flow<List<Data>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumbers(numbers: List<Data>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumber(data: Data)

    @Query("DELETE FROM Data WHERE value = :number")
    suspend fun delete(number: Int)

}