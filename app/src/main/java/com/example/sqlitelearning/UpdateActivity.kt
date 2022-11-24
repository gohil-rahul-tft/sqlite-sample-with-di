package com.example.sqlitelearning

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlitelearning.dao.BookDao
import com.example.sqlitelearning.data.Book
import com.example.sqlitelearning.databinding.ActivityAddBinding
import com.example.sqlitelearning.databinding.ActivityUpdateBinding
import com.example.sqlitelearning.db.DatabaseHelper
import com.example.sqlitelearning.utils.collectLatestFlow
import com.example.sqlitelearning.utils.normalText
import com.example.sqlitelearning.utils.toast
import com.example.sqlitelearning.view_models.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "UpdateActivity"
    }

    private lateinit var binding: ActivityUpdateBinding

//    private lateinit var db: DatabaseHelper
//    private lateinit var dao: BookDao

    private val context = this
    private val viewModel by viewModels<BookViewModel>()

    private var book: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        db = DatabaseHelper(context)
//        dao = BookDao(db)

        init()
        clickListener()
        collectData()
    }

    private fun init() {
        book = intent.getParcelableExtra("book")

        binding.titleInput2.setText(book?.title)
        binding.authorInput2.setText(book?.author)
        binding.pagesInput2.setText(book?.pages)


    }

    private fun clickListener() {
        binding.updateButton.setOnClickListener {

            val title = binding.titleInput2.normalText()
            val author = binding.authorInput2.normalText()
            val pages = binding.pagesInput2.normalText()

            val book = Book(
                id = book?.id ?: -1,
                title = title,
                author = author,
                pages = pages,
            )

            println("${viewModel.updateBook(book)}")
        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteBook(book?.id ?: return@setOnClickListener)
        }
    }

    private fun collectData() {
        collectLatestFlow(flow = viewModel.message) {
            Log.i(TAG, "Collect Data: $it")
            context.toast(it)
            finish()
        }
    }

}