package com.test.app.common

import com.google.gson.Gson
import com.test.app.BaseTest
import com.test.app.domain.mappers.toStockItem
import com.test.app.domain.models.dto.ResponseDto
import com.test.app.domain.models.ui.StocksItem
import java.io.InputStreamReader

fun readJson(fileName: String): ResponseDto {
    val input = BaseTest::class.java.classLoader?.getResourceAsStream(fileName)
    val reader = InputStreamReader(input)
    return Gson().fromJson(reader, ResponseDto::class.java)
}

fun toStocksItems(responseDto: ResponseDto): List<StocksItem> {
    val stocks = mutableListOf<StocksItem>()
    responseDto.stocks?.forEach {
        stocks.add(toStockItem(it))
    }
    return stocks
}