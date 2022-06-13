package com.test.numberslib.domain

import com.test.numberslib.data.NumbersResult
import kotlinx.coroutines.flow.Flow

interface NumbersRepository {

    suspend fun fetchNumbers(): NumbersResult

    suspend fun addNumber(value: Int)

    suspend fun deleteNumber(value: Int)

    suspend fun average(): Double?
}