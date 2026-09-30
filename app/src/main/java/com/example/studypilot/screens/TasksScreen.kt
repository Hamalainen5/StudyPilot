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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TasksScreen(
    onBack: () -> Unit,
    viewModel: TasksViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    val completedTasks = uiState.completedTaskIndexes.size

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "My Tasks"
        )

        Text(
            text = "$completedTasks of ${uiState.tasks.size} completed",
            style = MaterialTheme.typography.bodyMedium
        )

        Button(
            onClick = onBack
        ) {
            Text("Back to Home")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.tasks.withIndex().toList()) { (index, task) ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = index in uiState.completedTaskIndexes,
                            onCheckedChange = { checked ->
                                viewModel.setTaskCompleted(index, checked)
                            }
                        )

                        Column {
                            Text(
                                text = task.title,
                                textDecoration = if (index in uiState.completedTaskIndexes) {
                                    TextDecoration.LineThrough
                                } else {
                                    TextDecoration.None
                                }
                            )

                            Text(
                                text = "Priority: ${task.priority}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}