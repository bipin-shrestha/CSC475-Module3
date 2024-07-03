package com.example.todoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ToDoRepository(application)
    private val _tasks = MutableLiveData<List<ToDo>>()
    val tasks: LiveData<List<ToDo>> = _tasks

    init {
        loadTasks()
    }

    private fun loadTasks() {
        _tasks.value = repository.getAllTasks()
    }

    fun addTask(task: String) {
        repository.insertTask(task)
        loadTasks()
    }

    fun removeTask(id: Int) {
        repository.deleteTask(id)
        loadTasks()
    }
}
