package com.wanwan.sandbox.skully

import androidx.compose.ui.unit.Dp

internal sealed class AnimatedEffect {
    data class Fade(val alpha: Float) : AnimatedEffect()
    data class Shimmer(val offsetX: Float, val shimmerWidth: Dp) : AnimatedEffect()
}