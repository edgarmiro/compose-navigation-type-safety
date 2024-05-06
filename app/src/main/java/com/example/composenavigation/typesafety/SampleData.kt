package com.example.composenavigation.typesafety

object SampleData {
    val books = (0..25).map {
        Book(it, "Book $it")
    }

    fun getBook(id: Int) = books.first { it.id == id }
}
