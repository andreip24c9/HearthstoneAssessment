package com.andrei.hearthstoneassesment.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andrei.hearthstoneassesment.R
import com.andrei.hearthstoneassesment.presentation.ui.theme.*

@Composable
fun ScrollToTopButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = HSGoldYellow,
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, color = HSDarkBrown, shape = CircleShape)
    ) {
        Icon(Icons.Filled.KeyboardArrowUp, null, tint = HSDarkBrown)
    }
}

@Preview
@Composable
fun Toolbar(
    title: String = "",
    onNavigationItemClick: (() -> Unit)? = null,
    navigationIcon: ImageVector? = null,
    contentColor: Color = HSGoldYellow2,
    backgroundColor: Color = HSMediumRed,
    elevation: Dp = 4.dp
) {
    if (onNavigationItemClick != null && navigationIcon != null) {
        TopAppBar(
            title = { Text(text = title) },
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            navigationIcon = {
                IconButton(onClick = onNavigationItemClick) {
                    Icon(
                        navigationIcon,
                        contentDescription = null
                    )
                }
            },
            elevation = elevation
        )
    } else {
        TopAppBar(
            title = { Text(text = title) },
            backgroundColor = HSMediumRed,
            contentColor = HSGoldYellow2,
            elevation = elevation
        )
    }
}

@Composable
fun ImageToolbar(
    imageRes: Int,
    secondMenuIconRes: Int,
    onInfoClicked: () -> Unit,
    onSecondMenuItemClicked: () -> Unit,
    elevation: Dp = 4.dp
) {
    Surface(
        elevation = elevation,
        color = HSMediumRed,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {

        Row(
            modifier = Modifier
                .width(30.dp)
                .height(10.dp)
                .background(color = HSMediumRed)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = HSGoldYellow2),
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .selectable(selected = false, onClick = onInfoClicked)
            )

            Image(
                painter = painterResource(id = imageRes),
                modifier = Modifier.wrapContentWidth(),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = HSGoldYellow2)
            )

            Image(
                painter = painterResource(id = secondMenuIconRes),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = HSGoldYellow2),
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .selectable(selected = false, onClick = onSecondMenuItemClicked)
            )
        }
    }
}

@Preview
@Composable
fun LoadingErrorView(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    error: ErrorMessage? = null,
    retryButton: RetryButton? = null
) {
    Box(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = HSMediumRed)
            } else {
                error?.apply {
                    titleRes?.let {
                        Text(
                            modifier = Modifier
                                .padding(start = 32.dp, end = 32.dp),
                            textAlign = TextAlign.Center,
                            text = stringResource(id = it),
                            color = HSDarkBrown,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    if (titleRes != null && bodyRes != null) {
                        Spacer(modifier = Modifier.padding(top = 8.dp))
                    }
                    bodyRes?.let {
                        Text(
                            modifier = Modifier.padding(start = 32.dp, end = 32.dp),
                            text = stringResource(id = it),
                            color = HSMediumBrown,
                            fontSize = 17.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    retryButton?.apply {
                        Spacer(modifier = Modifier.padding(top = 48.dp))
                        Button(
                            onClick = onClick,
                            border = BorderStroke(1.dp, color = HSDarkBrown),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = HSGoldYellow2)
                        ) {
                            icon?.let {
                                Icon(
                                    painter = painterResource(id = it),
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 16.dp),
                                    tint = HSDarkBrown
                                )
                            }
                            textRes?.let {
                                Text(
                                    modifier = Modifier.padding(end = if (icon != null) 8.dp else 0.dp),
                                    text = stringResource(id = it),
                                    color = HSDarkBrown,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

class ErrorMessage(val titleRes: Int? = null, val bodyRes: Int? = null)
class RetryButton(val icon: Int? = null, val textRes: Int?, val onClick: () -> Unit)