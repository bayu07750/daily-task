package com.bayu.dailytask.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TaskScreen(
    tasks: List<Task>,
    onAddTask: (Task) -> Unit,
) {
    val (isActionAdd, setIsActionAdd) = rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TaskTopBar(isActionAdd = isActionAdd, onIsActionAddChange = setIsActionAdd)
        },
        floatingActionButton = {
            if (!isActionAdd) {
                AddTaskFab {
                    setIsActionAdd(true)
                }
            } else {
                Box { }
            }
        },
    ) {
        TaskScreenContent(
            tasks = tasks,
            onAddTask = onAddTask,
            isActionAdd = isActionAdd,
            onIsActionAddChange = setIsActionAdd,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun TaskTopBar(
    isActionAdd: Boolean,
    onIsActionAddChange: (Boolean) -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = if (isActionAdd) "Add" else "Daily Tasks",
                style = MaterialTheme.typography.h6
            )
        },
        navigationIcon = {
            if (isActionAdd) {
                TaskIconButton(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                    onClick = { onIsActionAddChange(false) },
                )
            } else {
                TaskIconButton(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = "Menu",
                    onClick = { /* TODO */ }
                )
            }
        },
    )
}

@Composable
fun TaskScreenContent(
    tasks: List<Task>,
    onAddTask: (Task) -> Unit,
    isActionAdd: Boolean,
    onIsActionAddChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (taskTitle, setTaskTitle) = rememberSaveable { mutableStateOf("") }
    val (taskDesc, setTaskDesc) = rememberSaveable { mutableStateOf("") }
    val (taskIsImportant, setTaskIsImportant) = rememberSaveable { mutableStateOf(false) }

    val newTaskAction = {
        val task = Task(taskTitle, taskDesc, taskIsImportant)
        onAddTask(task)
        setTaskTitle("")
        setTaskDesc("")
        setTaskIsImportant(false)
        onIsActionAddChange(false)
    }

    Surface(modifier = modifier) {
        if (isActionAdd) {
            TaskForms(
                title = taskTitle,
                onTitleChange = setTaskTitle,
                desc = taskDesc,
                onDescChange = setTaskDesc,
                isImportant = taskIsImportant,
                onIsImportantChange = setTaskIsImportant,
            ) {
                Button(
                    onClick = newTaskAction,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(text = "Add Task")
                }
            }
        } else {
            if (tasks.isNotEmpty()) {
                Tasks(items = tasks)
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "No Task added yet!",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
        }
    }
}
