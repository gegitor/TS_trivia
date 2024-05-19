package com.ziemowit.ts.trivia.app.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

internal data class HomeState(
    val email: State<String>,
//    val isSecretDifficultyVisible: State<Boolean>,
) {
    companion object {
        fun stub(
            email: String = "john.smith@gmail.com",
//            isSecretDifficultyVisible: Boolean = false,
        ) = HomeState(
            email = mutableStateOf(email),
//            isSecretDifficultyVisible = mutableStateOf(isSecretDifficultyVisible),
        )
    }
}