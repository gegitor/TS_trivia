package com.ziemowit.ts.trivia.app.screens.quizzing.welcome

sealed class WelcomeLoadingState {
    data object Loading : WelcomeLoadingState()
    data class AskName(val invalidNameNotify: Boolean) : WelcomeLoadingState()
    data class Ready(val userName: String) : WelcomeLoadingState() {
        companion object {
            fun stub() = Ready("Ziemowit")
        }
    }
}