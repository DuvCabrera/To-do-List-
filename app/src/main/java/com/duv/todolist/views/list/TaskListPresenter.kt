package com.duv.todolist.views.list

import com.duv.todolist.data.TaskRepository
import com.duv.todolist.model.TaskModel


class TaskListPresenter (private val view :TaskListView, private val taskRepository: TaskRepository) {
    fun getTaskList() {
        view.initTaskList(taskRepository.getTaskList())
    }

    fun deleteTask(id:Int){
        taskRepository.deleteTask(id)
    }

    fun updateTask(task:TaskModel){
        taskRepository.updateTask(task)
    }
}