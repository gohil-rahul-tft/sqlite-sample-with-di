package com.example.sqlitelearning

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlitelearning.dao.BookDao
import com.example.sqlitelearning.data.Book
import com.example.sqlitelearning.databinding.ActivityMainBinding
import com.example.sqlitelearning.db.DatabaseHelper
import com.example.sqlitelearning.utils.collectLatestFlow
import com.example.sqlitelearning.view_models.BookViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BookAdapter.BookInterface {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding
    private val context = this

//    private lateinit var db: DatabaseHelper
//    private lateinit var dao: BookDao

    private val bookAdapter by lazy { BookAdapter(this) }
    private val viewModel by viewModels<BookViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        db = DatabaseHelper(context)
//        dao = BookDao(db)

        init()
        clickListener()
        collectData()
    }


    private fun init() {

//        val books = dao.getAllBooks()

        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = bookAdapter
        }

//        bookAdapter.submitList(books)

    }


    private fun collectData() {
        collectLatestFlow(viewModel.books) {
            Log.i(TAG, "init: $it")
            bookAdapter.submitList(it)
        }
    }

    private fun clickListener() {
        binding.addButton.setOnClickListener {
            Intent(context, AddActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onClick(book: Book) {
        println("$book")

        Intent(context, UpdateActivity::class.java).also {
            it.putExtra("book", book)
            startActivity(it)
        }
    }

    override fun onDelete(book: Book) {
        viewModel.deleteBook(book.id)
    }
}