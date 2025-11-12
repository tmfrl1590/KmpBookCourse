package com.kmp.book.util

fun formatPrice(price: Int): String {
    return price.toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}