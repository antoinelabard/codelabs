package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import junit.framework.Assert.assertEquals
import org.junit.Test

class StatisticsUtilsKtTest {
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        val tasks = listOf(
                Task("title", "desc", isCompleted = false)
        )
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 100f)
    }

    @Test
    fun getActiveAndCompletedStats_empty_returnsZeroZero() {
        val tasks = listOf<Task>()
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 0f)
    }

    @Test
    fun getActiveAndCompletedStats_noActive_returnsZeroHundred() {
        val tasks = listOf(
                Task("title", "desc", isCompleted = true)
        )
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(result.completedTasksPercent, 100f)
        assertEquals(result.activeTasksPercent, 0f)
    }

    @Test
    fun getActiveAndCompletedStats_both_returnsFiftyFifty() {
        val tasks = listOf(
                Task("title", "desc", isCompleted = false),
                Task("title1", "desc1", isCompleted = true)
        )
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(result.completedTasksPercent, 50f)
        assertEquals(result.activeTasksPercent, 50f)
    }


}