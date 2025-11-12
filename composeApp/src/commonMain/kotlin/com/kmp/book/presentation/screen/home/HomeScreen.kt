package com.kmp.book.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kmp.book.presentation.navigation.Screen
import com.kmp.book.presentation.screen.home.action.HomeAction
import com.kmp.book.presentation.screen.home.component.BookListSection
import com.kmp.book.presentation.screen.home.component.InputQuerySection
import com.kmp.book.presentation.screen.home.viewmodel.HomeViewModel
import com.kmp.book.util.convertToAuthor
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = koinViewModel()
){
    val state by homeViewModel.state.collectAsStateWithLifecycle()

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
                .padding(horizontal = 20.dp)
        ) {
            InputQuerySection(
                inputText = state.inputQuery,
                onInputSearchQuery = { inputQuery -> homeViewModel.onAction(action = HomeAction.OnInputSearchQuery(inputQuery)) } ,
                onResetSearchQuery = { homeViewModel.onAction(action = HomeAction.OnResetSearchQuery)},
                onSearchBook = { homeViewModel.onAction(action = HomeAction.OnSearchBook)}
            )

            Spacer(
                modifier = Modifier
                    .height(40.dp)
            )

            BookListSection(
                state = state,
                onClick = { document ->
                    navController.navigate(
                        Screen.Detail(
                            thumbnail = document.thumbnail,
                            title = document.title,
                            contents = document.contents,
                            publisher = document.publisher,
                            salePrice = document.salePrice,
                            status = document.status,
                            authors = convertToAuthor(document.authors),
                            datetime = document.datetime
                        )
                    )
                },
                onScrolledToEnd = { homeViewModel.onAction(action = HomeAction.OnNextPage)}
            )
        }
    }
}