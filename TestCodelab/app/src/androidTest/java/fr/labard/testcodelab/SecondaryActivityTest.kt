package fr.labard.testcodelab

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SecondaryActivityTest : TestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(SecondaryActivity::class.java)

    @Test
    fun button_is_displayed() {
        onView(withId(R.id.secondary_button)).check(matches(isDisplayed()))
    }
}