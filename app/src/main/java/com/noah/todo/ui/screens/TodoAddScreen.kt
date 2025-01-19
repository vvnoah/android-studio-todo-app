package com.noah.todo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.noah.todo.R
import com.noah.todo.viewmodels.TodoViewModel

@Composable
fun TodoAddScreen(
    viewmodel:TodoViewModel,
    insertClick: () -> Unit
) {
    val todoState = viewmodel.todoState

    if(todoState.loading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    }

    if(todoState.error != null) {
        Text(text = "Error: ${todoState.error}", color = Color.Red)
        return
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewmodel.insertTodo()
                    insertClick()
                }
            ) {
                Icon(Icons.Default.Done, contentDescription = stringResource(R.string.done))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(4.dp, 8.dp)
        ) {
            OutlinedTextField(
                value = todoState.title,
                onValueChange = viewmodel::updateTitle,
                label = { Text(stringResource(R.string.title)) },
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = todoState.content,
                onValueChange = viewmodel::updateContent,
                label = { Text(stringResource(R.string.content)) },
                modifier = Modifier.fillMaxWidth().height(300.dp),
            )
        }
    }
}