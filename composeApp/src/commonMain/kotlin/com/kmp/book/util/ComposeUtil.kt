package com.kmp.book.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage

@Composable
fun ImageLoading(
    modifier: Modifier,
    imageUrl: String? = null,
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NetworkImageLoad(
                modifier = Modifier
                    .fillMaxSize(),
                url = imageUrl,
            )
        }
    }
}

@Composable
fun NetworkImageLoad(
    modifier: Modifier = Modifier,
    url: String? = null,
) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = "image",
        contentScale = ContentScale.Crop,
        modifier = modifier,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                ,
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
    )
}