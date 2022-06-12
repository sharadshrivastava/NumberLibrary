package com.test.numberslib.domain.usecases

import com.test.numberslib.data.NumbersRepositoryImpl
import javax.inject.Inject

class NumbersUseCase @Inject constructor(
    private val repository: NumbersRepositoryImpl
) {

    suspend fun fetchNumbers() = repository.fetchNumbers()

    //suspend fun addNumber(value: Int) = repository.numbers()

    //suspend fun deleteNumber(value: Int)
}
