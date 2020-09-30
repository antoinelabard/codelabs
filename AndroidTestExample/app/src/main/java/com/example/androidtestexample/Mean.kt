package com.example.androidtestexample

class Mean(val array: Array<Int>): Int {
    fun getMean() {
        return array.sum()
    }
}