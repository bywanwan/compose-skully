package com.wanwan.sandbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface ScreenScope
interface ScreenArgs

sealed class Screen {
//    abstract val route: String

//    /**
//     * Renders the screen's UI.
//     *
//     * @param scope The screen's scope, providing access to shared state.  May be null if no scope is needed.
//     * @param arguments The arguments passed to the screen.
//     */
//    @Composable
//    abstract fun Render(
//        scope: S,
//        arguments: A,
//    )

    abstract class Default : Screen() {
        @Composable
        abstract fun Render()
    }

    abstract class All<S : ScreenScope, A : ScreenArgs> : Screen() {
        @Composable
        abstract fun Render(
            scope: S,
            arguments: A,
        )
    }
}

//
//abstract class Screen<S : Screen.Scope, A : Screen.Args> {
//
//    interface Scope
////    interface Args
//    sealed interface Args {
//        data object NoArgs : Args
//
//        // Example: Arguments for a product details screen
//        data class With(val list: List<ScreenArgument>) : Args
//    }
//
//    object NoScope: Scope
////    object NoArgs: Args
//
//
//    /**
//     * Renders the screen's UI.
//     *
//     * @param scope The screen's scope, providing access to shared state.  May be null if no scope is needed.
//     * @param arguments The arguments passed to the screen.
//     */    @Composable
//    abstract fun Render(
//        scope: S,
//        arguments: A,
//    )
//}
//

/**
 * Abstract base class for defining reusable UI components.
 *
 * Components are modular pieces of UI that can be composed together to build more complex screens or layouts.
 *
 * @param S The type of scope associated with the component, providing access to shared state or context.
 * @param A The type of arguments passed to the component.
 */
abstract class Component<S : Component.Scope, A : Component.Args> {

    interface Scope
    interface Args

    object NoScope : Scope
    object NoArgs : Args

    @Composable
    abstract fun Render(
        scope: S,
        arguments: A,
    )
}


abstract class Widget<S : Widget.Scope, A : Widget.Args> {

    interface Scope
    interface Args

    object NoScope : Scope
    object NoArgs : Args

    @Composable
    abstract fun Render(
        scope: S,
        arguments: A,
    )
}


interface HomeScreenScope: ScreenScope
interface HomeScreenArgs : ScreenArgs {
    val name: String
}

object HomeScreen2 : Screen.All<HomeScreenScope, HomeScreenArgs>() {
    @Composable
    override fun Render(scope: HomeScreenScope, arguments: HomeScreenArgs) {
        TODO("Not yet implemented")
    }

}

object HomeScreen : Screen.Default() {

    //    interface Scope: ScreenScope
//    interface Arguments : ScreenArgs {
//        val name: String
//    }
//
//    @Composable
//    override fun Render(scope: NoScope, arguments: Args.NoArgs) {
////        @Composable
////        fun HomeScreen(arguments: MainScreenNav.Home.Arguments, modifier: Modifier = Modifier) {
//        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
////            Text("HomeScreen for ${arguments.name}")
//
//            Content()
//        }
//    }
    @Composable
    override fun Render() {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
//            Text("HomeScreen for ${arguments.name}")

//            Content()
        }
    }

}

//@Composable
//fun HomeScreen.Render() = HomeScreen.Render(Screen.NoScope, Args.NoArgs)
