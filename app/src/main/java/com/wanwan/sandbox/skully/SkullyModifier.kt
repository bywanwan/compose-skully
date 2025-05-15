package com.wanwan.sandbox.skully

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

fun Modifier.skullify(
    visible: Boolean = true,
    color: Color = Color.LightGray,
    effect: SkullyEffect,
    shape: Shape = RoundedCornerShape(4.dp),
    factory: SkullyFactory = SkullyFactory.Outline
): Modifier = this.composed {
    if (visible.not()) {
        return@composed this
    }
    clickable(false) { }

    var layoutSize by remember { mutableStateOf(IntSize(100, 100)) }
    onGloballyPositioned {
//        layoutSize = it.size
    }
    val animatedEffect = animateEffect(layoutSize, effect)

    return@composed when (factory) {
        is SkullyFactory.Outline -> drawWithContent {
            drawContent()
            drawSkullyEffect(
                size = size,
                animatedEffect = animatedEffect.value,
                color = color,
                shape = shape,
            )
        }

        is SkullyFactory.Inline -> drawWithContent {
            drawContent()

            val lines = mutableListOf<Size>()
            val builder = object : SkullyInlineBuilder() {
                override fun line(
                    widthFraction: Float
                ) {
                    lines.add(
                        Size(
                            height = factory.strokeWidth.toPx(),
                            width = (size.width * widthFraction).coerceIn(
                                0f,
                                size.width
                            )
                        )
                    )
                }
            }

            factory.build(builder)

            lines.forEachIndexed { index, line ->
                val lineHeight = factory.lineHeight.toPx()
                val spacing = lineHeight * 0.2f

                val top = index * (lineHeight + spacing) + (lineHeight - line.height)
                translate(0f, top) {
                    drawSkullyEffect(
                        size = line,
                        animatedEffect = animatedEffect.value,
                        color = color,
                        shape = shape,
                    )
                }
            }
        }
    }
}

private fun DrawScope.drawSkullyEffect(
    size: Size,
    animatedEffect: AnimatedEffect,
    color: Color,
    shape: Shape,
) {
    when (animatedEffect) {
        is AnimatedEffect.Fade -> {
            fade(
                size = size,
                animatedEffect = animatedEffect,
                color = color,
                shape = shape
            )
        }

        is AnimatedEffect.Shimmer -> {
            shimmer(
                size = size,
                animatedEffect = animatedEffect,
                color = color,
                shape = shape
            )
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
private fun animateEffect(
    layoutSize: IntSize,
    effect: SkullyEffect,
): State<AnimatedEffect> {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    return when (effect) {
        is SkullyEffect.Fade -> {
            val alpha by infiniteTransition.animateFloat(
                initialValue = effect.startValue,
                targetValue = effect.endValue,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 500,
                        easing = effect.easing
                    ),
                    repeatMode = effect.repeatMode
                ),
                label = ""
            )

            derivedStateOf { AnimatedEffect.Fade(alpha = alpha) }
        }


        is SkullyEffect.Shimmer -> {

            val offsetX by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = layoutSize.width.dp.value,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = effect.durationMillis,
                        easing = effect.easing
                    )
                )
            )

            derivedStateOf { AnimatedEffect.Shimmer(offsetX = offsetX, shimmerWidth = effect.shimmerWidth) }
        }
    }
}

private fun DrawScope.shimmer(
    size: Size,
    animatedEffect: AnimatedEffect.Shimmer,
    color: Color,
    shape: Shape,
) {
    val outline = shape.createOutline(
        size = size,
        layoutDirection = layoutDirection,
        density = Density(density)
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            color.copy(alpha = 0.7f),
            color.copy(alpha = 0.2f),
            color.copy(alpha = 0.7f)
        ),
        start = Offset(animatedEffect.offsetX + animatedEffect.shimmerWidth.toPx(), 0f),
        end = Offset(animatedEffect.offsetX, 0f)
    )

    drawOutline(
        outline = outline,
        brush = brush,
        blendMode = BlendMode.SrcOver,
    )
}

private fun DrawScope.fade(
    size: Size,
    animatedEffect: AnimatedEffect.Fade,
    color: Color,
    shape: Shape,
) {
    val outline = shape.createOutline(
        size = size,
        layoutDirection = layoutDirection,
        density = Density(density)
    )
    drawOutline(outline = outline, color = color.copy(alpha = animatedEffect.alpha))
}