package com.example.android.architecture.blueprints.todoapp.tasks

import android.app.usage.UsageEvents
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//import android.app.usage.UsageEvents
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.Observer
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import junit.framework.TestCase
//import org.hamcrest.MatcherAssert.assertThat
//import org.hamcrest.Matchers.not
//import org.hamcrest.Matchers.nullValue
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TasksViewModelTest : TestCase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addNewTask_setsNewTaskEvent() {
        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
        tasksViewModel.addNewTask()

        // Create observer - no need for it to do anything!
        val observer = Observer<Event<Unit>> {}
        try {

            // Observe the LiveData forever
            tasksViewModel.newTaskEvent.observeForever(observer)

            // When adding a new task
            tasksViewModel.addNewTask()

            // Then the new task event is triggered
            val value = tasksViewModel.newTaskEvent.value
            assertThat(value?.getContentIfNotHandled(), (not(nullValue())))

        } finally {
            // Whatever happens, don't forget to remove the observer!
            tasksViewModel.newTaskEvent.removeObserver(observer)
        }
    }
}