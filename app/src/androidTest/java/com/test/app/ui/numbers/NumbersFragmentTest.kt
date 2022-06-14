package com.test.app.ui.numbers


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.test.app.NumbersActivity
import com.test.app.R
import com.test.app.ui.common.hasItemCount
import com.test.app.ui.common.viewAtPosition
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This class is for Android UI testing using Espresso.
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class NumbersFragmentTest {

    @get:Rule
    val rule = ActivityScenarioRule(NumbersActivity::class.java)

    @Before
    fun setup() {
        Thread.sleep(1000) //Time to load UI.
    }

    @After
    fun cleanup() {
        rule.scenario.close()
    }

    @Test
    fun testProgressBar() {
        onView(withId(R.id.loading)).check(matches(withEffectiveVisibility(Visibility.GONE)));
    }

    @Test
    fun testListVisible() {
        onView(withId(R.id.numbersList)).check(matches(isDisplayed()))
    }

    @Test
    fun testListItemsCount() {
        onView(withId(R.id.numbersList)).check(matches(hasItemCount(4)))
    }

    @Test
    fun testListFirstItem() {
        onView(withId(R.id.numbersList))
            .check(matches(viewAtPosition(0, hasDescendant(withText("10")))));
    }

    @Test
    fun testListLastItem() {  //first scroll till last and then check item.
        onView(withId(R.id.numbersList))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(3))
            .check(matches(viewAtPosition(3, hasDescendant(withText("400")))));
    }

    @Test
    fun testListClick() {
        onView(withId(R.id.numbersList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        //If on click something is shown/launch then we can assert that here.
    }
}
