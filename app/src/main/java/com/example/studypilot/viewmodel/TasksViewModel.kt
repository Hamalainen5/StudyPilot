package com.example.studypilot.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.studypilot.data.DatabaseProvider
import com.example.studypilot.data.StudyTask
import com.example.studypilot.data.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first

class TasksViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepository(
        DatabaseProvider.getDatabase(application).taskDao()
    )

    val uiState: StateFlow<TasksUiState> =
        repository.getAllTasks()
            .map { tasks ->
                TasksUiState(tasks = tasks)
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                TasksUiState()
            )

    init {
        viewModelScope.launch {
            val tasks = repository.getAllTasks().first()

            if (tasks.isEmpty()) {
                repository.insertTasks(
                    listOf(
                        StudyTask(
                            title = "Review Kotlin coroutines",
                            priority = "High"
                        ),
                        StudyTask(
                            title = "Complete Android exercise",
                            priority = "High"
                        ),
                        StudyTask(
                            title = "Study Room database",
                            priority = "Medium"
                        ),
                        StudyTask(
                            title = "Practice Compose state",
                            priority = "Low"
                        ),
                        StudyTask(
                            title = "Review today's lecture",
                            priority = "Medium"
                        )
                    )
                )
            }
        }
    }

    fun setTaskCompleted(task: StudyTask, completed: Boolean) {
        viewModelScope.launch {
            repository.updateTask(
                task.copy(completed = completed)
            )
        }
    }

    fun addTask(title: String, priority: String) {
        viewModelScope.launch {
            repository.insertTask(
                StudyTask(
                    title = title,
                    priority = priority
                )
            )
        }
    }
}