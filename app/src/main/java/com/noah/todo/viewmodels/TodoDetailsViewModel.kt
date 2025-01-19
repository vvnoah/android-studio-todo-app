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

class TodoDetailsViewModel(app:Application):ViewModel() {
    private val repository:TodoRepositoryInterface = TodoDatabaseRepositoryImplementation(app)
    var todo:TodoModel? by mutableStateOf(null)

    fun loadTodoById(id:Int) {
        viewModelScope.launch {
            todo = repository.getTodoById(id)
        }
    }

    fun updateTodoCompletion(todo:TodoModel) {
        viewModelScope.launch {
            repository.updateTodo(todo.copy(completion = !todo.completion))
            loadTodoById(todo.id)
        }
    }
}