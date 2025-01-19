package com.noah.todo.ui.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
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
import com.noah.todo.models.TodoModel
import com.noah.todo.viewmodels.TodoAddViewModel
import com.noah.todo.viewmodels.TodoEditViewModel

@Composable
fun TodoEditScreen(
    viewmodel: TodoEditViewModel,
    doneClick: (TodoModel) -> Unit
) {
    var todo = viewmodel.todo
    if(todo == null) return
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {doneClick(viewmodel.todo!!)}
            ) {
                Icon(Icons.Default.Done, contentDescription = stringResource(R.string.done))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 8.dp)
        ) {
            OutlinedTextField(
                value = viewmodel.todo!!.title, label = { Text(stringResource(R.string.title)) },
                onValueChange = {viewmodel.todo = todo!!.copy(title = it)},
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().height(300.dp),
                value = viewmodel.todo!!.content,
                onValueChange = { viewmodel.todo = todo!!.copy(content = it) },
                label = { Text(stringResource(R.string.content)) }
            )
        }
    }
}