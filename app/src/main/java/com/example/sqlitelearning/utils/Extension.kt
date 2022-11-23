package com.example.sqlitelearning.utils

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import java.util.*


fun Context.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun EditText.normalText() = this.text.toString().trim()
fun EditText.upperCaseText() =
    this.text.toString().uppercase(Locale.getDefault()).trim()
