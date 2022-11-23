package com.example.sqlitelearning.utils

import android.database.Cursor

object DBUtils {

    fun getString(cursor: Cursor, columnName: String): String {
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName))
    }

    fun getFloat(cursor: Cursor, columnName: String): Float {
        return cursor.getFloat(cursor.getColumnIndexOrThrow(columnName))
    }

    fun getInteger(cursor: Cursor, columnName: String): Int {
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName))
    }

    fun getLong(cursor: Cursor, columnName: String): Long {
        return cursor.getLong(cursor.getColumnIndexOrThrow(columnName))
    }

    fun getBoolean(cursor: Cursor, columnName: String): Boolean {
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName)) == 1
    }

    fun getDouble(cursor: Cursor, columnName: String): Double {
        return cursor.getDouble(cursor.getColumnIndexOrThrow(columnName))
    }

}