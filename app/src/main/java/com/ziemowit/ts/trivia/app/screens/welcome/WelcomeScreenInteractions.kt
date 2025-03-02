package com.ziemowit.ts.trivia.app.screens.welcome


data class WelcomeScreenInteractions(
    val onNameEntered: (String) -> Unit,
    val onDismissErrorDialog: () -> Unit,
) {
    companion object {
        val STUB = WelcomeScreenInteractions(
            onNameEntered = {},
            onDismissErrorDialog = {},
        )
    }
}