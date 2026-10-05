package com.example.studypilot.viewmodel

import com.example.studypilot.data.StudyTask

data class TasksUiState(
    val tasks: List<StudyTask> = emptyList()
)