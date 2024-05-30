package com.example.composenavigation.typesafety

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
object ListOfBooks

@Serializable
data class BookDetail(val book: Book) {
    companion object {
        val typeMap = mapOf(typeOf<Book>() to serializableType<Book>())

        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<BookDetail>(typeMap)
    }
}

@Composable
fun TypeSafetyNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = ListOfBooks) {
        composable<ListOfBooks> {
            ListOfBooksScreen(
                modifier = modifier,
                onBookClick = { navController.navigate(BookDetail(it)) },
            )
        }

        composable<BookDetail>(
            typeMap = BookDetail.typeMap
        ) {
            BookDetailScreen(modifier = modifier)
        }
    }
}
