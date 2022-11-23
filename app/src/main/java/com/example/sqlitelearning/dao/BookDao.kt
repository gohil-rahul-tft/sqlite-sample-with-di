package com.example.sqlitelearning.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.sqlitelearning.data.Book
import com.example.sqlitelearning.db.BookTable
import com.example.sqlitelearning.db.DatabaseHelper
import com.example.sqlitelearning.utils.DBUtils
import javax.inject.Inject


class BookDao @Inject constructor(private val dbHelper: DatabaseHelper) {

    companion object {
        private const val TAG = "BookDao"
    }

    fun getAllBooks(): List<Book> {
        val list = mutableListOf<Book>()
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.query(BookTable.TABLE_NAME, null, null, null, null, null, null)

        cursor.use {
            while (cursor.moveToNext()) {
                list.add(
                    Book(
                        id = DBUtils.getInteger(cursor, BookTable.ID),
                        title = DBUtils.getString(cursor, BookTable.TITLE),
                        author = DBUtils.getString(cursor, BookTable.AUTHOR),
                        pages = DBUtils.getString(cursor, BookTable.PAGES),
                    )
                )
            }
        }

        return list
    }


    fun addBook(book: Book) {
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val cv = ContentValues().also { cv ->
            cv.put(BookTable.TITLE, book.title)
            cv.put(BookTable.AUTHOR, book.author)
            cv.put(BookTable.PAGES, book.pages)
        }

        when (db.insert(BookTable.TABLE_NAME, null, cv)) {
            -1L -> {
                Log.d(TAG, "addBook: Failed")
            }
            else -> {
                Log.d(TAG, "Added Successfully!")
            }
        }
    }


    fun updateBook(book: Book): Int {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val values = ContentValues().also {
            it.put(BookTable.TITLE, book.title)
            it.put(BookTable.AUTHOR, book.author)
            it.put(BookTable.PAGES, book.pages)
        }

        return db.update(BookTable.TABLE_NAME,
            values,
            "${BookTable.ID} = ${book.id}", null)
    }


    fun deleteBook(bookId: Int): Int {
        val db: SQLiteDatabase = dbHelper.readableDatabase

        return db.delete(BookTable.TABLE_NAME,
            "${BookTable.ID} = ?",
            arrayOf(bookId.toString()))
    }


}