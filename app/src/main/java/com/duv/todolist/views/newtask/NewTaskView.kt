package com.duv.todolist.views.newtask

interface NewTaskView {
    fun handleError(errorMessage: String)
    fun handleSuccess()
}