package com.example.todoapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class ToDoRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun insertTask(task: String): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.ToDoEntry.COLUMN_TASK, task)
        }
        return db.insert(DatabaseHelper.ToDoEntry.TABLE_NAME, null, values)
    }

    fun getAllTasks(): List<ToDo> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            DatabaseHelper.ToDoEntry.TABLE_NAME,
            null, null, null, null, null, null
        )

        val tasks = mutableListOf<ToDo>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseHelper.ToDoEntry.COLUMN_ID))
                val task = getString(getColumnIndexOrThrow(DatabaseHelper.ToDoEntry.COLUMN_TASK))
                tasks.add(ToDo(id, task))
            }
        }
        cursor.close()
        return tasks
    }

    fun deleteTask(id: Int): Int {
        val db = dbHelper.writableDatabase
        val selection = "${DatabaseHelper.ToDoEntry.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        return db.delete(DatabaseHelper.ToDoEntry.TABLE_NAME, selection, selectionArgs)
    }
}
