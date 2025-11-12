package com.kmp.book.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Home: Screen
    @Serializable
    data class Detail(
        val thumbnail: String,
        val title: String,
        val contents: String,
        val publisher: String,
        val salePrice: Int,
        val status: String,
        val authors: String,
        val datetime: String,
    ): Screen
}