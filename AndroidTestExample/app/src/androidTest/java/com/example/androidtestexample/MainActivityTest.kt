package com.example.androidtestexample

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Test
    fun testEvent() {
        @get:Rule var activityScenarioRule = activityScenarioRule<MainActivity>()
        val scenario = launchActivity<MainActivity>()
    }
}