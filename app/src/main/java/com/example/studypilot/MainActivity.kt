package com.example.studypilot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.studypilot.screens.AppNavigation
import com.example.studypilot.ui.theme.StudyPilotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyPilotTheme {
                AppNavigation()

            }
        }
    }
}


