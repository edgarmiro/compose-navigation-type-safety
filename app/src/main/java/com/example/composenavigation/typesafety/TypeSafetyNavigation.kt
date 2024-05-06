package com.example.composenavigation.typesafety

import android.os.Build
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Serializable
object ListOfBooks

@Serializable
data class BookDetail(val book: Book)

val BookType = object : NavType<Book>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): Book? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, Book::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): Book {
        return Json.decodeFromString<Book>(value)
    }

    override fun put(bundle: Bundle, key: String, value: Book) {
        bundle.putParcelable(key, value)
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
            typeMap = mapOf(typeOf<Book>() to BookType)
        ) { backStackEntry ->
            val book = backStackEntry.toRoute<BookDetail>().book

            BookDetailScreen(
                modifier = modifier,
                book = book,
            )
        }
    }
}
