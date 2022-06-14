package com.test.numberslib.data

import com.test.numberslib.data.cache.NumbersDao
import com.test.numberslib.data.cache.entity.Data
import com.test.numberslib.data.network.NumbersApi
import com.test.numberslib.domain.NumbersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This is a concrete implementation of repository.
 */
@Singleton
class NumbersRepositoryImpl @Inject constructor(
    private val api: NumbersApi,
    private val dao: NumbersDao?
) : NumbersRepository {

    override suspend fun fetchNumbers(): NumbersResult = try {
        val numbers = api.numbers()
        if (!numbers.isNullOrEmpty()) {
            dao?.insertNumbers(mapToData(numbers))
        }
        NumbersResult.Success(dao?.numbers())
    } catch (e: Exception) {
        NumbersResult.Error(e.message)
    }

    override suspend fun addNumber(value: Int) {
        dao?.insertNumber(Data(value = value))
    }

    override suspend fun deleteNumber(value: Int) {
        dao?.delete(value)
    }

    override suspend fun average() = dao?.average()

    private fun mapToData(list: List<Int>) = list.map { Data(value = it) }
}