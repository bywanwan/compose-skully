//package com.wanwan.sandbox
//
//import androidx.compose.animation.animateColor
//import androidx.compose.animation.core.LinearEasing
//import androidx.compose.animation.core.RepeatMode
//import androidx.compose.animation.core.animateFloat
//import androidx.compose.animation.core.infiniteRepeatable
//import androidx.compose.animation.core.rememberInfiniteTransition
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.composed
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.drawWithContent
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.graphics.StrokeCap
//import androidx.compose.ui.graphics.painter.ColorPainter
//import androidx.compose.ui.layout.SubcomposeLayout
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.tooling.preview.PreviewParameter
//import androidx.compose.ui.tooling.preview.PreviewParameterProvider
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//
//sealed class UiSkull<out T> {
//    data class Data<T>(val value: T) : UiSkull<T>()
//    data object Skeleton : UiSkull<Nothing>()
//}
//
//fun <T> UiSkull<T>.orDefault(defaultValue: T): T = (this as? UiSkull.Data<T>)?.value ?: defaultValue
//fun <T> UiSkull<T>.orNull(defaultValue: T): T? = (this as? UiSkull.Data<T>)?.value
//val <T> UiSkull<T>.isSkeleton: Boolean
//    get() = this is UiSkull.Skeleton
//
//
//interface UiHeaderPlaceHolder {
//    val title: UiPlaceholder<Any>
//    val description: UiPlaceholder<Any>
//    val imageResId: UiPlaceholder<Any>
//}
//
//sealed class UiHeaderPlaceholder {
//    abstract val title: UiPlaceholder<Any>
//    abstract val description: UiPlaceholder<Any>?
//    abstract val imageResId: UiPlaceholder<Any>
//
//    data class Data(
//        val backgroundColor: Color,
//        override val title: UiPlaceholder<String>,
//        override val description: UiPlaceholder<String>?,
//        override val imageResId: UiPlaceholder<Int>,
//        val onClick: () -> Unit,
//    ) : UiHeaderPlaceholder()
//
//    data class Skeleton(
//        override val title: UiPlaceholder.Skeleton,
//        override val description: UiPlaceholder.Skeleton,
//        override val imageResId: UiPlaceholder.Skeleton,
//    ) : UiHeaderPlaceholder()
//}
//
//
//data class UiHeader(
//    val backgroundColor: Color,
//    override val title: UiPlaceholder<String>,
//    override val description: UiPlaceholder<String?>,
//    override val imageResId: UiPlaceholder<Int>,
//    val onClick: () -> Unit,
//) : UiHeaderPlaceHolder
//
//
//@Composable
//fun <T> UiSkull<T>.Skull(
//    skeletonSlot: (@Composable () -> Unit)? = null,
//    contentSlot: (@Composable (T) -> Unit)
//) {
//    when (this) {
//        is UiSkull.Data<T> -> contentSlot(this.value)
//        is UiSkull.Skeleton -> ShimmerEffect {
//            skeletonSlot?.invoke()
//        }
//    }
//}
//
//
//interface ShimmerEffectScope {
//    val animateColor: Color
//}
//
//fun Modifier.skeletonEffect(
//    visible: Boolean = true,
//    color: Color = Color.Gray,
//    shape: Shape = RoundedCornerShape(4.dp),
//    fraction: Float? = null,
//): Modifier = composed {
//    if (!visible) return@composed this
//
//    val infiniteTransition = rememberInfiniteTransition(label = "")
//    val alpha by infiniteTransition.animateFloat(
//        initialValue = 0.7f,
//        targetValue = 0.2f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 500, easing = LinearEasing),
//            repeatMode = RepeatMode.Reverse
//        ),
//        label = ""
//    )
//
//    Modifier
//        .alpha(alpha)
//        .background(color = color)
//        .clip(shape)
//        .composed {
//            fraction?.let { fillMaxWidth(fraction) } ?: this
//        }
//}
//
//@Composable
//fun ShimmerEffect(
//    content: @Composable ShimmerEffectScope.() -> Unit
//) {
//    val infiniteTransition = rememberInfiniteTransition(label = "")
//
//    val scope = object : ShimmerEffectScope {
//        override val animateColor by infiniteTransition.animateColor(
//            initialValue = Color.LightGray,
//            targetValue = Color.Gray,
//            animationSpec = infiniteRepeatable(
//                animation = tween(
//                    durationMillis = 500,
//                    easing = LinearEasing,
//                ),
//                repeatMode = RepeatMode.Reverse,
//            ),
//            label = "",
//        )
//    }.content()
//}
//
//@Composable
//private fun <T> UiSkull.Data<T>.WithSkeleton(
//    modifier: Modifier = Modifier,
//    skeletonSlot: (@Composable (height: Int, width: Int) -> Unit)? = null,
//    contentSlot: (@Composable (T) -> Unit)
//) {
//    SubcomposeLayout(modifier = modifier) { constraints ->
//        val skeletonPlaceable = subcompose("content", {
//            contentSlot(this@WithSkeleton.value)
//        }).map { it.measure(constraints) }.let {
//            check(it.size <= 1) {
//                "contentSlot expects only 1 child, but got ${it.size}"
//            }
//
//            it.firstOrNull()
//        }?.let { placeable ->
//            subcompose("skeleton", {
//                skeletonSlot?.invoke(placeable.height, placeable.width)
//            }).map { it.measure(constraints) }.let {
//                check(it.size <= 1) {
//                    "skeletonSlot expects only 1 child, but got ${it.size}"
//                }
//
//                it.firstOrNull()
//            }
//        }
//
//        layout(
//            width = skeletonPlaceable?.width ?: 0,
//            height = skeletonPlaceable?.height ?: 0
//        ) {
//            skeletonPlaceable?.placeRelative(0, 0)
//        }
//    }
//}
//
//private class UiHeaderProvider :
//    PreviewParameterProvider<UiHeader?> {
//
//    override val values = sequenceOf<UiHeader?>(
//        null,
//        UiHeader(
//            title = UiPlaceholder.Skeleton,
//            description = UiPlaceholder.Skeleton,
//            imageResId = UiPlaceholder.Skeleton,
//        ),
//        UiHeader(
//            title = UiPlaceholder.Data("Hello !"),
//            description = UiPlaceholder.Data("Little padawan Little padawan Little padawan Little padawan Little padawan Little padawan Little padawan"),
//            imageResId = UiPlaceholder.Skeleton,
//        ),
//        UiHeader(
//            title = UiPlaceholder.Data("Hello !"),
//            description = UiPlaceholder.Data("Little padawan Little padawan Little padawan Little padawan Little padawan Little padawan Little padawan"),
//            imageResId = UiPlaceholder.Data(R.drawable.ic_launcher_foreground),
//        ),
//    )
//}
//
//@Preview(
//    showBackground = true,
//    group = "Sandbox",
//)
//
//@Composable
//fun PreviewPostDetailsScreenData(
//    @PreviewParameter(UiHeaderProvider::class) uiHeader: UiHeader?,
//) {
//    Box(
//        modifier = Modifier
//            .height(200.dp)
//            .width(400.dp)
//            .padding(24.dp),
//    ) {
//        uiHeader?.let { (title, description, imageResId) ->
//            Row(verticalAlignment = Alignment.Top) {
//                imageResId.Placeholder(
//
//                    contentSlot = { modifier, imageResId ->
//
//                    },
//                    skeletonSlot = {
//
//                    }
//                )
//                Image(
//                    modifier = Modifier
//                        .size(80.dp)
//                        .padding(8.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                        .background(Color.Green)
//                        .drawWithContent {
//                            drawContent()
//                            // Ligne 1
//                            drawLine(
//                                color = Color.Magenta,
//                                start = Offset(20f, 40f),
//                                end = Offset(size.width - 20f, 40f),
//                                strokeWidth = 20f,
//                                cap = StrokeCap.Round
//                            )
//
//                            // Ligne 2
//                            drawLine(
//                                color = Color.Cyan,
//                                start = Offset(20f, 100f),
//                                end = Offset(size.width - 20f, 100f),
//                                strokeWidth = 20f,
//                                cap = StrokeCap.Round
//                            )
//                        }
//                        .skeletonEffect(
//                            visible = imageResId.isSkeleton,
//                            color = Color.Green,
//                            shape = RoundedCornerShape(8.dp)
//                        ),
//                    painter = when (imageResId) {
//                        is UiSkull.Data -> painterResource(imageResId.value)
//                        is UiSkull.Skeleton -> ColorPainter(Color.Transparent)
//                    },
//                    contentDescription = null,
//                )
//                Column {
//                    title?.let {
//                        Text(
//                            style = TextStyle(lineHeight = 16.sp),
//                            fontSize = 18.sp,
//                            maxLines = 1,
//                            modifier = Modifier
//                                .padding(vertical = 8.dp)
//                                .skeletonEffect(visible = title.isSkeleton, fraction = 0.6f),
//                            text = title.orDefault("")
//                        )
//                    }
//
//                    description?.let {
//                        Text(
//                            style = TextStyle(lineHeight = 16.sp),
//                            fontSize = 12.sp,
//                            maxLines = 2,
//                            overflow = TextOverflow.Ellipsis,
//                            modifier = Modifier
//                                .padding(vertical = 8.dp)
//                                .skeletonEffect(visible = description.isSkeleton, fraction = 0.8f),
//                            text = description.orDefault("")
//                        )
//                    }
//                }
//            }
//        }
//    }
//}