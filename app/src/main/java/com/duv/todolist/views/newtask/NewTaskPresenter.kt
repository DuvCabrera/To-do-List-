package com.duv.todolist.views.newtask

import com.duv.todolist.data.TaskRepository
import com.duv.todolist.model.TaskModel

class NewTaskPresenter (private val taskRepository: TaskRepository, private val view: NewTaskView) {

    fun addNewTask( title:String, description: String ){
        when {
            title.isEmpty() -> {
                view.handleError("O Titulo esta vazio")
            }
            description.isEmpty() -> {
                view.handleError("Adicione uma descrição")
            }
            else -> {
                taskRepository.saveTask(TaskModel(null,title,description,0))
                view.handleSuccess()
            }
        }
    }
}