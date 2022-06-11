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

    override suspend fun numbers(): NumbersResult = try {
        NumbersResult.Success(api.numbers())
    } catch (e: Exception) {
        NumbersResult.Error(e.message)
    }
}