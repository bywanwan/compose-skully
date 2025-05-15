package com.wanwan.sandbox.navigator.ui.model

interface TopBarConfig
interface BottomBarConfig
interface FabConfig

/**
 * Represents the layout configuration for a UI screen.
 *
 * @property content The main content to be displayed.
 * @property topBar Optional configuration for the top app bar.  If `null`, no top bar will be displayed.
 * @property bottomBar Optional configuration for the bottom navigation bar. If `null`, no bottom bar will be displayed.
 * @property fab Optional configuration for the floating action button. If `null`, no FAB will be displayed.
 */
data class UiLayout(
    val content: UiContent,
    val topBar: TopBarConfig? = null,
    val bottomBar: BottomBarConfig? = null,
    val fab: FabConfig? = null,
)
