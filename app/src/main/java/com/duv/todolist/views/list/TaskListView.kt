package com.duv.todolist.views.list

import com.duv.todolist.model.TaskModel

interface TaskListView {
    fun initTaskList(list: List<TaskModel>){}


}