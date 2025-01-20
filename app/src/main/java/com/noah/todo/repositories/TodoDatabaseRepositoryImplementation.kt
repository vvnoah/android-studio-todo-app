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
                title = "Todo Model",
                content = "Een todo model is om de structuur van een todo te bepalen.",
                completion = false
            ),
            TodoModel(
                id = 2,
                title = "Todo Dao",
                content = "Een todo data access object is om te bepalen wat voor functies beschikbaar zijn voor de data.",
                completion = false
            ),
            TodoModel(
                id = 3,
                title = "Todo Database",
                content = "De todo database is een Room Database met één entity, todo dao.",
                completion = false
            ),
            TodoModel(
                id = 4,
                title = "Repository Interface",
                content = "De repository interface bepaalt de structuur van de repositories",
                completion = false
            ),
            TodoModel(
                id = 5,
                title = "Database Repository",
                content = "De database repository haalt de dao van de database op en biedt functies aan om met de dao te werken.",
                completion = false
            ),
            TodoModel(
                id = 6,
                title = "Todo Viewmodels",
                content = "De view models zijn de verbinding tussen UI en repositories, en onthouden de state van een scherm",
                completion = false
            ),
            TodoModel(
                id = 7,
                title = "Nav Graph",
                content = "De nav graph zorgt voor navigatie tussen verschillende schermen, alsook het meegeven van data tijdens het navigeren.",
                completion = false
            ),
        ))
    }
}