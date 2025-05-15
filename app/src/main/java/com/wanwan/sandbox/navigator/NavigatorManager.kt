//package com.wanwan.sandbox.navigator
//
//import com.wanwan.sandbox.navigator.ui.model.UiEffect
//import com.wanwan.sandbox.navigator.ui.model.UiScreen
//import kotlinx.coroutines.flow.MutableSharedFlow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.SharedFlow
//import kotlinx.coroutines.flow.StateFlow
//
//
//interface Destination {
//    val route: String
//    val args: Map<String, Any?>
//    val presentation: PresentationMode
//}
//
//
//sealed interface MainDestination: Destination
//sealed interface SubDestination: Destination
//
//sealed interface AuthDestination: Destination, MainDestination
//
//data object AuthSelectProviderDestination: AuthDestination {
//    override val route: String
//        get() = TODO("Not yet implemented")
//    override val args: Map<String, Any?>
//        get() = TODO("Not yet implemented")
//    override val presentation: PresentationMode
//        get() = TODO("Not yet implemented")
//}
//
//data object AuthGoogleDestination: AuthDestination
//data object AuthMsDestination: AuthDestination
//data object AuthOktaDestination: AuthDestination
//data object AuthEmailDestination: AuthDestination
//
//fun sandbox() {
//
//    val destination: AuthDestination = AuthSelectProviderDestination
//
//    when (destination) {
//        AuthEmailDestination -> TODO()
//        AuthGoogleDestination -> TODO()
//        AuthMsDestination -> TODO()
//        AuthOktaDestination -> TODO()
//        AuthSelectProviderDestination -> TODO()
//    }
//}
//
//
//
//enum class PresentationMode {
//    FullScreen,
//    Sheet,
//    Dialog
//}
//
//class NavigatorManager {
//    private val _navigation = MutableSharedFlow<Destination>()
//    val navigation: SharedFlow<Destination> = _navigation
//
//    private var current: MainDestination = AuthSelectProviderDestination
//    fun currentDestination(): Destination = current
//
//    suspend fun navigateTo(target: Destination) {
//    }
//}
//
//
//class NavigationStore {
//    val screenState = MutableStateFlow<UiScreen>(UiScreen.default)
//    val effectState = MutableSharedFlow<UiEffect>()
//}
//
//class NavigationViewModel {
//    private val store: NavigationStore = NavigationStore()
//
//    val screenState: StateFlow<UiScreen> = store.screenState
//    val effectState: SharedFlow<UiEffect> = store.effectState
//
//    fun navigateTo(screen: UiScreen) {
//        store.dispatch
//        _uiScreen.value = screen
//    }
//
//    suspend fun emitEffect(effect: UiEffect) {
//        _uiEffect.emit(effect)
//    }
//}