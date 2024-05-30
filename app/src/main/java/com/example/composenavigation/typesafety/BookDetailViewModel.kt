package com.example.composenavigation.typesafety

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val bookDetail = BookDetail.from(savedStateHandle)

    private val _book = MutableStateFlow(bookDetail.book)
    val book = _book.asStateFlow()
}
