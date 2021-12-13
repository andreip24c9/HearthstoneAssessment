package com.andrei.hearthstoneassessment.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.andrei.hearthstoneassessment.R
import com.andrei.hearthstoneassessment.domain.model.HearthstoneCard
import com.andrei.hearthstoneassessment.presentation.theme.*
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun <ItemType> VerticalScrollToTopList(
    modifier: Modifier = Modifier,
    listItems: List<ItemType>,
    lazyListState: LazyListState = rememberLazyListState(),
    withLoader: Boolean = false,
    items: @Composable (index: Int, item: ItemType) -> Unit
) {
    Box(modifier) {
//        val listState = rememberLazyListState()
        val showButton = lazyListState.firstVisibleItemIndex > 0
        val scope = rememberCoroutineScope()

        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(bottom = (88.dp))
        ) {
            itemsIndexed(listItems) { index, item ->
                items(index, item)
                if (index == listItems.size - 1 && withLoader) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(top = 24.dp), color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = showButton,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            ScrollToTopButton(onClick = {
                scope.launch { lazyListState.animateScrollToItem(0) }
            })
        }
    }
}

@Composable
fun HearthstoneCardListViewItem(
    hearthstoneCard: HearthstoneCard,
    onClick: () -> Unit
) {
    Column(Modifier.selectable(selected = true, onClick = onClick)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
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
                    .width(70.dp)
                    .height(108.dp)
            )

            Column(modifier = Modifier.padding(start = 20.dp, top = 14.dp)) {
                Text(
                    text = hearthstoneCard.name,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = hearthstoneCard.cardSet,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    hearthstoneCard.attack?.let {
                        AttachHealthDurabilityChip(AttackColor, it)
                    }
                    hearthstoneCard.health?.let {
                        AttachHealthDurabilityChip(HealthColor, it)
                    }
                    hearthstoneCard.durability?.let {
                        AttachHealthDurabilityChip(DurabilityColor, it)
                    }
                }
            }
        }
    }
}

@Composable
fun AttachHealthDurabilityChip(color: Color, modifier: Int) {
    Box(
        modifier = Modifier
            .background(
                color = color,
                shape = RoundedCornerShape(24.dp)
            )
            .border(1.dp, color = MaterialTheme.colors.onSurface, shape = RoundedCornerShape(24.dp))
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 1.dp),
            text = modifier.toString(),
            color = White,
            style = MaterialTheme.typography.h4
        )
    }
}