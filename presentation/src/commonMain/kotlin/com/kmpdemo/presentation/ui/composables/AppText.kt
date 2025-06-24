package com.kmpdemo.presentation.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    maxLines: Int = Int.MAX_VALUE,
    shadow: Shadow? = null,
    style: TextStyle = TextStyle.Default.copy(shadow = shadow)
    ) {
    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines, overflow = TextOverflow.Ellipsis,
        style = style
    )
}

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    maxLines: Int = Int.MAX_VALUE,
    shadow: Shadow? = null,
    style: TextStyle = TextStyle.Default.copy(shadow = shadow)
) {
    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines, overflow = TextOverflow.Ellipsis,
        style = style
    )
}

