package com.noah.todo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import com.noah.todo.models.TodoModel

@Dao
interface TodoDao {
    @Insert
    suspend fun insertTodo(todo: TodoModel)

    @Insert
    suspend fun insertTodos(todos:List<TodoModel>)

    @Update
    suspend fun updateTodo(todo: TodoModel)

    @Delete
    suspend fun deleteTodo(todo: TodoModel)

    @Query("SELECT * FROM todos")
    suspend fun getTodos():List<TodoModel>

    @Query("SELECT * FROM todos WHERE id = :id")
    suspend fun getTodoById(id:Int): TodoModel
}