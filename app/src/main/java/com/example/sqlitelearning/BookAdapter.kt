package com.example.sqlitelearning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitelearning.data.Book
import com.example.sqlitelearning.databinding.MyRowBinding

class BookAdapter(private val listener: BookInterface) :
    ListAdapter<Book, BookAdapter.CustomerViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding =
            MyRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    inner class CustomerViewHolder(private val binding: MyRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onClick(getItem(position))
                }
            }

            binding.root.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDelete(getItem(position))
                }

                true
            }
        }


        fun bind(item: Book) {

            binding.apply {
                bookIdTxt.text = item.id.toString()
                bookAuthorTxt.text = item.author
                bookPagesTxt.text = item.pages
                bookTitleTxt.text = item.title
            }

        }


    }

    class DiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(old: Book, aNew: Book) =
            old.id == aNew.id

        override fun areContentsTheSame(old: Book, aNew: Book) =
            old == aNew
    }


    interface BookInterface {
        fun onClick(book: Book)
        fun onDelete(book: Book)
    }

}