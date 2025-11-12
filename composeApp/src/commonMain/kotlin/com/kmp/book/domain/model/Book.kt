package com.kmp.book.domain.model

data class Book(
    val meta: Meta,
    val documents: List<Documents>,
)

data class Meta(
    val isEnd: Boolean,
    val pageableCount: Int,
    val totalCount: Int,
)

data class Documents(
    val title: String,
    val contents: String,
    val url: String,
    val isbn: String,
    val datetime: String,
    val publisher: String,
    val authors: List<String>,
    val translators: List<String>,
    val price: Int,
    val salePrice: Int,
    val thumbnail: String,
    val status: String,
)