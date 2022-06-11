package com.test.app.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.app.common.readJson
import com.test.app.common.toStocksItems
import com.test.app.data.network.Resource
import com.test.app.domain.usecases.StocksUseCase
import com.test.app.ui.numbers.viewmodel.NumbersViewModel
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
class StocksViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var stocksUseCase: StocksUseCase

    private lateinit var numbersViewModel: NumbersViewModel

    @Before
    fun setup() {
        numbersViewModel = NumbersViewModel(stocksUseCase)
    }

    @Test
    fun testStocks() {
        runBlocking {
            val responseDto = readJson("response.json")
            Mockito.`when`(stocksUseCase.stocks()).thenReturn(Resource.success(toStocksItems(responseDto)))

            numbersViewModel.fetchNumbers.observeForever {
                if (it.status == Resource.Status.SUCCESS) {
                    Assert.assertEquals(responseDto, it.data)
                }
            }
        }
    }
}