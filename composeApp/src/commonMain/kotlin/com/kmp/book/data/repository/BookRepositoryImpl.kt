package com.kmp.book.data.repository

import com.kmp.book.data.mapper.BookMapper.mapperToBook
import com.kmp.book.data.model.BookDto
import com.kmp.book.domain.model.Book
import com.kmp.book.domain.repository.BookRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://dapi.kakao.com/v3/search/book"
private const val SIZE = 20

class BookRepositoryImpl(
    private val httpClient: HttpClient
): BookRepository {
    override suspend fun searchBooks(
        query: String,
        page: Int
    ): Result<Book> = runCatching {
        val response = httpClient.get(urlString = BASE_URL) {
            parameter("query", query)
            parameter("size", SIZE)
            parameter("page", page)
        }.body<BookDto>()


        mapperToBook(response)
    }
}