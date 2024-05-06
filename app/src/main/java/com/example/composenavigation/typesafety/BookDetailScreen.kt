package com.example.composenavigation.typesafety

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BookDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: BookDetailViewModel = hiltViewModel(),
) {
    val book by viewModel.book.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text("Id: ${book.id}")
        Text("Title: ${book.title}")
    }
}
