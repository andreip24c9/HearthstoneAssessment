package com.andrei.hearthstoneassessment.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.andrei.hearthstoneassessment.R

val fonts = FontFamily(
    Font(R.font.belwe_regular),
    Font(R.font.belwe_bold, weight = FontWeight.Bold),
    Font(R.font.belwe_bold, weight = FontWeight.ExtraBold),
    Font(R.font.belwe_light, weight = FontWeight.Light),
    Font(R.font.belwe_light, weight = FontWeight.ExtraLight),
    Font(R.font.belwe_light, weight = FontWeight.Thin),
    Font(R.font.belwe_medium, weight = FontWeight.SemiBold),
    Font(R.font.belwe_medium, weight = FontWeight.Medium),
    Font(R.font.belwe_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
)

val Typography = Typography(
    defaultFontFamily = fonts,

    body1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 17.sp
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    )
)