package com.bayu.dailytask.task

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    var tasks = mutableStateListOf<Task>()
        private set

    fun onAddTask(task: Task) {
        tasks.add(task)
    }

}