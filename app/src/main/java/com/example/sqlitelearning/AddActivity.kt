package com.example.sqlitelearning

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.example.sqlitelearning.dao.BookDao
import com.example.sqlitelearning.data.Book
import com.example.sqlitelearning.databinding.ActivityAddBinding
import com.example.sqlitelearning.db.DatabaseHelper
import com.example.sqlitelearning.utils.collectLatestFlow
import com.example.sqlitelearning.utils.normalText
import com.example.sqlitelearning.utils.toast
import com.example.sqlitelearning.view_models.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "AddActivity"
    }

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
        collectData()
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


    private fun collectData() {
        collectLatestFlow(flow = viewModel.message) {
            Log.i(TAG, "Collect Data: $it")
            context.toast(it)
            finish()
        }
    }
}