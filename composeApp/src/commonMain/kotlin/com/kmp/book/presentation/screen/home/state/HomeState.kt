package com.kmp.book.presentation.screen.home.state

import com.kmp.book.domain.model.Documents

data class HomeState(
    val inputQuery: String = "", // 입력한 검색어
    val isLoading: Boolean = false, // 로딩중인지
    val bookList: List<Documents> = emptyList(), // 검색된 리스트
    val currentPage: Int = 1, // 현재 페이지
)