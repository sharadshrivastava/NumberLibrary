package com.test.app.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.app.ui.numbers.viewmodel.NumbersViewModel
import com.test.app.ui.numbers.viewmodel.NumbersViewState
import com.test.numberslib.data.NumbersResult
import com.test.numberslib.data.cache.entity.Data
import com.test.numberslib.domain.usecases.NumbersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class NumbersViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    //Using Test Dispatcher, can be converted this in to rule in real apps.
    private val dispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var useCase: NumbersUseCase

    private lateinit var numbersViewModel: NumbersViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        numbersViewModel = NumbersViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testFetchNumbers() {
        runTest {
            val flow = flowOf(listOf(Data(0, 10), Data(1, 20), Data(2, 30)))
            Mockito.`when`(useCase.fetchNumbers()).thenReturn(NumbersResult.Success(flow))
            val expected = flow.first().first().value

            numbersViewModel.fetchNumbers()
            val viewState = numbersViewModel.viewState.first()
            if (viewState is NumbersViewState.Success){
                Assert.assertEquals(expected, viewState.numbers.first().value)
            }
        }
    }

    @Test
    fun testDoesNumberExists(){
        runTest {
            val actual = numbersViewModel.doesNumberExists(10)
            Assert.assertEquals(10, actual)
        }
    }
}