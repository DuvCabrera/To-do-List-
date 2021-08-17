package com.duv.todolist.views.detail

import com.duv.todolist.data.TaskRepository
import com.duv.todolist.model.TaskModel

interface DetailTaskView {
    fun handleError(errorMessage: String)
    fun handleSuccess(task: TaskModel)
}