package com.kmp.book

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform