package com.example.sqlitelearning

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlitelearning.dao.BookDao
import com.example.sqlitelearning.data.Book
import com.example.sqlitelearning.databinding.ActivityAddBinding
import com.example.sqlitelearning.db.DatabaseHelper
import com.example.sqlitelearning.utils.normalText
import com.example.sqlitelearning.view_models.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

//    private lateinit var db: DatabaseHelper
//    private lateinit var dao: BookDao

    private val context = this
    private val viewModel by viewModels<BookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        db = DatabaseHelper(context)
//        dao = BookDao(db)

        init()
        clickListener()
    }

    private fun clickListener() {
        binding.addButton.setOnClickListener {

            val title = binding.titleInput.normalText()
            val author = binding.authorInput.normalText()
            val pages = binding.pagesInput.normalText()

            val book = Book(
                title = title,
                author = author,
                pages = pages,
            )

            viewModel.addBook(book)
        }
    }

    private fun init() {

    }
}