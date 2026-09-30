package com.example.studypilot.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onStartStudying: () -> Unit
) {


    val tasks = listOf(
        StudyTask("Review Kotlin coroutines", "High"),
        StudyTask("Complete Android exercise", "High"),
        StudyTask("Study Room database", "Medium"),
        StudyTask("Practice Compose state", "Low"),
        StudyTask("Review today's lecture", "Medium")
    )

    var started by remember { mutableStateOf(false) }
    var completedTaskIndexes by remember { mutableStateOf(setOf<Int>()) }
    val completedTasks = completedTaskIndexes.size
    val totalTasks = tasks.size


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("StudyPilot")
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth()
            ){
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    Text(
                        text = "Today's progress"
                    )

                    Text(
                        text = "$completedTasks of $totalTasks tasks completed"
                    )

                    LinearProgressIndicator(
                        progress = { completedTasks.toFloat() / totalTasks },
                        modifier = Modifier.fillMaxWidth()
                    )

                }
            }


            Text(
                text = "Good afternoon! 👋"
            )

            Text(
                text = "Ready to study?"
            )

            if (started) {
                Text(
                    text = "🎯 Let's start studying!"
                )
            }

            Button(
                onClick = {
                    started = true
                    onStartStudying()
                },
                enabled = !started
            ) {
                Text(
                    text = if (started) {
                        "Studying..."
                    } else {
                        "Start studying"
                    }
                )
            }
        }
    }
}