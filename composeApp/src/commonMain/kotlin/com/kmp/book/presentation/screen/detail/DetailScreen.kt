package com.kmp.book.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kmp.book.presentation.screen.detail.component.BookImageSection
import com.kmp.book.presentation.screen.detail.component.BookInfoSection
import com.kmp.book.presentation.screen.detail.component.DetailTopBar

@Composable
fun DetailScreen(
    navController: NavHostController,
    thumbnail: String,
    title: String,
    contents: String,
    publisher: String,
    salePrice: Int,
    status: String,
    authors: String,
    datetime: String,
){
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            DetailTopBar(
                title = title,
                onNavigationClick = { navController.popBackStack() }
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
                .verticalScroll(scrollState)
        ) {
            BookImageSection(
                imageUrl = thumbnail
            )

            Spacer(modifier = Modifier.height(20.dp))

            BookInfoSection(
                title = title,
                contents = contents,
                publisher = publisher,
                salePrice = salePrice,
                status = status,
                authors = authors,
                datetime = datetime,
            )
        }
    }
}