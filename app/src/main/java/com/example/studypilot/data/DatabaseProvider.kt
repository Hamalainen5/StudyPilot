package com.example.studypilot.data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: StudyPilotDatabase? = null

    fun getDatabase(context: Context): StudyPilotDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                StudyPilotDatabase::class.java,
                "studypilot_database"
            ).build()

            INSTANCE = instance
            instance
        }
    }
}