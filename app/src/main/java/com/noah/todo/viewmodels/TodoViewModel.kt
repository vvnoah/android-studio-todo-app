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

data class TodoState(
    val id:Int = 0,
    val title:String = "",
    val content:String = "",
    val completion:Boolean = false,
    val loading:Boolean = true,
    val error:String? = null,
)

class TodoViewModel(app: Application): ViewModel() {
    private val repository:TodoRepositoryInterface = TodoDatabaseRepositoryImplementation(app)

    var todoState by mutableStateOf(TodoState())
        private set

    fun getTodoById(id:Int) {
        viewModelScope.launch {
            todoState = try {
                val todo = repository.getTodoById(id)
                TodoState(
                    id = todo.id,
                    title = todo.title,
                    content = todo.content,
                    completion = todo.completion,
                    loading = false,
                )
            } catch (e:Exception) {
                todoState.copy(loading = false, error = e.message)
            }
        }
    }

    fun updateTitle(title:String) {
        todoState = todoState.copy(title = title)
    }

    fun updateContent(content:String) {
        todoState = todoState.copy(content = content)
    }

    fun updateCompletion(completion: Boolean) {
        todoState = todoState.copy(completion = completion)
        updateTodo()
    }

    fun insertTodo() {
        viewModelScope.launch {
            try {
                repository.insertTodo(
                    TodoModel(
                        title = todoState.title,
                        content = todoState.content,
                        completion = false
                    )
                )
            } catch (e:Exception) {
                todoState = todoState.copy(error = e.message)
            }
        }
    }

    fun updateTodo() {
        viewModelScope.launch {
            try {
                repository.updateTodo(
                    TodoModel(
                        id = todoState.id,
                        title = todoState.title,
                        content = todoState.content,
                        completion = todoState.completion
                    )
                )
            } catch (e:Exception) {
                todoState = todoState.copy(error = e.message)
            }
        }
    }
}