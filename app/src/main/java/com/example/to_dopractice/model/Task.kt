package com.example.to_dopractice.model

data class Task(
    val mainTask: MainTask? = null,
    val subTask: List<SubTask>? = null
)

data class TasksExample(
    val tasks: List<Task>? = null
)