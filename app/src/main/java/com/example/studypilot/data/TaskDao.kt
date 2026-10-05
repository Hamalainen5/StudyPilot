package com.example.studypilot.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Insert
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<StudyTask>>

    @Update
    suspend fun updateTask(task: StudyTask)

    @Insert
    suspend fun insertTasks(tasks: List<StudyTask>)

    @Insert
    suspend fun insertTask(task: StudyTask)
}