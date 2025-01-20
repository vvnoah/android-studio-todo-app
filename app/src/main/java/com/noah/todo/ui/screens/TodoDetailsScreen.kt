package com.noah.todo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noah.todo.R
import com.noah.todo.viewmodels.TodoViewModel

@Composable
fun TodoDetailsScreen(
    viewmodel:TodoViewModel,
    editClick: () -> Unit,
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
                onClick = { editClick() }
            ) {
                Icon(Icons.Default.Edit, contentDescription = stringResource(R.string.todo_edit))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(4.dp, 8.dp)
        ) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Checkbox(
                        todoState.completion,
                        { isChecked -> viewmodel.updateCompletion(isChecked) }
                    )
                    Text(
                        text = todoState.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textDecoration = if (todoState.completion) TextDecoration.LineThrough else TextDecoration.None
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(300.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                Text(
                    text = todoState.content,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}