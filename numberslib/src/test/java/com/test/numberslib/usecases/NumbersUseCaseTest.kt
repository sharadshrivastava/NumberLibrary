package com.test.numberslib.usecases


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.numberslib.common.readJson
import com.test.numberslib.data.NumbersRepositoryImpl
import com.test.numberslib.data.NumbersResult
import com.test.numberslib.data.cache.entity.Data
import com.test.numberslib.domain.usecases.NumbersUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NumbersUseCaseTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: NumbersRepositoryImpl

    private lateinit var numbersUseCase: NumbersUseCase

    @Before
    fun setup() {
        numbersUseCase = NumbersUseCase(repository)
    }

    @Test
    fun testFetchNumbers() {
        runBlocking {
            val responseDto: List<Int> = readJson("response.json")
            val response = responseDto.map {
                Data(value = it)
            }
            val expected = flowOf(response)
            Mockito.`when`(repository.fetchNumbers()).thenReturn(NumbersResult.Success(expected))

            val actual = numbersUseCase.fetchNumbers()
            if (actual is NumbersResult.Success ) {
                Assert.assertEquals(expected, actual.numbers)
            }
        }
    }

    @Test
    fun testAverage() {
        runBlocking {
            val expected = 2.5
            Mockito.`when`(repository.average()).thenReturn(2.5)

            val actual = numbersUseCase.average()
            Assert.assertEquals(expected, actual)
        }
    }
}
