package com.andrei.hearthstoneassessment.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightThemeColors = lightColors(
    primary = HSMediumRed,
    primaryVariant = HSDarkRed,
    secondary = HSGoldYellow,
    secondaryVariant = HSGoldYellow2,
    surface = HSPaleYellow,
    onSurface = HSMediumBrown,
    onPrimary = HSGoldYellow2,
    onSecondary = HSMediumBrown,
    background = HSPaleYellow,
    onBackground = HSMediumBrown
)

private val DarkThemeColors = darkColors(
    primary = HSMediumRed,
    primaryVariant = HSDarkRed,
    onPrimary = HSGoldYellow2,
    secondary = HSMediumBrown2,
    onSecondary = HSGoldYellow,
    secondaryVariant = HSGoldYellow3,
    surface = HSDarkestBrown,
    onSurface = HSPaleYellow2,
    background = HSDarkestBrown,
    onBackground = HSPaleYellow2
)

@Composable
fun HSAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = Typography
    ) {
        content()
    }
}