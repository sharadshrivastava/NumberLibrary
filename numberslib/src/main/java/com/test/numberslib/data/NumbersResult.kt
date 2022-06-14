package com.test.numberslib.data

import com.test.numberslib.data.cache.entity.Data
import kotlinx.coroutines.flow.Flow

sealed class NumbersResult {

    data class Success(val numbers: Flow<List<Data>>?) : NumbersResult()

    data class Error(val msg: String?) : NumbersResult()
}
