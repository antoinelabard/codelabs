package com.example.androidtestexample

import junit.framework.TestCase.assertEquals
import org.junit.Test

class StatsTest {
    val a = Stats(arrayOf(1, 2, 3, 4, 5, 6))
    @Test
    fun correctMean() = assertEquals(21/6.0, a.getMean())

    @Test
    fun correctSum() = assertEquals(21, a.getSum())
}