//package com.wanwan.sandbox
//
//import androidx.compose.foundation.lazy.LazyItemScope
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//
//
//interface SkeletonScope
//
//inline fun <T> SkeletonScope.lines(
//    items: List<T>,
//    noinline key: ((item: T) -> Any)? = null,
//    noinline contentType: (item: T) -> Any? = { null },
//    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
//) {
//    // itemContent(items[it])
//}
//
//
//sealed class UiPlaceholder<out T> {
//    data class Data<T>(val value: T) : UiPlaceholder<T>()
////    data object Loading : UiPlaceholder<Nothing>()
//    data object Skeleton : UiPlaceholder<Nothing>()
//}
//
//@Composable
//fun <T> UiPlaceholder<T>.Render(defaultValue: T): T = (this as? UiSkull.Data<T>)?.value ?: defaultValue
//fun <T> UiPlaceholder<T>.orNull(defaultValue: T): T? = (this as? UiSkull.Data<T>)?.value
//val <T> UiPlaceholder<T>.isSkeleton: Boolean
//    get() = this is UiSkull.Skeleton
//
//
//@Composable
//fun <T> UiPlaceholder<T>.Placeholder(
//    modifier: Modifier = Modifier,
//    skeletonSlot: @Composable SkeletonScope.() -> Unit,
//    contentSlot: @Composable (Modifier, T) -> Unit,
//) {
//    when (this) {
//        is UiPlaceholder.Skeleton -> {
//            skeletonSlot()
//        }
//        is UiPlaceholder.Data -> {
//
//        }
//    }
//    println()
//}
