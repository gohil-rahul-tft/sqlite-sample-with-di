package com.example.sqlitelearning.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.sqlitelearning.data.Book
import com.example.sqlitelearning.utils.DBUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DatabaseHelper @Inject constructor(@ApplicationContext context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val TAG = "DatabaseHelper"

        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "BookLibrary.db"
    }


    override fun onCreate(db: SQLiteDatabase) {
        addBookTable(db)
    }

    private fun addBookTable(db: SQLiteDatabase) {
        db.execSQL(BookTable.getCreateBookQuery())
    }

    /*override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS ${BookTable.TABLE_NAME}")
        onCreate(db)

        Log.e(TAG, "onUpgrade: CALLED")
    }*/

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        Log.e(TAG, "onUpgrade: $oldVersion == $newVersion")
        when {
            oldVersion < 2 -> {
                upgradeVersion2(db)
            }
            /*oldVersion <  3 -> {
                upgradeVersion3(db)

            }
            oldVersion <  4 -> {
                upgradeVersion4(db)
            }*/
        }
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }


    private fun upgradeVersion2(db: SQLiteDatabase) {
//      ALTER TABLE Notes ADD COLUMN image TEXT NOT NULL DEFAULT 'unknown'
//        db.execSQL("ALTER TABLE ${BookTable.TABLE_NAME} ADD COLUMN ${BookTable.PRICE} INTEGER DEFAULT 0;")
        db.execSQL(BookTable.addPriceFieldQuery())

    }

}