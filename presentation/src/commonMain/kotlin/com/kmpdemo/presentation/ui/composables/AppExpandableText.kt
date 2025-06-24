package com.kmpdemo.presentation.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextGranularity.Companion.Character
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.kmpdemo.presentation.ui.theme.BlackColor

const val DEFAULT_MINIMUM_TEXT_LINE = 3
const val EXPAND_TEXT = "... Read More"
const val COLLAPSE_TEXT = " Read Less"

@Composable
fun AppExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    size: TextUnit = 14.sp,
    color: Color = Color.White,
    shadow: Shadow? = null,
    textAlign: TextAlign = TextAlign.Start,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    style: TextStyle = TextStyle.Default.copy(shadow = shadow),
    textDecoration: TextDecoration = TextDecoration.None,
    collapsedMaxLine: Int = DEFAULT_MINIMUM_TEXT_LINE,
    showMoreText: String = EXPAND_TEXT,
    showMoreStyle: SpanStyle = SpanStyle(fontWeight = FontWeight.W500, color = BlackColor),
    showLessText: String = COLLAPSE_TEXT,
    showLessStyle: SpanStyle = showMoreStyle,
) {

    var isExpanded by remember { mutableStateOf(false) }
    var hasTextOverflow by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableIntStateOf(0) }

    val annotatedString by remember(isExpanded, text, hasTextOverflow) {
        derivedStateOf {
            buildAnnotatedString {
                if(hasTextOverflow){
                    if (isExpanded) {
                        append(text)
                        withStyle(style = showLessStyle) { append(showLessText) }
                    } else {
                        val adjustText = text.substring(startIndex = 0, endIndex = lastCharIndex)
                            .dropLast(showMoreText.length)
                            .dropLastWhile { it.isWhitespace() || it == '.' }
                        append(adjustText)
                        withStyle(style = showMoreStyle) { append(showMoreText) }
                    }
                }else{
                    append(text)
                }
            }
        }
    }

    Box(modifier = Modifier
        .clickable(hasTextOverflow) {
            isExpanded = !isExpanded
        }
        .then(modifier)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .verticalScroll(rememberScrollState()),
            text = annotatedString,
            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
            onTextLayout = { textLayoutResult ->
                if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                    hasTextOverflow = true
                    lastCharIndex = textLayoutResult.getLineEnd( collapsedMaxLine - 1)
                }
            },
            style = style,
            textAlign = textAlign,
            lineHeight = lineHeight,
            fontSize = size,
            color = color,
            letterSpacing = letterSpacing,
            fontWeight = fontWeight,
            textDecoration = textDecoration,
        )
    }

}
