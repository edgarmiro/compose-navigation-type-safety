package com.example.composenavigation.typesafety

import kotlinx.serialization.Serializable

@Serializable
data class Book(val id: Int, val title: String)
