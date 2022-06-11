package com.test.numberslib.domain.usecases

import android.content.Context
import com.test.numberslib.data.NumbersRepositoryImpl
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NumbersUseCase @Inject constructor(
    private val repository: NumbersRepositoryImpl
) {

    suspend fun numbers() = repository.numbers()

    //suspend fun addNumber(value: Int) = repository.numbers()

    //suspend fun deleteNumber(value: Int)
}
