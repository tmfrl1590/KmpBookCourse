package com.kmp.book.presentation.screen.home.action

sealed interface HomeAction {
    data class OnInputSearchQuery(val query: String): HomeAction
    data object OnSearchBook: HomeAction
    data object OnResetSearchQuery: HomeAction
    data object OnNextPage: HomeAction
}