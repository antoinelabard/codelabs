package com.example.testcodelab

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.internal.inject.InstrumentationContext
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest : TestCase() {

    @Rule
    @JvmField
    val mActivityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun isActivityInView() {
        onView(withId(R.id.activity_main)).check(matches(isDisplayed()))
    }

    @Test
    fun can_enter_number_One() {
        onView(withId(R.id.operand_one_edit_text)).perform(typeText("1"))
    }

    @Test
    fun can_enter_number_Two() {
        onView(withId(R.id.operand_two_edit_text)).perform(typeText("1"))
    }

    @Test
    fun canGetCorrectResultFrom1Add1() {
        onView(withId(R.id.operand_one_edit_text)).perform(typeText("1"))
        onView(withId(R.id.operand_two_edit_text)).perform(typeText("1"))
        onView(withId(R.id.operation_add_btn)).perform(click())
        onView(withId(R.id.operation_result_text_view)).check(matches(withText("2.0")))
    }

}