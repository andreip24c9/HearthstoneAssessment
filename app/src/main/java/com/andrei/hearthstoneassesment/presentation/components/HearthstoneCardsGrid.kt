package com.andrei.hearthstoneassesment.presentation.components

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.andrei.hearthstoneassesment.R
import com.andrei.hearthstoneassesment.domain.model.HearthstoneCard
import com.andrei.hearthstoneassesment.presentation.ui.theme.HSMediumRed
import com.andrei.hearthstoneassesment.presentation.ui.theme.HSPaleYellow

@Composable
fun HearthstoneCardGridViewItem(
    hearthstoneCard: HearthstoneCard,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = HSPaleYellow)
            .selectable(selected = true, onClick = onClick)
            .height(240.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter(
                data = hearthstoneCard.img,
                builder = {
                    crossfade(true)
                    fallback(R.drawable.hearthstone_card_placeholder)
                    placeholder(R.drawable.hearthstone_card_placeholder)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun <ItemType> ScrollToTopGridView(
    modifier: Modifier = Modifier,
    listItems: List<ItemType>,
    lazyListState: LazyListState = rememberLazyListState(),
    withLoader: Boolean = false,
    items: @Composable (index: Int, item: ItemType) -> Unit
) {
    val configuration = LocalConfiguration.current
    val columns = if(configuration.orientation == ORIENTATION_LANDSCAPE) 4 else 2
    Box(modifier) {
//        val listState = rememberLazyListState()
//        val showButton = listState.firstVisibleItemIndex > 0
//        val scope = rememberCoroutineScope()

        LazyVerticalGrid(
//            state = listState,
            state = lazyListState,
            cells = GridCells.Fixed(columns),
            content = {
                itemsIndexed(listItems) { index, item ->
                    items(index, item)
                }
            },
            contentPadding = PaddingValues(bottom = 88.dp, start = 16.dp, end = 16.dp)
        )

//        AnimatedVisibility(
//            visible = showButton,
//            enter = fadeIn(),
//            exit = fadeOut(),
//            modifier = Modifier.align(Alignment.BottomEnd)
//        ) {
//            ScrollToTopButton(onClick = {
//                scope.launch { listState.animateScrollToItem(0) }
//            })
//        }
        if (withLoader) {
            Box(modifier = Modifier.align(alignment = Alignment.BottomCenter)) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(all = 24.dp), color = HSMediumRed
                )
            }
        }
    }
}