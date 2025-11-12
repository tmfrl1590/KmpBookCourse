package com.kmp.book.domain.repository

import com.kmp.book.domain.model.Book

interface BookRepository {

    suspend fun searchBooks(query: String, page: Int): Result<Book>
}