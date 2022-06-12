package com.test.numberslib.domain

import com.test.numberslib.data.NumbersResult

interface NumbersRepository {

    suspend fun fetchNumbers(): NumbersResult
}