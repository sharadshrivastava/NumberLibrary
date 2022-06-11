package com.test.numberslib.data.network

import retrofit2.http.GET

/**
 * Retrofit library's network interface.
 */
interface NumbersApi {

    @GET(STOCKS_PATH)
    suspend fun numbers(): List<Int>?

    companion object {
        const val BASE_URL = "https://roktcdn1.akamaized.net/"
        private const val STOCKS_PATH = "store/test/android/prestored.json"
    }
}