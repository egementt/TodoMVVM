package com.example.todomvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String,
    //1-3 (1: low, 3: high)
    val priorityLevel: Int = 1
){

}
