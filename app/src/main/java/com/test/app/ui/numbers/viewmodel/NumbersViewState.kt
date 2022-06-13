package com.test.app.ui.numbers.viewmodel

import com.test.numberslib.data.cache.entity.Data

sealed class NumbersViewState {

    object Loading : NumbersViewState()

    data class Success(val numbers: List<Data>) : NumbersViewState()

    data class Error(val msg: String?) : NumbersViewState()
}
