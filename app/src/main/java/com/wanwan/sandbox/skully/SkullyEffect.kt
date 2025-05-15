package com.wanwan.sandbox.skully

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class SkullyEffect {

    data class Fade(
        val startValue: Float = 0.7f,
        val endValue: Float = 0.2f,
        val durationMillis: Int = 500,
        val easing: Easing = LinearEasing,
        val repeatMode: RepeatMode = RepeatMode.Reverse,
    ) : SkullyEffect()

    data class Shimmer(
        val shimmerWidth: Dp = 100.dp,
        val durationMillis: Int = 1200,
        val easing: Easing = LinearEasing,
        val repeatMode: RepeatMode = RepeatMode.Restart,
    ) : SkullyEffect()
}