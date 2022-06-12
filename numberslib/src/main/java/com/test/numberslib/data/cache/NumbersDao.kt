package com.test.numberslib.data.cache

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NumbersDao {

    @Query("SELECT * FROM Number")
    fun numbers(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNumbers(numbers: List<Int>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(number: Int)

    @Delete
    suspend fun delete(number: Int)

}