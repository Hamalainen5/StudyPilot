package com.example.studypilot.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TasksViewModel : ViewModel() {

    val tasks = listOf(
        StudyTask("Review Kotlin coroutines", "High"),
        StudyTask("Complete Android exercise", "High"),
        StudyTask("Study Room database", "Medium"),
        StudyTask("Practice Compose state", "Low"),
        StudyTask("Review today's lecture", "Medium")
    )

    private val _uiState = MutableStateFlow(
        TasksUiState(
            tasks = tasks
        )
    )

    val uiState: StateFlow<TasksUiState> =
        _uiState.asStateFlow()

    fun setTaskCompleted(index: Int, completed: Boolean) {

        val currentCompleted = _uiState.value.completedTaskIndexes

        val updatedCompleted =
            if (completed) {
                currentCompleted + index
            } else {
                currentCompleted - index
            }

        _uiState.value = _uiState.value.copy(
            completedTaskIndexes = updatedCompleted
        )
    }
}