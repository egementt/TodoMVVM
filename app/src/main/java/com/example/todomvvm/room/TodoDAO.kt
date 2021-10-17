package com.example.todomvvm.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todomvvm.model.TodoModel

@Dao
interface TodoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodo(todoModel: TodoModel)

    @Query("SELECT* FROM todo_table ORDER BY priorityLevel DESC")
    fun getTodoList(): LiveData<List<TodoModel>>

    @Delete
    fun deleteTodo(todoModel: TodoModel)

}