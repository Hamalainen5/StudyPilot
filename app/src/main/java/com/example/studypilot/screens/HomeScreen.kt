package com.example.studypilot.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studypilot.viewmodel.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onStartStudying: () -> Unit,
    viewModel: TasksViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val completedTasks = uiState.tasks.count { it.completed }
    val totalTasks = uiState.tasks.size

    var started by remember { mutableStateOf(false) }


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
                onClick = onStartStudying,
                modifier = Modifier.fillMaxWidth()
            ){
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    Text(
                        text = "Today's progress"
                    )


                    Text("$completedTasks of $totalTasks tasks completed")

                    LinearProgressIndicator(
                        progress = if (totalTasks > 0) {
                            completedTasks.toFloat() / totalTasks
                        } else {
                            0f
                        },
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