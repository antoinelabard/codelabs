package fr.labard.testcodelab

/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.IllegalArgumentException
import kotlin.math.pow


/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4::class)
//@SmallTest
class CalculatorTest {
    private lateinit var mCalculator: Calculator
    // gives the magnitudes of the large numbers tested
    private val magnitude = 15

    /**
     * Set up the environment for testing
     */
    @Before
    fun setUp() {
        mCalculator = Calculator()
    }

    /**
     * Test for simple addition
     */
    @Test
    fun addTwoNumbers() {
        val resultAdd = mCalculator.add(1.0, 1.0)
        assertThat(resultAdd, `is`(equalTo(2.0)))
    }

    @Test
    fun addTwoNumbersNegative() {
        val resultAdd = mCalculator.add(1.0, -1.0)
        assertThat(resultAdd, `is`(equalTo(0.0)))
    }

    @Test
    fun addTwoNumbersfloats() {
        val resultAdd = mCalculator.add(0.9, 1.1)
        assertThat(resultAdd, `is`(equalTo(2.0)))
    }

    @Test
    fun addTwoNumbersLarge() {
        val resultAdd = mCalculator.add(10.0.pow(magnitude), 10.0.pow(magnitude))
        assertThat(resultAdd, `is`(equalTo(2*10.0.pow(15))))
    }

    @Test
    fun addTwoNumbersZero() {
        val resultAdd = mCalculator.add(1.0, 0.0)
        assertThat(resultAdd, `is`(equalTo(1.0)))
    }

    @Test
    fun addTwoNumbersInfinity() {
        val resultAdd = mCalculator.add(1.0, Double.POSITIVE_INFINITY)
        assertThat(resultAdd, `is`(equalTo(Double.POSITIVE_INFINITY)))
    }

    @Test
    fun subTwoNumbers() {
        val resultSub = mCalculator.sub(1.0, 1.0)
        assertThat(resultSub, `is`(equalTo(0.0)))
    }

    @Test
    fun subTwoNumbersNegative() {
        val resultSub = mCalculator.sub(1.0, -1.0)
        assertThat(resultSub, `is`(equalTo(2.0)))
    }

    @Test
    fun subTwoNumbersInfinity() {
        val resultSub = mCalculator.sub(1.0, Double.POSITIVE_INFINITY)
        assertThat(resultSub, `is`(equalTo(Double.NEGATIVE_INFINITY)))
    }

    @Test
    fun subTwoNumbersZero() {
        val resultSub = mCalculator.sub(1.0, 0.0)
        assertThat(resultSub, `is`(equalTo(1.0)))
    }

    @Test
    fun subTwoNumbersLarge() {
        val resultSub = mCalculator.sub(10.0.pow(magnitude), 10.0.pow(magnitude))
        assertThat(resultSub, `is`(equalTo(0.0)))
    }

    @Test
    fun divTwoNumbers() {
        val resultDiv = mCalculator.div(1.0, 2.0)
        assertThat(resultDiv, `is`(equalTo(0.5)))
    }
    @Test(expected = IllegalArgumentException::class)
    fun divByZero() {
        val resultDiv = mCalculator.div(1.0, 0.0)
    }
}