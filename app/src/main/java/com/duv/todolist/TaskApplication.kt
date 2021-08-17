package com.duv.todolist

import android.app.Application
import com.duv.todolist.data.SQLiteDBTask

class TaskApplication : Application() {

    var helperDB: SQLiteDBTask? = null

    companion object {
        lateinit var instance: TaskApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        helperDB = SQLiteDBTask(this)
    }
}