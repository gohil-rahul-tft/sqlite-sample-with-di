package com.example.sqlitelearning.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


fun <T> AppCompatActivity.collectLatestFlow(
    flow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    collect: suspend (T) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(lifecycleState) {
            flow.collectLatest(collect)
        }
    }
}