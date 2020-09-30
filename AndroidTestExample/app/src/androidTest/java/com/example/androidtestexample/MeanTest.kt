package com.example.androidtestexample

import android.location.Location
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.pow

class MeanTest {
    @Test
    fun correctMean() {
        val a = arrayOf(1, 2, 3)
        assertEquals(2, Mean(a).getMean())
    }
}