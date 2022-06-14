package com.test.numberslib.common

import com.google.gson.Gson
import com.test.numberslib.BaseTest
import java.io.InputStreamReader

fun readJson(fileName: String): List<Int> {
    val input = BaseTest::class.java.classLoader?.getResourceAsStream(fileName)
    val reader = InputStreamReader(input)
    return Gson().fromJson(reader, Array<Int>::class.java).toList()
}