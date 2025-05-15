package com.wanwan.sandbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wanwan.sandbox.skully.SkullyEffect
import com.wanwan.sandbox.skully.SkullyFactory
import com.wanwan.sandbox.skully.skullify

sealed class ContentState<out T> {
    data class Content<T>(val value: T) : ContentState<T>()
    object Loading : ContentState<Nothing>()
}

class UiHeaderPreviewProvider : PreviewParameterProvider<UiHeader> {
    override val values = sequenceOf(
        UiHeader(
            title = ContentState.Content("Ut provincias metuens pericula pro"),
            description = ContentState.Content("De ne De ne utroque iudicavit rem vestrum ais me ut quidem laudantur laudantur enim"),
            imageResId = ContentState.Content(R.drawable.ic_launcher_foreground),
        ),
        UiHeader(
            title = ContentState.Loading,
            description = ContentState.Loading,
            imageResId = ContentState.Loading,
        ),
    )
}

data class UiHeader(
    val title: ContentState<String>,
    val description: ContentState<String>,
    val imageResId: ContentState<Int>,
)

fun <T> ContentState<T>.getOrNull(): T? = (this as? ContentState.Content<T>)?.value
fun <T> ContentState<T>.getOrDefault(defaultValue: T): T =
    (this as? ContentState.Content<T>)?.value ?: defaultValue

val <T> ContentState<T>.isLoading: Boolean
    get() = this is ContentState.Loading

@Preview(
    showBackground = true,
    group = "Sandbox",
)
@Composable
fun PreviewSkeleton(
    @PreviewParameter(UiHeaderPreviewProvider::class) uiHeader: UiHeader
) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(400.dp)
            .padding(24.dp),
    ) {
        Row(verticalAlignment = Alignment.Top) {

            uiHeader.imageResId.let { imageResId ->
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .skullify(
                            visible = imageResId.isLoading,
                            effect = SkullyEffect.Shimmer()
                        ),
                    painter = imageResId.getOrNull()?.let {
                        painterResource(it)
                    } ?: ColorPainter(Color.Transparent),
                    contentDescription = null,
                )
            }

            Column {
                uiHeader.title.let { title ->
                    val fontSize = 20.sp
                    Text(
                        text = title.getOrDefault(""),
                        fontSize = fontSize,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .skullify(
                                visible = title.isLoading,
                                effect = SkullyEffect.Shimmer(),
                                factory = SkullyFactory.Inline(
                                    lineHeight = fontSize,
                                )
                            ),
                    )
                }
                uiHeader.description.let { description ->
                    val fontSize = 16.sp
                    val maxLines = 3
                    Text(
                        text = description.getOrDefault(""),
                        fontSize = fontSize,
                        maxLines = maxLines,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                            .height(200.dp)
                            .skullify(
                                visible = description.isLoading,
                                effect = SkullyEffect.Shimmer(),
                                factory = SkullyFactory.Inline(
                                    lineHeight = fontSize,
                                ) {
                                    line(widthFraction = 1f)
                                    line(widthFraction = 0.6f)
                                }
                            ),
                    )
                }
            }
        }
    }
}