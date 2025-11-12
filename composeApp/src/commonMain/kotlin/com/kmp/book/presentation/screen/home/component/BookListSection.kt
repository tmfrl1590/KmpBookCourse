package com.kmp.book.presentation.screen.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmp.book.domain.model.Documents
import com.kmp.book.presentation.screen.home.state.HomeState
import com.kmp.book.util.ImageLoading
import com.kmp.book.util.convertToAuthor
import com.kmp.book.util.formatPrice

@Composable
fun BookListSection(
    state: HomeState,
    onClick: (Documents) -> Unit,
    onScrolledToEnd: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxSize()
        ,
        contentAlignment = Alignment.Center
    ){
        when {
            state.isLoading -> CircularProgressIndicator()
            state.bookList.isEmpty() -> Text(text = "데이터가 없습니다.")
            else -> {
                BookList(
                    bookList = state.bookList,
                    onClick = onClick,
                    onScrolledToEnd = onScrolledToEnd
                )
            }
        }
    }
}

@Composable
private fun BookList(
    bookList: List<Documents>,
    onClick: (Documents) -> Unit,
    onScrolledToEnd: () -> Unit,
){
    val listState = rememberLazyListState()

    // 리스트가 끝까지 스크롤되었는지 감지
    val visibleItemsInfo = listState.layoutInfo.visibleItemsInfo
    val isScrolledToEnd = if (listState.layoutInfo.totalItemsCount == 0) {
        false
    } else {
        val lastVisibleItem = visibleItemsInfo.last()
        val viewportHeight = listState.layoutInfo.viewportEndOffset + listState.layoutInfo.viewportStartOffset
        (lastVisibleItem.index == listState.layoutInfo.totalItemsCount - 1) && (lastVisibleItem.offset + lastVisibleItem.size <= viewportHeight)
    }

    // 스크롤이 끝까지 가면 콜백 호출
    LaunchedEffect(isScrolledToEnd) {
        if (isScrolledToEnd) {
            onScrolledToEnd()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        state = listState
    ) {
        itemsIndexed(
            items = bookList,
            key = { index, item ->
                index
            }
        ){ _,  item ->
            SearchedBookItem(
                documents = item,
                onClick = {
                    onClick(item)
                }
            )
        }
    }
}

@Composable
private fun SearchedBookItem(
    documents: Documents,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        BookImage(
            modifier = Modifier
                .size(80.dp)
            ,
            imageUrl = documents.thumbnail,
        )

        Spacer(modifier = Modifier.width(12.dp))

        BookInfo(documents = documents)
    }
}

@Composable
private fun BookImage(
    modifier: Modifier,
    imageUrl: String,
) {
    ImageLoading(
        modifier = modifier,
        imageUrl = imageUrl,
    )
}

@Composable
private fun BookInfo(
    documents: Documents,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
        ,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = documents.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1, // 한 줄로 제한
            overflow = TextOverflow.Ellipsis, // 한 줄이 넘을 경우 "..." 표시
        )
        Text(
            text = convertToAuthor(documents.authors),
            maxLines = 1, // 한 줄로 제한
            overflow = TextOverflow.Ellipsis, // 한 줄이 넘을 경우 "..." 표시
        )
        Text(
            text = "${formatPrice(documents.salePrice)}원",
        )
    }
}