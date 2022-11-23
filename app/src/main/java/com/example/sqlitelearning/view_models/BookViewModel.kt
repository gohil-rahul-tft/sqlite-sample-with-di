package com.example.sqlitelearning.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqlitelearning.dao.BookDao
import com.example.sqlitelearning.data.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val dao: BookDao,
) : ViewModel() {

    private val _books = MutableStateFlow<List<Book>?>(null)
    val books = _books.asStateFlow()

    init {
        getBooks()
    }

    private fun getBooks() = viewModelScope.launch {
        val books = dao.getAllBooks()
        _books.emit(books)
    }

    fun addBook(book: Book) = viewModelScope.launch {
        dao.addBook(book)
        getBooks()
    }

    fun updateBook(book: Book) = viewModelScope.launch {
        dao.updateBook(book)
        getBooks()
    }

    fun deleteBook(id: Int) = viewModelScope.launch {
        dao.deleteBook(id)
        getBooks()
    }
}