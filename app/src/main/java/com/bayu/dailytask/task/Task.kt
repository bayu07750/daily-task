package com.bayu.dailytask.task

data class Task(
    val title: String,
    val description: String,
    val isImportant: Boolean = false,
)