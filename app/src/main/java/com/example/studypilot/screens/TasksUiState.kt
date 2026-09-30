package com.example.studypilot.screens

data class TasksUiState(
    val tasks: List<StudyTask> = emptyList(),
    val completedTaskIndexes: Set<Int> = emptySet()
)