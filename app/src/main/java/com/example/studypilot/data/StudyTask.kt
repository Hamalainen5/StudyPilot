package com.example.studypilot.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class StudyTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val priority: String,
    val completed: Boolean = false
)