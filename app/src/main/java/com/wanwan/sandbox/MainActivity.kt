//package com.wanwan.sandbox
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material.navigation.rememberBottomSheetNavigator
//import androidx.compose.material3.BasicAlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.ModalBottomSheet
//import androidx.compose.material3.SnackbarDuration
//import androidx.compose.material3.Text
//import androidx.compose.material3.rememberBottomSheetScaffoldState
//import androidx.compose.material3.rememberModalBottomSheetState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.staticCompositionLocalOf
//import androidx.compose.ui.Modifier
//import androidx.navigation.NamedNavArgument
//import androidx.navigation.NavArgumentBuilder
//import androidx.navigation.NavDestination
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavHostController
//import androidx.navigation.NavType
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//import com.wanwan.sandbox.navigator.ui.model.UiAlertDialog
//import com.wanwan.sandbox.navigator.ui.model.UiEffect
//import com.wanwan.sandbox.navigator.ui.model.UiLayout
//import com.wanwan.sandbox.navigator.ui.model.UiScreen
//import com.wanwan.sandbox.navigator.ui.model.UiSheet
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.filterNotNull
//import kotlinx.coroutines.launch
//
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            MainScreen()
//        }
//    }
//}
//
//val LocalNavigator = staticCompositionLocalOf<Navigator> {
//    noLocalProvidedFor("LocalNavigator")
//}
//
//private fun noLocalProvidedFor(name: String): Nothing {
//    error("CompositionLocal $name not present")
//}
//
//
//@Composable
//fun ProfileScreen(arguments: MainScreenNav.Profile.Arguments, modifier: Modifier = Modifier) {
//    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
//        Text("ProfileScreen for ${arguments.userId}")
//
//        Content()
//    }
//}
//
//@Composable
//fun SettingsScreen(arguments: MainScreenNav.Settings.Arguments, modifier: Modifier = Modifier) {
//    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
//        Text("SettingsScreen for ${arguments}")
//
//        Content()
//    }
//}
//
////@Composable
////fun DequeueSnackbar(snackbarHostState: SnackbarHostState, input: Flow<UiEffect?>) {
////    val coroutineScope = rememberCoroutineScope()
////    val snackbarState = rememberSnackbarState(input)
////    val snackbar by snackbarState.flow.collectAsState(initial = null)
////    val haptics = LocalHapticFeedback.current
//
////    LaunchedEffect(snackbar) {
////        val currentSnackbar = snackbar ?: return@LaunchedEffect
////
////        coroutineScope.launch {
////            snackbarHostState.showSnackbar(
////                message = currentSnackbar.message,
////                duration = currentSnackbar.duration,
////                actionLabel = currentSnackbar.actionLabel,
////            )
////
////            snackbarState.next()
////        }
////    }
////}
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ScreenHost(
//    screen: UiScreen?,
//    startDestination: String,
////    content: @Composable (UiLayout, NavHostController) -> Unit,
//    builder: NavGraphBuilder.(UiLayout?, NavHostController) -> Unit,
//    navHostController: NavHostController,
//    sheetSlot: @Composable (UiSheet) -> Unit = { sheet -> SheetContent(sheet) },
//    dialogSlot: @Composable (UiAlertDialog) -> Unit = { dialog -> AlertDialogContent(dialog) },
//) {
//    val bottomSheetNavigator = rememberBottomSheetNavigator()
//
////    val destination by navigator.destination.collectAsState(initial = NavScreen.Auth)
//    val scaffoldState = rememberBottomSheetScaffoldState(
//        bottomSheetState = rememberModalBottomSheetState(),
////        bottomSheetState = rememberStandardBottomSheetState(
////            initialValue = SheetValue.Hidden,
////            skipHiddenState = false // Le problème est ici !
////        )
//    )
//
////    DequeueSnackbar(scaffoldState.snackbarHostState, navigator.effect)
//
//    screen?.dialog?.let { dialog -> dialogSlot(dialog) }
//    screen?.sheet?.let { sheet -> sheetSlot(sheet) }
//
//    NavHost(navHostController, startDestination = startDestination) {
//        builder(screen?.layout, navHostController)
//    }
//}
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SheetContent(sheet: UiSheet?) {
//    sheet?.let {
//        ModalBottomSheet(
//            sheetState = rememberModalBottomSheetState(),
//            onDismissRequest = sheet.onDismiss,
//        ) {
////            sheet
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AlertDialogContent(alertDialog: UiAlertDialog?) {
//    alertDialog?.let {
//        BasicAlertDialog(
//            onDismissRequest = alertDialog.onDismiss
//        ) {
////            alertDialog
//        }
//    }
//}
//
//
//enum class AppRoute(val route: String, val arguments: List<NamedNavArgument> = listOf()) {
//    NotInitialized("NotInitialized"),
//    Home("home"),
//    Settings("settings"),
//    Profile("profile", arguments = listOf(
//        navArgument("ID") {
//            this.type = NavType.StringType
//        }
//    ));
//
//    companion object {
//
//        fun fromRoute(route: String?): AppRoute =
//            entries.find { it.route == route } ?: Home
//    }
//}
//
//
//fun MainScreenNav.toAppRoute(): AppRoute = when (this) {
//    MainScreenNav.NotInitialized -> AppRoute.NotInitialized
//    is MainScreenNav.Home -> AppRoute.Home
//    is MainScreenNav.Profile -> AppRoute.Profile
//    is MainScreenNav.Settings -> AppRoute.Settings
//}
//
//
//sealed class MainScreenNav {
//
//    open val arguments: ScreenArgument? = null
//
//    data object NotInitialized : MainScreenNav()
//
//    data class Home(override val arguments: Arguments) : MainScreenNav() {
//        data class Arguments(val name: String) : ScreenArgument
//
//        companion object {
//            val route = AppRoute.Home
//        }
//    }
//
//    data class Settings(override val arguments: Arguments = Arguments) : MainScreenNav() {
//        object Arguments : ScreenArgument
//    }
//
//    data class Profile(override val arguments: Arguments) : MainScreenNav() {
//        data class Arguments(val userId: String) : ScreenArgument
//    }
//}
//
//inline fun <reified T : Any> T.toBundle(): Bundle {
//    val bundle = Bundle()
//    T::class.memberProperties.forEach { prop ->
//        val name = prop.name
//        val value = prop.get(this)
//        when (value) {
//            is String -> bundle.putString(name, value)
//            is Int -> bundle.putInt(name, value)
//            is Boolean -> bundle.putBoolean(name, value)
//            is Parcelable -> bundle.putParcelable(name, value)
//            // Ajoute d'autres types si besoin
//        }
//    }
//    return bundle
//}
//
//inline fun <reified T : Any> Bundle.toDataClass(): T {
//    val ctor = T::class.primaryConstructor!!
//    val args = ctor.parameters.associateWith { param ->
//        when (param.type.classifier) {
//            String::class -> getString(param.name)
//            Int::class -> getInt(param.name!!)
//            Boolean::class -> getBoolean(param.name!!)
//            else -> null
//        }
//    }
//    return ctor.callBy(args)
//}
//
//@Composable
//fun RenderRoute(appRoute: MainScreenNav) {
//    when (appRoute) {
//        is MainScreenNav.NotInitialized -> {
//            Text("NotInitialized")
//        }
//
//        is MainScreenNav.Home -> HomeScreen.Render()
//        is MainScreenNav.Settings -> SettingsScreen(arguments = appRoute.arguments)
//        is MainScreenNav.Profile -> ProfileScreen(arguments = appRoute.arguments)
//    }
//}
//
//
//val backstack: MutableMap<NavDestination, MainScreenNav> = mutableMapOf()
//
//@Composable
//fun MainScreen() {
//    val navigator = remember { Navigator() }
//    val screenNav by navigator.screenNav.collectAsState(initial = MainScreenNav.NotInitialized)
//    val navHostController: NavHostController = rememberNavController()
//
//    val currentBackStackEntry by navHostController.currentBackStackEntryFlow.collectAsState(null)
//
//    LaunchedEffect(currentBackStackEntry) {
//        println("currentBackStackEntry")
//        println(currentBackStackEntry)
//    }
//    navHostController.addOnDestinationChangedListener { controller, destination, arguments ->
//        backstack.put(destination, screenNav)
////        TODO("Not yet implemented")
//    }
//    LaunchedEffect(Unit) {
//
//        navigator.push(MainScreenNav.Home(MainScreenNav.Home.Arguments("Init")))
//    }
//    LaunchedEffect(screenNav) {
////        val navArgument = screenNav.arguments.toNavArgument()
//
//        navHostController.navigate(screenNav.toAppRoute().route)
//    }
//
//    CompositionLocalProvider(LocalNavigator provides navigator) {
//        ScreenHost(
//            startDestination = MainScreenNav.Home.route.route,
//            navHostController = navHostController,
//            screen = UiScreen.default,
//            builder = { uiLayout, navHostController ->
//                AppRoute.entries.forEach { entry ->
//                    composable(route = entry.route, arguments = entry.arguments) {
//                        it.arguments
//                        backstack[it.destination]?.let {
//                            RenderRoute(it)
//                        }
//                    }
//                }
//            }
//        )
//    }
//}
//
//@Composable
//fun Content() {
//    val navigator = LocalNavigator.current
//
//    Button(onClick = {
//        navigator.push(
//            MainScreenNav.Profile(
//                MainScreenNav.Profile.Arguments("ID")
//            )
//        )
//    }) {
//        Text("Aller à Profile")
//    }
//    Button(onClick = {
//        navigator.push(
//            MainScreenNav.Settings()
//        )
//    }) {
//        Text("Aller à Settings")
//    }
//    Button(onClick = {
//        navigator.push(
//            MainScreenNav.Home(
//                arguments = MainScreenNav.Home.Arguments("Pushed !")
//            )
//        )
//    }) {
//        Text("Aller à Home")
//    }
////    Button(onClick = {
//////        navigator.push(NavScreen.BottomSheetExemple())
////    }) {
////        Text("Snackbar")
////    }
//}
//
////@Composable
////fun NavScreen.toComposable(navigator: Navigator, backStackEntry: NavBackStackEntry) {
////    CompositionLocalProvider(LocalNavigator provides navigator) {
////        when (this) {
////            is NavScreen.Root -> {
////                HomeScreen(
////                    argument = HomeScreenArgument(name = this.javaClass.simpleName)
////                )
////            }
////
////            is NavScreen.Main -> {
////                HomeScreen(
////                    modifier = Modifier.background(Color.Red),
////                    argument = HomeScreenArgument(name = this.javaClass.simpleName)
////                )
////            }
////
////            is NavScreen.BottomSheetExemple -> {
////                HomeScreen(
////                    modifier = Modifier.background(Color.Gray),
////                    argument = HomeScreenArgument(name = this.javaClass.simpleName)
////                )
////            }
////
////            is NavScreen.DialogExemple -> {
////                HomeScreen(
////                    modifier = Modifier.background(Color.Blue),
////                    argument = HomeScreenArgument(name = this.javaClass.simpleName)
////                )
////            }
////
////            is NavScreen.AlertMessage -> {
////                HomeScreen(
////                    modifier = Modifier.background(Color.Yellow),
////                    argument = HomeScreenArgument(name = this.javaClass.simpleName)
////                )
////            }
////        }
////    }
////}
//
//
//class ScreenArguments {
//    companion object {
//
//    }
//}
//
//val argument = ScreenArgument(
//    name = "",
//    defaultValue = Unit,
//    type =
//)
//
//
//sealed class ScreenArgument {
//    data class Boolean(): ScreenArgument()
//    data class Boolean(): ScreenArgument()
//    data class Boolean(): ScreenArgument()
//    data class Boolean(): ScreenArgument()
//}
//
//
//class ScreenArgument(
//    val name: String,
//    val defaultValue: Any,
//    val type: Type
//) {
//    enum class Type {
//        BOOLEAN,
//        BOOLEAN_LIST,
//        NUMBER,
//        NUMBER_LIST,
//        STRING,
//        STRING_LIST,
//        // Ajoute d’autres types simples si besoin
//    }}
//
//
//public fun screenArgument(name: String, builder: NavArgumentBuilder.() -> Unit): NamedNavArgument =
//    NamedNavArgument(name, NavArgumentBuilder().apply(builder).build())
//
//
//@Composable
//fun rememberSnackbarState(
//    flow: Flow<UiSnackbar?>, coroutineScope: CoroutineScope = rememberCoroutineScope()
//): SnackbarState = remember { SnackbarState(coroutineScope, flow) }
//
////@Composable
////fun rememberEffectState(
////    flow: Flow<UiEffect?>,
////    coroutineScope: CoroutineScope = rememberCoroutineScope()
////): SnackbarState = remember { SnackbarState(coroutineScope, flow) }
//
//data class UiSnackbar(
//    val message: String,
//    val actionLabel: String? = null,
//    val duration: SnackbarDuration = SnackbarDuration.Short,
//    val onAction: (() -> Unit)? = null
//)
//
//class SnackbarState(coroutineScope: CoroutineScope, flow: Flow<UiSnackbar?>) {
//
//    private val queue = ArrayDeque<UiSnackbar>()
//    private val _currentSnackbar = MutableStateFlow<UiSnackbar?>(null)
//
//    val flow: Flow<UiSnackbar?> = _currentSnackbar
//
//    init {
//        coroutineScope.launch {
//            flow.filterNotNull().collect { snackbar ->
//                queue.add(snackbar)
//                internalNext()
//            }
//        }
//    }
//
//    private fun internalNext() {
//        if (_currentSnackbar.value == null) {
//            _currentSnackbar.value = queue.removeFirstOrNull()
//        }
//    }
//
//    fun next() {
//        _currentSnackbar.value = null
//        internalNext()
//    }
//}
//
//class Navigator {
//    private val scope = CoroutineScope(Dispatchers.Default)
////    private val _destination = MutableSharedFlow<NavScreen>(extraBufferCapacity = 1)
////    val destination = _destination.asSharedFlow()
//
//    private val _effect = MutableStateFlow<UiEffect?>(null)
//    val effect = _effect.asStateFlow()
//
////    private val _appRoute = MutableStateFlow<AppRoute>(
////        AppRoute.Home
////    )
////    val appRoute = _appRoute.asStateFlow()
//
//    private val _screenNav = MutableStateFlow<MainScreenNav>(
//        MainScreenNav.NotInitialized
//    )
//    val screenNav = _screenNav.asStateFlow()
//
//    fun push(screenNav: MainScreenNav) {
//        scope.launch {
//            _screenNav.emit(screenNav)
//        }
//    }
//
//    fun back() {
////        _destination.tryEmit(NavCommand.Back)
//    }
//}
//
////sealed class NavScreen {
////    abstract val route: String
////    abstract val argument: ScreenArgument?
////    abstract val type: NavType
////
////    data object Auth : NavScreen() {
////        override val route: String = "/"
////        override val argument: HomeScreenArgument? = null
////        override val type: NavType = NavType.DEFAULT
////    }
////
////    data class Main(
////        override val argument: ScreenArgument? = null
////    ) : NavScreen() {
////        override val route: String = "/"
////        override val type: NavType = NavType.DEFAULT
////    }
////
////    data class DialogExemple(
////        override val argument: ScreenArgument? = null
////    ) : NavScreen() {
////        override val route: String = "/DialogExemple"
////        override val type: NavType = NavType.DIALOG
////    }
////
////    data class BottomSheetExemple(
////        override val argument: ScreenArgument? = null
////    ) : NavScreen() {
////        override val route: String = "/BottomSheetExemple"
////        override val type: NavType = NavType.SHEET
////    }
////
////    data class AlertMessage(
////        override val argument: ScreenArgument? = null
////    ) : NavScreen() {
////        override val route: String = "/BottomSheetExemple"
////        override val type: NavType = NavType.SHEET
////    }
////}
//
//enum class NavType {
//    DEFAULT, SHEET, DIALOG
//}
//
////sealed class NavCommand {
////
////    data class Open(val screen: NavScreen, val type: NavType) : NavCommand()
////
////    object Back : NavCommand()
////}