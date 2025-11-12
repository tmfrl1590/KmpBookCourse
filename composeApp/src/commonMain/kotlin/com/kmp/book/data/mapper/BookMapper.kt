package com.kmp.book.data.mapper

import com.kmp.book.data.model.BookDto
import com.kmp.book.data.model.DocumentsDto
import com.kmp.book.domain.model.Book
import com.kmp.book.domain.model.Documents
import com.kmp.book.domain.model.Meta

object BookMapper {

    fun mapperToBook(bookDto: BookDto): Book{
        return Book(
            meta = Meta(
                isEnd = bookDto.meta.isEnd,
                pageableCount = bookDto.meta.pageableCount,
                totalCount = bookDto.meta.totalCount,
            ),
            documents = bookDto.documents.map {
                mapperToDocuments(it)
            }
        )
    }

    private fun mapperToDocuments(documentsDto: DocumentsDto): Documents{
        return Documents(
            title = documentsDto.title,
            contents = documentsDto.contents,
            url = documentsDto.url,
            isbn = documentsDto.isbn,
            datetime = documentsDto.datetime,
            publisher = documentsDto.publisher,
            authors = documentsDto.authors,
            translators = documentsDto.translators,
            price = documentsDto.price,
            salePrice = documentsDto.salePrice,
            thumbnail = documentsDto.thumbnail,
            status = documentsDto.status
        )
    }
}