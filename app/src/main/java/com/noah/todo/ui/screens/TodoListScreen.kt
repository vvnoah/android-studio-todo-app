package com.noah.todo.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.noah.todo.viewmodels.TodoListViewModel

@Composable
fun TodoListScreen(
    viewmodel:TodoListViewModel,
    addClick:() -> Unit,
    checkClick:(TodoModel) -> Unit,
    detailsClick:(TodoModel) -> Unit,
    deleteClick:(TodoModel) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = addClick
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.todo_add))
            }
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(4.dp, 8.dp)
        ) {
            LazyColumn {
                items(viewmodel.todos) {todo ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable { detailsClick(todo) },
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                    ){
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(todo.completion, { checkClick(todo) })
                                Text(
                                    text = todo.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    textDecoration = if (todo.completion) TextDecoration.LineThrough else TextDecoration.None
                                )
                            }
                            Column {
                                Row {
                                    IconButton(onClick = { deleteClick(todo) }) {
                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = stringResource(R.string.todo_delete))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}