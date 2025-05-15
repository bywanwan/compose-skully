package com.wanwan.sandbox.navigator.ui.model

interface UiContent
data class UiSheet(
    val onDismiss: () -> Unit,
)
data class UiAlertDialog(
    val onDismiss: () -> Unit
)

interface UiAlertMessage

//enum class HapticType {
//    IMPACT_LIGHT,
//    IMPACT_MEDIUM,
//    IMPACT_HEAVY,
//    SELECTION,
//    SUCCESS,
//    WARNING,
//    ERROR,
//    LONG_PRESS
//}
//
//enum class HapticType(
//    val androidTag: String,
//    val iosTag: String
//) {
//    // Basic impacts
//    IMPACT_LIGHT(".KEYBOARD_TAP", "UIImpactFeedbackGenerator(style: .light)"),
//    IMPACT_MEDIUM(".LONG_PRESS", "UIImpactFeedbackGenerator(style: .medium)"),
//    IMPACT_HEAVY(".LONG_PRESS", "UIImpactFeedbackGenerator(style: .heavy)"),
//    IMPACT_RIGID("N/A", "UIImpactFeedbackGenerator(style: .rigid)"),
//    IMPACT_SOFT("N/A", "UIImpactFeedbackGenerator(style: .soft)"),
//
//    // Gestures
//    GESTURE_START(".GESTURE_START", ".medium"),
//    GESTURE_END(".GESTURE_END", ".medium"),
//
//    // Text / selection
//    TOGGLE_ON(".TOGGLE_ON", "UISelectionFeedbackGenerator().selectionChanged()"),
//
//
//    // Contextual
//    CONTEXT_CLICK(".CONTEXT_CLICK", ".rigid)"),
//    LONG_PRESS(".LONG_PRESS", ".medium)"),
//
//
//    //PRESS-RELEASE
//    //  VIRTUAL_KEY / VIRTUAL_KEY_RELEASE
//    //  KEYBOARD_PRESS / KEYBOARD_RELEASE
//
//    // TEXTURE
//    //  CLOCK_TICK
//    //  TEXT_HANDLE_MOVE
//
//
////
////    UIImpactFeedbackGenerator
////
//////    SelectionFeedback
////    SELECTION("HapticFeedbackConstants.TEXT_HANDLE_MOVE", "UISelectionFeedbackGenerator().selectionChanged()"),
////
//////    NotificationFeedback
////    SUCCESS("HapticFeedbackConstants.CONFIRM", "UINotificationFeedbackGenerator().notificationOccurred(.success)"),
////    ERROR("HapticFeedbackConstants.REJECT", "UINotificationFeedbackGenerator().notificationOccurred(.error)"),
////    WARNING("HapticFeedbackConstants.CONFIRM", "UINotificationFeedbackGenerator().notificationOccurred(.warning)"),
////}


/**
 * Represents a screen in the user interface, composed of a layout,
 * an optional sheet, and an optional dialog.
 *
 * @property layout The main layout of the screen, defining its primary content and structure.
 * @property sheet An optional bottom sheet to be displayed. If `null`, no sheet is shown.
 * @property dialog An optional alert dialog to be displayed. If `null`, no dialog is shown.
 */
data class UiScreen(
    val layout: UiLayout,
    val sheet: UiSheet? = null,
    val dialog: UiAlertDialog? = null,
) {
    companion object {
        val default: UiScreen = UiScreen(
            layout = UiLayout(
                content = object : UiContent {}
            )
        )
    }
}

