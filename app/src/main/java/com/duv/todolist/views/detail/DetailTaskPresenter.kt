package com.duv.todolist.views.detail

import com.duv.todolist.data.TaskRepository
import com.duv.todolist.model.TaskModel

class DetailTaskPresenter (private val view: DetailTaskView, private val taskRepository: TaskRepository) {

    fun getSingleDetail(id: Int) {
        val task = taskRepository.getSingleTask(id)

        if (task == null){
            view.handleError("Ocorreu algum erro =(")
        }else {
            view.handleSuccess(task)
        }
    }
}