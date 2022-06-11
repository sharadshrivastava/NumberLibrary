package com.test.app.data

import com.test.app.BaseTest
import com.test.app.data.network.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StocksRepositoryTest : BaseTest() {

    @Test
    fun testSuccessResponse() {
        setResponse("response.json")
        runBlocking {
            Assert.assertTrue(
                repository.stocks().status == Resource.Status.SUCCESS
            )
        }
    }

    @Test
    fun testFailResponse() {
        setErrorResponse()
        runBlocking {
            Assert.assertTrue(
                repository.stocks().status == Resource.Status.ERROR
            )
        }
    }

    @Test
    fun testStocks() {
        setResponse("response.json")
        runBlocking {
            val expectedItems = 17 //in local json file, we have 17 items.
            Assert.assertTrue(
                repository.stocks().data?.stocks?.size == expectedItems
            )
        }
    }

    //Error scenarios below
    @Test
    fun testEmpty() {
        setResponse("response_empty.json")
        runBlocking {
            val expectedItems = 0
            Assert.assertTrue(
                repository.stocks().data?.stocks?.size == expectedItems
            )
        }
    }

    @Test
    fun testMalformed() {
        setResponse("response_malformed.json")
        runBlocking {
            val stocksResource = repository.stocks()
            Assert.assertTrue(
                stocksResource.status == Resource.Status.ERROR
            )
        }
    }

    //In this way we can test other functionality as well, using mock webserver and dummy responses.
}