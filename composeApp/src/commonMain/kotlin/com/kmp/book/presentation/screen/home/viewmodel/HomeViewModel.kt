package com.kmp.book.presentation.screen.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.book.domain.repository.BookRepository
import com.kmp.book.presentation.screen.home.action.HomeAction
import com.kmp.book.presentation.screen.home.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bookRepository: BookRepository
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    // 책 리스트 검색
    fun searchBookList(query: String, page: Int = 1){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = bookRepository.searchBooks(query = query, page = page)
            result.onSuccess { data ->
                val bookList = data.documents
                _state.update {
                    it.copy(
                        bookList = it.bookList + bookList,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onAction(action: HomeAction){
        when(action){
            is HomeAction.OnInputSearchQuery -> _state.update { it.copy(inputQuery = action.query) }
            is HomeAction.OnSearchBook -> {
                _state.update { it.copy(bookList = emptyList(), currentPage = 1 ) }
                searchBookList(query = _state.value.inputQuery)
            }
            is HomeAction.OnResetSearchQuery -> _state.update { it.copy(inputQuery = "") }
            is HomeAction.OnNextPage -> {
                _state.update { it.copy(currentPage = it.currentPage + 1) }
                searchBookList(
                    query = _state.value.inputQuery,
                    page = _state.value.currentPage
                )
            }
        }
    }
}