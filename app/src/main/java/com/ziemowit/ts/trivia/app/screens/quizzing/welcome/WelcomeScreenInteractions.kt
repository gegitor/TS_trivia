package com.ziemowit.ts.trivia.app.screens.quizzing.welcome


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