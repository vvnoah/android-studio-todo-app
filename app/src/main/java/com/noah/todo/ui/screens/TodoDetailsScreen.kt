package com.noah.todo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noah.todo.R
import com.noah.todo.models.TodoModel
import com.noah.todo.viewmodels.TodoDetailsViewModel

@Composable
fun TodoDetailsScreen(
    viewmodel:TodoDetailsViewModel,
    checkTodoClick:(TodoModel) -> Unit,
    editTodoClick:(TodoModel) -> Unit
) {
    val todo = viewmodel.todo
    if(todo == null) {
        return
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {editTodoClick(todo)}
            ) {
                Icon(Icons.Default.Edit, contentDescription = stringResource(R.string.todo_edit))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Checkbox(todo.completion, { checkTodoClick(todo) })
                if (todo.completion) {
                    Text(
                        todo.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textDecoration = TextDecoration.LineThrough
                    )
                } else {
                    Text(
                        todo.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
            }
            OutlinedTextField(
                todo.content, onValueChange = {},
                readOnly = true, label = { Text(stringResource(R.string.content)) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}