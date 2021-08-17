package com.duv.todolist.data

import android.content.ContentValues
import com.duv.todolist.model.TaskModel

class TaskRepository (private var taskDB: SQLiteDBTask?){

    fun saveTask( task: TaskModel){
        var db = taskDB?.writableDatabase ?: return
        var content = ContentValues()
        content.put(SQLiteDBTask.COLUMNS_TITLE, task.title)
        content.put(SQLiteDBTask.COLUMNS_DESCRIPTION, task.description)
        content.put(SQLiteDBTask.COLUMNS_CHECKCONCLUDED, task.isConcluded)

        db.insert(SQLiteDBTask.TABLE_NAME, null, content)
        db.close()
    }

    fun deleteAllTasks(): Boolean {
        val db = taskDB?.writableDatabase
        val _success = db?.delete(SQLiteDBTask.TABLE_NAME, null , null)
            ?.toLong()
        db?.close()
        return ("$_success").toInt() != -1
    }

    fun updateTask(task: TaskModel) {
        val db = taskDB?.writableDatabase ?: return
        val sql = "UPDATE ${SQLiteDBTask.TABLE_NAME} SET ${SQLiteDBTask.COLUMNS_TITLE} = ?, ${SQLiteDBTask.COLUMNS_DESCRIPTION}= ?, ${SQLiteDBTask.COLUMNS_CHECKCONCLUDED} =? WHERE ${SQLiteDBTask.COLUMNS_ID} = ?"
        val arg = arrayOf(task.title,task.description,task.id, task.isConcluded)
        db.execSQL(sql,arg)
        db.close()
    }

    fun deleteTask2(id: Int): Boolean {
        var ids = id - 1
        var db = taskDB?.writableDatabase
        val _success = db?.delete(SQLiteDBTask.TABLE_NAME, SQLiteDBTask.COLUMNS_ID  + "=?", arrayOf(ids.toString()))
            ?.toLong()
        return ("$_success").toInt() != -1
    }

    fun deleteTask( id: Int){
        var db = taskDB?.writableDatabase ?: return
        var ids = id
        val where = "id =?"
        val args = arrayOf("$id")
        db.delete(SQLiteDBTask.TABLE_NAME, where, args)
        db.close()
    }

    fun getTaskList() : List<TaskModel> {
        val readableDatabase = taskDB?.readableDatabase ?: return mutableListOf()

        var taskList = mutableListOf<TaskModel>()
        var cursor = readableDatabase.query(SQLiteDBTask.TABLE_NAME, null, null, arrayOf(), null, null, null)

        if (cursor == null){
            readableDatabase.close()
            return mutableListOf()
        }

        while (cursor.moveToNext()) {
            var task = TaskModel (
                cursor.getInt(cursor.getColumnIndex(SQLiteDBTask.COLUMNS_ID)),
                cursor.getString(cursor.getColumnIndex(SQLiteDBTask.COLUMNS_TITLE)),
                cursor.getString(cursor.getColumnIndex(SQLiteDBTask.COLUMNS_DESCRIPTION)),
                cursor.getInt(cursor.getColumnIndex(SQLiteDBTask.COLUMNS_CHECKCONCLUDED))
            )
            taskList.add(task)
        }
        readableDatabase.close()
        return taskList
    }

    fun getSingleTask(id: Int): TaskModel? {
        val readableDatabase = taskDB?.readableDatabase ?: null
        val ids = id -1
        var singleTask: TaskModel? = null
        var cursor = readableDatabase?.query(SQLiteDBTask.TABLE_NAME, null,
            id.toString(), arrayOf(), null, null, null)

        if (cursor == null){
            readableDatabase?.close()
            return null
        }

        cursor.moveToPosition(ids)
        var task = TaskModel (
            cursor.getInt(cursor.getColumnIndex(SQLiteDBTask.COLUMNS_ID)),
            cursor.getString(cursor.getColumnIndex(SQLiteDBTask.COLUMNS_TITLE)),
            cursor.getString(cursor.getColumnIndex(SQLiteDBTask.COLUMNS_DESCRIPTION)),
            cursor.getInt(cursor.getColumnIndex(SQLiteDBTask.COLUMNS_CHECKCONCLUDED)),
        )
        singleTask = task

        readableDatabase?.close()
        return singleTask
    }

}