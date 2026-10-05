package com.example.studypilot.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [StudyTask::class],
    version = 1
)
abstract class StudyPilotDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}