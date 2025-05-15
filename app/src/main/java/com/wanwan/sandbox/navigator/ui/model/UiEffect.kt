package com.wanwan.sandbox.navigator.ui.model

/**
 * Represents a UI effect that can be triggered, such as displaying an alert message
 * or providing haptic feedback.
 */
sealed class UiEffect {

    /**
     * Displays an alert message to the user.
     *
     * @property message The content of the alert message.
     */
    data class AlertMessage(
        val message: UiAlertMessage,
    ) : UiEffect()

//    /**
//     * Triggers haptic feedback for the user.
//     *
//     * @property feedback The type of haptic feedback to provide.
//     */
//    data class HapticFeedback(
//        val type: HapticType
//    ) : UiEffect()
}
