package com.example.todomvvm.repository

import androidx.lifecycle.LiveData
import com.example.todomvvm.model.TodoModel
import com.example.todomvvm.room.TodoDAO

class TodoRepository(private val todoDAO: TodoDAO) {

    val todoList: LiveData<List<TodoModel>> = todoDAO.getTodoList()

    suspend fun addTodo(todoModel: TodoModel){
        todoDAO.addTodo(todoModel)
    }

    fun deleteTodo(todoModel: TodoModel){
        todoDAO.deleteTodo(todoModel)
    }


}