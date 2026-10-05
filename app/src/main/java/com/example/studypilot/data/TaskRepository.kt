package com.example.studypilot.data

import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val taskDao: TaskDao
) {

    fun getAllTasks(): Flow<List<StudyTask>> {
        return taskDao.getAllTasks()
    }

    suspend fun updateTask(task: StudyTask) {
        taskDao.updateTask(task)
    }

    suspend fun insertTasks(tasks: List<StudyTask>) {
        taskDao.insertTasks(tasks)
    }

    suspend fun insertTask(task: StudyTask) {
        taskDao.insertTask(task)
    }
}