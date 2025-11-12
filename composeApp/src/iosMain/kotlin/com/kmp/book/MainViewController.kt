package com.kmp.book

import androidx.compose.ui.window.ComposeUIViewController
import com.kmp.book.di.initKoin
import com.kmp.book.presentation.navigation.BookNavigation

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { BookNavigation() }