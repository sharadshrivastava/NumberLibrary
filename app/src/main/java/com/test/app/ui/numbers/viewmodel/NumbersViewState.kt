package com.test.app.ui.numbers.viewmodel

sealed class NumbersViewState {

    object Loading : NumbersViewState()

    data class Success(val numbers: List<Int>?) : NumbersViewState()

    data class Error(val msg: String?) : NumbersViewState()
}
