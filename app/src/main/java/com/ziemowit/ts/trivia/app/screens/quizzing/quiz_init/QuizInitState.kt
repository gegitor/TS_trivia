package com.ziemowit.ts.trivia.app.screens.quizzing.quiz_init

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

internal data class QuizInitState(
    val email: State<String>,
    val isSecretDifficultyVisible: State<Boolean>,
) {
    companion object {
        fun stub(
            email: String = "john.smith@gmail.com",
            isSecretDifficultyVisible: Boolean = false,
        ) = QuizInitState(
            email = mutableStateOf(email),
            isSecretDifficultyVisible = mutableStateOf(isSecretDifficultyVisible),
        )
    }
}