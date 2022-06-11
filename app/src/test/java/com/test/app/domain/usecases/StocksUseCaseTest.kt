package com.test.app.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.app.RoktApp
import com.test.app.common.readJson
import com.test.app.common.toStocksItems
import com.test.app.data.StocksRepositoryImpl
import com.test.app.data.network.Resource
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
class StocksUseCaseTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var stocksRepository: StocksRepositoryImpl

    private lateinit var stocksUseCase: StocksUseCase

    @Before
    fun setup() {
        stocksUseCase = StocksUseCase(stocksRepository, RoktApp.appContext)
    }

    @Test
    fun testStocks() {
        runBlocking {
            val responseDto = readJson("response.json")
            Mockito.`when`(stocksRepository.stocks()).thenReturn(Resource.success(responseDto))

            val stocksResource = stocksUseCase.stocks()
            if (stocksResource.status == Resource.Status.SUCCESS) {
                Assert.assertEquals(toStocksItems(responseDto), stocksResource.data)
            }
        }
    }

    @Test
    fun testStocksEmpty() {
        runBlocking {
            val responseDto = readJson("response_empty.json")
            Mockito.`when`(stocksRepository.stocks()).thenReturn(Resource.error("empty",responseDto))

            val stocksResource = stocksUseCase.stocks()
            if (stocksResource.status == Resource.Status.ERROR) {
                Assert.assertEquals("empty", stocksResource.message)
            }
        }
    }
}