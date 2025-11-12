package com.kmp.book.util

import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun formatIsoDate(isoDate: String): String {
    // ISO-8601 문자열을 Instant로 변환
    val instant = Instant.parse(isoDate)

    // 원하는 시간대의 LocalDateTime으로 변환 (예: Asia/Seoul)
    val dateTime = instant.toLocalDateTime(TimeZone.of("Asia/Seoul"))

    // 년, 월, 일 포맷으로 반환
    return "${dateTime.year}년 ${dateTime.month.number}월 ${dateTime.day}일"
}

fun convertToAuthor(authors: List<String>): String{
    return authors.joinToString(separator = ", ")
}