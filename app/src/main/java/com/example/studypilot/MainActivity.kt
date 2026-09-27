package com.example.studypilot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studypilot.ui.theme.StudyPilotTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.composed

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyPilotTheme {
                HomeScreen()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    var started by remember { mutableStateOf(false) }
    var completedTasks by remember { mutableStateOf(3) }
    val totalTasks = 5
    val tasks = listOf(
        "Review Kotlin coroutines",
        "Complete Android exercise",
        "Study Room database",
        "Practice Compose state",
        "Review today's lecture"
    )

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

                    Button(
                        onClick = {
                            completedTasks++
                        },
                        enabled = completedTasks < totalTasks
                    ) {
                        Text(
                            text = if (completedTasks < totalTasks) {
                                "Complete task"
                            } else {
                                "All tasks completed"
                            }
                        )
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(tasks) { task ->
                    Text(
                        text = task,
                        modifier = Modifier.padding(8.dp)
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
