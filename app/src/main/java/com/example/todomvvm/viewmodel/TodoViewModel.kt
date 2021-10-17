package com.example.todomvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todomvvm.model.TodoModel
import com.example.todomvvm.repository.TodoRepository
import com.example.todomvvm.room.TodoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {

    val todoList: LiveData<List<TodoModel>>
    val repository: TodoRepository

    init {
        val todoDao = TodoDatabase.getDatabase(application.applicationContext).todoDao()
        repository = TodoRepository(todoDao)
        todoList = repository.todoList
    }

    fun addTodo(todoModel: TodoModel)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todoModel)
        }
    }

    fun deleteTodo(todoModel: TodoModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todoModel)
        }
    }

}