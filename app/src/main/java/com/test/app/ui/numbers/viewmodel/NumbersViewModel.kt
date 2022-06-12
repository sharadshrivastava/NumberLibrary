package com.test.app.ui.numbers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.numberslib.data.NumbersResult
import com.test.numberslib.domain.usecases.NumbersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumbersViewModel @Inject constructor(
    private val useCase: NumbersUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<NumbersViewState>(NumbersViewState.Loading)
    val viewState: StateFlow<NumbersViewState> = _viewState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _viewState.value = NumbersViewState.Error(throwable.message)
    }

    init {
        _viewState.value = NumbersViewState.Loading
        fetchNumbers()
    }

    fun fetchNumbers() {
        viewModelScope.launch(exceptionHandler) {
            when (val numbersResult = useCase.fetchNumbers()) {
                is NumbersResult.Success -> {
                    _viewState.value = NumbersViewState.Success(numbersResult.numbers)
                }
                is NumbersResult.Error -> {
                    _viewState.value = NumbersViewState.Error(numbersResult.msg)
                }
            }
        }
    }
}