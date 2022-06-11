package com.test.numberslib.data

sealed class NumbersResult {

    data class Success(val numbers: List<Int>?) : NumbersResult()

    data class Error(val msg: String?) : NumbersResult()
}
