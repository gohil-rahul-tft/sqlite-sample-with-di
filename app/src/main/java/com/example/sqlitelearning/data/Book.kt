package com.example.sqlitelearning.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: Int = 0,
    val title: String,
    val author: String,
    val pages: String,
) : Parcelable
