package com.arabam.android.assignment.core.common.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.arabam.android.assignment.core.common.R

val Monserrat = FontFamily(
    Font(R.font.montserrat_black, weight = FontWeight.Black),
    Font(R.font.montserrat_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),
    Font(R.font.montserrat_bold, weight = FontWeight.Bold),
    Font(R.font.montserrat_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.montserrat_italic, style = FontStyle.Italic),
    Font(R.font.montserrat_light, weight = FontWeight.Light),
    Font(R.font.montserrat_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(R.font.montserrat_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.montserrat_regular, weight = FontWeight.Normal),
    Font(R.font.montserrat_semi_bold, weight = FontWeight.SemiBold),
    Font(
        R.font.montserrat_semi_bold_italic,
        weight = FontWeight.SemiBold,
        style = FontStyle.Italic
    ),
    Font(R.font.montserrat_thin, weight = FontWeight.Thin),
    Font(R.font.montserrat_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic),
)

val ArabamTypography = Typography(
    h1 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 54.sp
    ),
    h2 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 42.sp
    ),
    h3 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp
    ),
    h4 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp
    ),
    h5 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp
    ),
    h6 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    body2 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Thin,
        fontSize = 14.sp
    )
)