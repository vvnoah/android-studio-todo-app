package com.noah.todo.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "todos")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val title:String,
    val content:String,
    val completion:Boolean
)