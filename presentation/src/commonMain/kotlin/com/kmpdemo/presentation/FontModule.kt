package com.kmpdemo.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.kmpdemo.resources.AppFonts
import org.jetbrains.compose.resources.Font

@Composable
fun customFontFamily(): FontFamily {
    return FontFamily(
        Font(AppFonts.OPENSANS_REGULAR, weight = FontWeight.Normal),
        Font(AppFonts.OPENSANS_SEMI_BOLD, weight = FontWeight.Medium),
        Font(AppFonts.OPENSANS_BOLD, weight = FontWeight.Bold),
    )
}
