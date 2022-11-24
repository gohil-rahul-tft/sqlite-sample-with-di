package com.example.sqlitelearning.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqlitelearning.dao.BookDao
import com.example.sqlitelearning.data.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val dao: BookDao,
) : ViewModel() {

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

//    private val _books = MutableStateFlow<List<Book>?>(null)
//    val books = _books.asStateFlow()
//
//    init {
//        getBooks()
//    }

    fun getBooks() = flow {
        val books = dao.getAllBooks()
//        _books.value = books
        emit(books)
    }

    fun addBook(book: Book) = viewModelScope.launch {
        if (dao.addBook(book) != 1L) {
//            getBooks()
            _message.emit("Book Inserted Successfully")
        }
    }

    fun updateBook(book: Book) = viewModelScope.launch {
        if (dao.updateBook(book) > 0) {
//            getBooks()
            _message.emit("Book Updated Successfully")
        }
    }

    fun deleteBook(id: Int) = viewModelScope.launch {
        if (dao.deleteBook(id) > 0) {
//            getBooks()
            _message.emit("Book Deleted Successfully")
        }
    }
}