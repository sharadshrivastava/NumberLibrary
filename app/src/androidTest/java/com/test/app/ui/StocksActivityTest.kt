package com.test.app.ui

import androidx.navigation.findNavController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.test.app.R
import com.test.app.NumbersActivity
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class StocksActivityTest {

    @get:Rule
    val rule = ActivityScenarioRule(NumbersActivity::class.java)


    @After
    fun cleanup() {
        rule.scenario.close()
    }

    @Test
    fun testFragmentLoaded() {
        rule.scenario.onActivity {
            val fragment = it.findNavController(R.id.navHostFragment)
            Assert.assertNotNull(fragment)
        }
    }
}
