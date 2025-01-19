package com.noah.todo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.noah.todo.R
import com.noah.todo.viewmodels.TodoAddViewModel

@Composable
fun TodoAddScreen(
    viewmodel:TodoAddViewModel,
    doneClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = doneClick
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
                modifier = Modifier.fillMaxWidth(),
                value = viewmodel.todo.title,
                onValueChange = { viewmodel.todo = viewmodel.todo.copy(title = it) },
                label = { Text(stringResource(R.string.title)) }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().height(300.dp),
                value = viewmodel.todo.content,
                onValueChange = { viewmodel.todo = viewmodel.todo.copy(content = it) },
                label = { Text(stringResource(R.string.content)) }
            )
        }
    }
}