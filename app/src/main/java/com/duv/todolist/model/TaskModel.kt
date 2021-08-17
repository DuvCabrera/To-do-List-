package com.duv.todolist.model

data class TaskModel (
    val id : Int?,
    var title: String,
    var description: String,
    var isConcluded: Int ?
    )