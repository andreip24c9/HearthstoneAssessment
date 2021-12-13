package com.andrei.hearthstoneassessment.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.andrei.hearthstoneassessment.R
import com.andrei.hearthstoneassessment.domain.model.HearthstoneCard
import com.andrei.hearthstoneassessment.domain.util.sdk28AndUp
import com.andrei.hearthstoneassessment.presentation.theme.Black
import com.andrei.hearthstoneassessment.presentation.theme.HSPaleYellow2
import com.andrei.hearthstoneassessment.presentation.theme.White
import com.andrei.hearthstoneassessment.presentation.theme.WhiteTranslucent


@Composable
fun PageDetailView(
    modifier: Modifier = Modifier,
    card: HearthstoneCard
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(color = Black)
                    .verticalScroll(rememberScrollState())
            ) {
                val context = LocalContext.current
                val imageLoader = ImageLoader.Builder(context)
                    .componentRegistry {
                        sdk28AndUp { add(ImageDecoderDecoder(context)) }
                            ?: run { add(GifDecoder()) }
                    }.build()

                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = rememberImagePainter(
                        data = card.imgGold,
                        imageLoader = imageLoader,
                        builder = {
                            crossfade(true)
                            fallback(R.drawable.hearthstone_card_placeholder)
                            placeholder(R.drawable.hearthstone_card_placeholder)
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    contentDescription = null
                )

                Column(
                    modifier = Modifier.padding(
                        top = 16.dp,
                        start = 32.dp,
                        end = 32.dp
                    )
                ) {
                    Text(
                        text = card.name,
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    card.flavor?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            color = WhiteTranslucent,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light,
                            fontStyle = FontStyle.Italic
                        )
                    }

                    card.text?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            color = White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                    }

                    val bulletItems = mutableListOf<BulletListItem>()
                    card.type?.let { bulletItems.add(BulletListItem(R.string.type_label, it)) }
                    card.rarity?.let { bulletItems.add(BulletListItem(R.string.rarity_label, it)) }
                    bulletItems.add(BulletListItem(R.string.card_set_label, card.cardSet))
                    bulletItems.add(BulletListItem(R.string.player_class_label, card.playerClass))
                    card.cost?.let {
                        bulletItems.add(BulletListItem(R.string.cost_label, it.toString()))
                    }
                    card.collectible?.let {
                        bulletItems.add(BulletListItem(R.string.collectible_label, "✓"))
                    }
                    BulletPointList(
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .wrapContentHeight(),
                        list = bulletItems
                    )
                }
            }
        }
    }
}


class BulletListItem(val typeRes: Int, val value: String)

@Composable
fun BulletPointList(
    modifier: Modifier = Modifier,
    list: List<BulletListItem>,
) {
    Column(modifier = modifier) {
        list.forEach { listItem ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "• ${stringResource(id = listItem.typeRes)}:",
                    color = HSPaleYellow2,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = listItem.value,
                    modifier = Modifier.padding(start = 4.dp),
                    color = White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}