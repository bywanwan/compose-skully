package com.wanwan.sandbox.skully

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

sealed class SkullyFactory {
    data object Outline : SkullyFactory()
    data class Inline(
        val lineHeight: TextUnit,
        val strokeWidth: Dp = lineHeight.value.dp * 0.8f,
        val build: SkullyInlineBuilder.() -> Unit = { line(widthFraction = 1f)}
    ) : SkullyFactory()
}
