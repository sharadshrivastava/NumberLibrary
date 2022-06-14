package com.test.app.ui.numbers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.numberslib.data.NumbersResult
import com.test.numberslib.domain.usecases.NumbersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class NumbersViewModel @Inject constructor(
    private val useCase: NumbersUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<NumbersViewState>(NumbersViewState.Loading)
    val viewState: StateFlow<NumbersViewState> = _viewState
    val averageNumberFlow = MutableStateFlow("")

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _viewState.value = NumbersViewState.Error(throwable.message)
    }

    init {
        fetchNumbers()
    }

    fun fetchNumbers() {
        viewModelScope.launch(exceptionHandler) {
            handleResult(useCase.fetchNumbers())
        }
    }

    fun addNumber(value: Int) {
        viewModelScope.launch(exceptionHandler) {
            useCase.addNumber(value)
        }
    }

    suspend fun deleteNumber(value: Int) = useCase.deleteNumber(value)

    //Checking on ui layer instead of library using DB, because its an App's feature.
    suspend fun doesNumberExists(value: Int): Boolean {
        val currentState = _viewState.first()
        if (currentState is NumbersViewState.Success) {
            val result = currentState.numbers.filter {
                it.value == value
            }
            return result.isNotEmpty()
        }
        return false
    }

    fun isNumberValid(value: String) = value.toIntOrNull() != null

    private suspend fun handleResult(numbersResult: NumbersResult) {
        when (numbersResult) {
            is NumbersResult.Success -> {
                numbersResult.numbers?.collect {
                    _viewState.value = NumbersViewState.Success(it)
                    updateAverage()
                }
            }
            is NumbersResult.Error -> {
                _viewState.value = NumbersViewState.Error(numbersResult.msg)
            }
        }
    }

    private suspend fun updateAverage() {
        val bd = BigDecimal(useCase.average() ?: 0.0).setScale(2, RoundingMode.HALF_UP)
        averageNumberFlow.value = bd.toDouble().toString()
    }
}