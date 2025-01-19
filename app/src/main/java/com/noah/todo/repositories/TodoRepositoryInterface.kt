package com.noah.todo.repositories

import com.noah.todo.models.TodoModel

interface TodoRepositoryInterface {
    suspend fun insertTodo(todo: TodoModel)
    suspend fun insertTodos(todos:List<TodoModel>)
    suspend fun updateTodo(todo: TodoModel)
    suspend fun deleteTodo(todo: TodoModel)
    suspend fun getTodos():List<TodoModel>
    suspend fun getTodoById(id:Int): TodoModel
}
