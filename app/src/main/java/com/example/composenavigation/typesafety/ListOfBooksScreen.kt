package com.example.composenavigation.typesafety

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListOfBooksScreen(
    modifier: Modifier = Modifier,
    onBookClick: (Book) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(SampleData.books) {
            BookCell(
                modifier = Modifier.clickable(onClick = { onBookClick(it) }),
                book = it,
            )
        }
    }
}

@Composable
fun BookCell(book: Book, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(16.dp),
        text = book.title,
    )
}
