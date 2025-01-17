package com.example.todoapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "ToDoList.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${ToDoEntry.TABLE_NAME} (" +
                    "${ToDoEntry.COLUMN_ID} INTEGER PRIMARY KEY," +
                    "${ToDoEntry.COLUMN_TASK} TEXT)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ToDoEntry.TABLE_NAME}"
    }

    object ToDoEntry {
        const val TABLE_NAME = "todo"
        const val COLUMN_ID = "id"
        const val COLUMN_TASK = "task"
    }
}
