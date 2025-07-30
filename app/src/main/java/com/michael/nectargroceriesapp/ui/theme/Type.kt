package com.michael.nectargroceriesapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.michael.nectargroceriesapp.R

val Gilory = FontFamily(
    Font(R.font.gilroy_regular, FontWeight.Normal),
    Font(R.font.gilroy_medium, FontWeight.Medium),
    Font(R.font.gilroy_semibold, FontWeight.SemiBold),
    Font(R.font.gilroy_bold, FontWeight.Bold)
)
// Set of Material typography styles to start with
val Typography = Typography(
    displayMedium = TextStyle(
        fontFamily = Gilory,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    displaySmall = TextStyle(
        fontFamily = Gilory,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    titleLarge = TextStyle(
        fontFamily = Gilory,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    titleMedium = TextStyle(
        fontFamily = Gilory,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    titleSmall = TextStyle(
        fontFamily = Gilory,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = Gilory,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.1.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = Gilory,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),

    bodySmall = TextStyle(
        fontFamily = Gilory,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 13.sp,
        letterSpacing = 0.sp
    )
)