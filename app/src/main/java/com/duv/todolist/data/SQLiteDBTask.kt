package com.duv.todolist.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteDBTask(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION ){

    companion object{
        const val DATABASE_NAME = "TASK_DB"
        private const val VERSION = 1

        const val TABLE_NAME = "TAREFAS"

        const val COLUMNS_ID = "ID"
        const val COLUMNS_TITLE = "TITLE"
        const val COLUMNS_DESCRIPTION = "DESCRIPTION"
        const val COLUMNS_CHECKCONCLUDED = "CONCLUIDO"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(getTableString())
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    private fun getTableString(): String {
        return "CREATE TABLE $TABLE_NAME (" +
                "$COLUMNS_ID INTEGER NOT NULL," +
                "$COLUMNS_TITLE TEXT NOT NULL," +
                "$COLUMNS_DESCRIPTION TEXT NOT NULL," +
                "$COLUMNS_CHECKCONCLUDED INTEGER NOT NULL," +
                "" +
                "PRIMARY KEY($COLUMNS_ID AUTOINCREMENT)" +
                ")"
    }
}