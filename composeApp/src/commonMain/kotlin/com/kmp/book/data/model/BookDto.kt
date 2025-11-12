package com.kmp.book.data.model

import com.kmp.book.domain.model.Documents
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    val meta: MetaDto,
    val documents: List<DocumentsDto>,
)

@Serializable
data class MetaDto(
    @SerialName("is_end")
    val isEnd: Boolean,
    @SerialName("pageable_count")
    val pageableCount: Int,
    @SerialName("total_count")
    val totalCount: Int,
)

@Serializable
data class DocumentsDto(
    val title: String,
    val contents: String,
    val url: String,
    val isbn: String,
    val datetime: String,
    val publisher: String,
    val authors: List<String>,
    val translators: List<String>,
    val price: Int,
    @SerialName("sale_price")
    val salePrice: Int,
    val thumbnail: String,
    val status: String,
)