package com.example.sqlitelearning.db

object BookTable {

    const val TABLE_NAME = "books"
    const val ID = "id"
    const val TITLE = "title"
    const val AUTHOR = "author"
    const val PAGES = "book_pages"
    const val PRICE = "price"


    fun getCreateBookQuery(): String {
        val query = StringBuilder("CREATE TABLE ")
        query.append(TABLE_NAME)
        query.append(" ( ")
        query.append("$ID INTEGER PRIMARY KEY AUTOINCREMENT,")
        query.append("$TITLE TEXT NOT NULL,")
        query.append("$AUTHOR TEXT NOT NULL,")
        query.append("$PAGES TEXT NOT NULL")
        query.append(" )")

        return query.toString()
    }


    fun addPriceFieldQuery(): String {
        val query = StringBuilder("ALTER TABLE $TABLE_NAME ")
        query.append("ADD COLUMN $PRICE ")
        query.append("INTEGER DEFAULT 0;")

        return query.toString()
    }
}