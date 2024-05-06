package com.example.composenavigation.typesafety

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Book(val id: Int, val title: String): Parcelable
