package com.noah.todo.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noah.todo.models.TodoModel
import com.noah.todo.repositories.TodoDatabaseRepositoryImplementation
import com.noah.todo.repositories.TodoRepositoryInterface
import kotlinx.coroutines.launch

class TodoAddViewModel(app: Application): ViewModel() {
    private val repository:TodoRepositoryInterface = TodoDatabaseRepositoryImplementation(app)

    var todo:TodoModel by mutableStateOf(TodoModel(title = "", content = "", completion = false))

    fun saveTodo() {
        viewModelScope.launch {
            repository.insertTodo(todo)
        }
    }
}