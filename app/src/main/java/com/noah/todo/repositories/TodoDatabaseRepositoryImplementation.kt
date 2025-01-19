package com.noah.todo.repositories

import android.app.Application
import com.noah.todo.database.TodoDatabase
import com.noah.todo.models.TodoModel

class TodoDatabaseRepositoryImplementation(app:Application): TodoRepositoryInterface {
    private val todoDao = TodoDatabase.getInstance(app).todoDao()

    override suspend fun insertTodo(todo: TodoModel) {
        todoDao.insertTodo(todo)
    }

    override suspend fun insertTodos(todos:List<TodoModel>) {
        todoDao.insertTodos(todos)
    }

    override suspend fun updateTodo(todo: TodoModel) {
        todoDao.updateTodo(todo)
    }

    override suspend fun deleteTodo(todo: TodoModel) {
        todoDao.deleteTodo(todo)
    }

    override suspend fun getTodos():List<TodoModel> {
        var todos:List<TodoModel> = todoDao.getTodos()
        if(todos.isEmpty()) {
            seed()
            todos = todoDao.getTodos()
        }
        return todos
    }

    override suspend fun getTodoById(id:Int): TodoModel {
        return todoDao.getTodoById(id)
    }

    private suspend fun seed() {
        todoDao.insertTodos(listOf(
            TodoModel(
                id = 1,
                title = "Maak de App.",
                content = "Maak de todo app in Android Studio met Jetpack Compose en Room.",
                completion = true
            ),
            TodoModel(
                id = 2,
                title = "Geef een demo.",
                content = "Geef een demo van de app waar je laat zien wat mogelijk is.",
                completion = false
            ),
            TodoModel(
                id = 3,
                title = "Geef meer uitleg.",
                content = "Leg uit wat goed en slecht verliep bij het maken van de app.",
                completion = false
            )
        ))
    }
}