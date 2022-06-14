package com.test.numberslib.data

import com.test.numberslib.BaseTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NumbersRepositoryTest : BaseTest() {

    @Test
    fun testSuccessResponse() {
        setResponse("response.json")
        runBlocking {
            val numbers = repository.fetchNumbers()
            Assert.assertTrue(
                numbers is NumbersResult.Success
            )
        }
    }

    @Test
    fun testFailResponse() {
        setErrorResponse()
        runBlocking {
            Assert.assertTrue(
                repository.fetchNumbers() is NumbersResult.Error
            )
        }
    }

    @Test
    fun testMalformed() {
        setResponse("response_malformed.json")
        runBlocking {
            Assert.assertTrue(
                repository.fetchNumbers() is NumbersResult.Error
            )
        }
    }

    //In this way we can test other functionality as well, using mock webserver and dummy responses.

     /*Other functions of repository needs dao object and they can be tested only in 'android test'
     as dao needs application context. Those functions will be covered in Dao testing*/
}