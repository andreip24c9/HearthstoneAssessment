package com.andrei.hearthstoneassesment.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andrei.hearthstoneassesment.presentation.ui.theme.*

@Composable
fun <ItemType> HorizontalList(
    listItems: List<ItemType>,
    modifier: Modifier = Modifier,
    items: @Composable (index: Int, item: ItemType) -> Unit,
) {
    Box(modifier) {
        val listState = rememberLazyListState()
        LazyRow(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
        ) {
            itemsIndexed(listItems) { index, item -> items(index, item) }
        }
    }
}

@Composable
fun CardSetChip(
    text: String,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(end = 16.dp)
            .selectable(selected = selected, onClick = onClick, enabled = !selected),
        elevation = 8.dp,
        color = if (selected) HSMediumRed else HSPaleYellow2,
        border = BorderStroke(if (selected) 2.dp else 1.dp, color = if (selected) HSGoldYellow2 else HSMediumBrown),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 6.dp),
            text = text,
            style = TextStyle(
                color = if (selected) HSGoldYellow2 else HSMediumBrown,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}