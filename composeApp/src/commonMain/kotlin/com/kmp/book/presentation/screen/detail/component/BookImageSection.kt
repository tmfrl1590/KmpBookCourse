package com.kmp.book.presentation.screen.detail.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kmp.book.util.ImageLoading

@Composable
fun BookImageSection(
    imageUrl: String,
){
    ImageLoading(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
        ,
        imageUrl = imageUrl,
    )
}