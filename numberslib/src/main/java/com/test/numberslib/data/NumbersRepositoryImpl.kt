package com.test.numberslib.data

import com.test.numberslib.data.network.NumbersApi
import com.test.numberslib.domain.NumbersRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This is a concrete implementation of repository.
 */
@Singleton
class NumbersRepositoryImpl @Inject constructor(private val api: NumbersApi) : NumbersRepository {

    private var numbers: MutableList<Int>? = null

    override suspend fun fetchNumbers(): NumbersResult = try {
        numbers = api.numbers()?.toMutableList()
        NumbersResult.Success(numbers)
    } catch (e: Exception) {
        NumbersResult.Error(e.message)
    }

    override suspend fun addNumber(value: Int): NumbersResult {
        numbers?.add(value)
        return NumbersResult.Success(numbers)
    }

    override suspend fun deleteNumber(value: Int): NumbersResult {
        numbers?.remove(value)
        return NumbersResult.Success(numbers)
    }
}