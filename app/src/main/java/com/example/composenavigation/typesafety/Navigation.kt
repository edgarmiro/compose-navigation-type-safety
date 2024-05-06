package com.example.composenavigation.typesafety

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "list-of-books") {
        composable(route = "list-of-books") {
            ListOfBooksScreen(
                modifier = modifier,
                onBookClick = { navController.navigate("book-detail/${it.id}") },
            )
        }

        composable(
            route = "book-detail/{bookId}",
            arguments = listOf(
                navArgument("bookId") {
                    type = NavType.IntType
                }
            ),
        ) { backStackEntry ->
            val bookId = requireNotNull(backStackEntry.arguments?.getInt("bookId"))
            val book by remember { mutableStateOf(SampleData.getBook(bookId)) }

            BookDetailScreen(
                modifier = modifier,
                book = book,
            )
        }
    }
}
