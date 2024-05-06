package com.example.composenavigation.typesafety

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Serializable
object ListOfBooks

@Serializable
data class BookDetail(val book: Book)

val bookType = object : NavType<Book>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Book? {
        return bundle.getString(key)?.let { Json.decodeFromString<Book>(it) }
    }

    override fun parseValue(value: String) = Json.decodeFromString<Book>(value)

    override fun serializeAsValue(value: Book): String = Json.encodeToString(value)

    override fun put(bundle: Bundle, key: String, value: Book) {
        bundle.putString(key, Json.encodeToString(value))
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
            typeMap = mapOf(typeOf<Book>() to bookType)
        ) {
            BookDetailScreen(modifier = modifier)
        }
    }
}
